package rhymestudio.rhyme.entity.plants.prefabs;

import rhymestudio.rhyme.entity.AbstractPlant;

import java.util.function.Function;
import java.util.function.Supplier;

public class PresetBuilders {
    public static final Supplier<AbstractPlant.Builder> NORMAL_PEA_PLANT = () -> new AbstractPlant.Builder()
            .setAnimSpeed(2)
            .setAttackDamage(10)//子弹伤害
            .setAttackInternalTick(20)//idle_on

            .setAttackTriggerTick(15)
            .setAttackAnimTick((int) (1.5 * 20));//shoot

    public static final Supplier<AbstractPlant.Builder> NORMAL_SUNFLOWER_PLANT = () -> new AbstractPlant.Builder()
            .setAnimSpeed(5)//动画倍速
            .setAttackInternalTick(100)//产阳光间隔/tick

            .setAttackTriggerTick(10)//攻击动画触发时间
            .setAttackAnimTick(20);//攻击动画持续时间

    public static final Function<Integer,AbstractPlant.Builder> DEFENSE_PLANT = (hp) -> new AbstractPlant.Builder()
            .setHealth(hp)
            .setAttackTriggerTick(10)//攻击动画触发时间
            .setAttackAnimTick(20);//攻击动画持续时间

}
