package rhymestudio.rhyme.entity.plants;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.goal.shootGoal;
import rhymestudio.rhyme.registry.ModEntities;

public class Pea extends AbstractPlant {
    static final int internalTime = 1*20;


    public Pea(EntityType<? extends Pea> tEntityType, Level level) {
        super(tEntityType, level);
    }

    public Pea(Vec3 pos, Level level) {
        super(ModEntities.PEA.get(), level);
        this.setPos(pos);
    }

    public void registerGoals(){
//        this.goalSelector.addGoal(0, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(1, new LookAtPlayerGoal(this, Monster.class, 20.0F));
        this.goalSelector.addGoal(0,new shootGoal(this, internalTime,()->new Arrow(level(), position().x, position().y+1, position().z)));

        this.targetSelector.addGoal(0, new NearestAttackableTargetGoal<>(this, Slime.class, true));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Monster.class, true));
    }

    public void tick() {
        super.tick();

        if(!level().isClientSide()){
            if(getTarget()==null) return;
            lookAt(getTarget(),30,85);


            /*
            if(--internalTime <= 0){

                if(computer.angle(getForward(),getTarget().position().subtract(position()))>0.05) {return;}
                internalTime = MAX_AGE;
                Arrow arrow = new Arrow(level(), position().x, position().y+1, position().z);
                arrow.setOwner(this);
                arrow.shoot(getForward().x, getForward().y, getForward().z, 2F, 1.0F);
                level().addFreshEntity(arrow);

            }
            */




        }

    }

    @Override
    public ResourceLocation getTexture() {
        return Rhyme.space("textures/item/plant_card/pea.png");
    }
}
