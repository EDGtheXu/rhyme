package rhymestudio.rhyme.event;

import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import rhymestudio.rhyme.entity.AbstractPlant;

@Mod.EventBusSubscriber(modid = "rhyme")
public class EntityEvent {

    @SubscribeEvent
    public static void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if(event.getEntity() instanceof Monster monster ){
            if(monster instanceof NeutralMob) return;
            monster.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(monster, AbstractPlant.class, true));
        }else if(event.getEntity() instanceof Slime slime){
            slime.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(slime,  AbstractPlant.class, true));
        }
    }
}
