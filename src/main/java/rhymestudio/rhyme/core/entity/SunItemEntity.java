package rhymestudio.rhyme.core.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;
import rhymestudio.rhyme.network.s2c.SunCountPacketS2C;
import rhymestudio.rhyme.core.registry.entities.PlantEntities;
import rhymestudio.rhyme.core.registry.ModAttachments;


public class SunItemEntity extends ExperienceOrb {
    private boolean wasOnGround;
    int age = 20*20;

    public SunItemEntity(EntityType<SunItemEntity> entityType, Level level) {
        super(entityType, level);
        this.wasOnGround = false;
    }

    public SunItemEntity(Level level, Vec3 pos) {
        this(PlantEntities.SUN_ITEM_ENTITY.get(), level);
        setPos(pos);

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

    public void playerTouch(Player entity) {
        if (entity instanceof ServerPlayer serverplayer) {
            if (entity.takeXpDelay == 0) {
                int c = ++serverplayer.getData(ModAttachments.PLAYER_STORAGE).sunCount;
                PacketDistributor.sendToPlayer(serverplayer, new SunCountPacketS2C(c));
                discard();
            }
        }
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
