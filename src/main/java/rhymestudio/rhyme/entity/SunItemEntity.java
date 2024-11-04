package rhymestudio.rhyme.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.registry.ModEntities;
import rhymestudio.rhyme.registry.items.MaterialItems;
import rhymestudio.rhyme.registry.items.PlantItems;


public class SunItemEntity extends ItemEntity {
    private boolean wasOnGround;
    int age = 20*20;

    public SunItemEntity(EntityType<SunItemEntity> entityType, Level level) {
        super(entityType, level);
        this.wasOnGround = false;
    }

    public SunItemEntity(Level level, Vec3 pos) {
        this(ModEntities.SUN_ITEM_ENTITY.get(), level);
        setPos(pos);
        setItem(MaterialItems.SUN_ITEM.get().getDefaultInstance());
        float r =  0.2F;
        float f = random.nextFloat() * 3.1415927F * 2.0F;
        setDeltaMovement(r*Math.sin(f*10),0.2f,r*Math.cos(f*10));
        this.lifespan = 12000;

    }

    @Override
    public void tick() {
        super.tick();
        if (!level().isClientSide) {
            if(--age<0) discard();
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putBoolean("wasOnGround", wasOnGround);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.wasOnGround = pCompound.getBoolean("wasOnGround");
    }

    public static void summon(ServerLevel serverLevel) {
        if (!(serverLevel.dimension().equals(Level.OVERWORLD) && serverLevel.getDayTime() % 24000 > 12000 && serverLevel.getGameTime() % 600 == 0)) {
            RandomSource random = serverLevel.random;
            for (ServerPlayer serverPlayer : serverLevel.players()) {
                int offsetX = (random.nextBoolean() ? 1 : -1) * random.nextInt(2);
                int offsetZ = (random.nextBoolean() ? 1 : -1) * random.nextInt(2);
                BlockPos pos = serverPlayer.getOnPos().offset(offsetX, 0, offsetZ).atY(256);
                if (serverLevel.isLoaded(pos)) {
                    serverLevel.addFreshEntity(new SunItemEntity(serverLevel, pos.getCenter()));
                }
            }
        }
    }
}
