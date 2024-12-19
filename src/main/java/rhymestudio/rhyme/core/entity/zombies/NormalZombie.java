package rhymestudio.rhyme.core.entity.zombies;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import rhymestudio.rhyme.core.entity.AbstractMonster;
import rhymestudio.rhyme.core.entity.misc.HelmetEntity;
import rhymestudio.rhyme.core.registry.entities.MiscEntities;
import rhymestudio.rhyme.core.registry.entities.Zombies;
import rhymestudio.rhyme.core.registry.items.ArmorItems;

import javax.annotation.Nullable;

public class NormalZombie extends AbstractMonster {
    public NormalZombie(EntityType<? extends Monster> type, Level level, Builder builder) {
        super(type, level, builder);
        if(this.getType() == Zombies.CONE_ZOMBIE.get()){
            this.setItemSlot(EquipmentSlot.HEAD, ArmorItems.CONE_HELMET.toStack());
        }else if(this.getType() == Zombies.IRON_BUCKET_ZOMBIE.get()){
            this.setItemSlot(EquipmentSlot.HEAD, ArmorItems.IRON_BUCKET_HELMET.toStack());
        }
    }

    public String getNamePath() {
        return "normal_zombie";
    }

    public boolean hurt(DamageSource source, float amount){
        ItemStack stack = this.getItemBySlot(EquipmentSlot.HEAD);
        if(!level().isClientSide && this.getHealth()-amount < 35 && !stack.isEmpty()){
            HelmetEntity entity = MiscEntities.HELMET_ENTITY.get().create(level());
            entity.setPos(this.getEyePosition());
            entity.setOwner(this);
            entity.setHelmetStack(stack);
            level().addFreshEntity(entity);
            this.setItemSlot(EquipmentSlot.HEAD, ItemStack.EMPTY);
        }
        return super.hurt(source, amount);
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        RandomSource randomsource = level.getRandom();
/*
        if (this.getItemBySlot(EquipmentSlot.HEAD).isEmpty()) {
            if(randomsource.nextFloat() < getAttributeBaseValue(Attributes.SPAWN_REINFORCEMENTS_CHANCE)){
                if(randomsource.nextFloat() < 0.33f){
                    this.setItemSlot(EquipmentSlot.HEAD, ArmorItems.IRON_BUCKET_HELMET.toStack());

                }else{
                    this.setItemSlot(EquipmentSlot.HEAD, ArmorItems.CONE_HELMET.toStack());

                }
            }
        }*/



        return spawnGroupData;
    }
}
