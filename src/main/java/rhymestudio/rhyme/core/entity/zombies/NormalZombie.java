package rhymestudio.rhyme.core.entity.zombies;

import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import rhymestudio.rhyme.core.entity.AbstractMonster;
import rhymestudio.rhyme.core.registry.Entities.Zombies;
import rhymestudio.rhyme.core.registry.items.ArmorItems;

import javax.annotation.Nullable;

public class NormalZombie extends AbstractMonster {
    public NormalZombie(EntityType<? extends Monster> type, Level level, Builder builder) {
        super(type, level, builder);
    }

    public String getNamePath() {
        return "normal_zombie";
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

        if(this.getType() == Zombies.CONE_ZOMBIE.get()){
            this.setItemSlot(EquipmentSlot.HEAD, ArmorItems.CONE_HELMET.toStack());
        }else if(this.getType() == Zombies.IRON_BUCKET_ZOMBIE.get()){
            this.setItemSlot(EquipmentSlot.HEAD, ArmorItems.IRON_BUCKET_HELMET.toStack());
        }

        return spawnGroupData;
    }
}
