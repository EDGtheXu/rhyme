package rhymestudio.rhyme.entity.plants.prefabs;

import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.Vec3;
import rhymestudio.rhyme.entity.AbstractPlant;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.entity.proj.LineProj;
import rhymestudio.rhyme.utils.Computer;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class peaPrefabs {
    public static final Supplier<AbstractPlant.Builder> PEA = () -> new peaPrefabs(1,1,10,10,10).getPrefab();


    private AbstractPlant.Builder  builder;
    public peaPrefabs(int attack,int health,int idleTick,int attackTriggerTick,int attackAnimTick){
        builder = new AbstractPlant.Builder()
                .setAnimSpeed(2)
                .setAttackDamage(attack)
                .setHealth(health)
                .setAttackInternalTick(idleTick)
                .setAttackTriggerTick(attackTriggerTick)
                .setAttackAnimTick(attackAnimTick)
                .setDefineSkill(plant->{
                    //tip                                                  idle持续时间        触发攻击时间
                    CircleSkill idle = new CircleSkill( "idle",  999999999, builder.attackInternalTick,
                            // tip刚进入状态
                            a->{},
                            // tip进入状态触发时间
                            a-> {
                                if(plant.skills.canContinue() &&
                                        plant.getTarget() != null && plant.getTarget().isAlive() &&
                                        Computer.angle(plant.getForward(), plant.getTarget().getEyePosition().subtract(plant.getEyePosition())) < 20){

                                    //tip触发攻击，进入射击状态
                                    plant.skills.forceEnd();
                                }
                            },
                            // tip结束状态
                            a->{}
                    );
                    // tip                                                攻击持续时间        射击触发时间
                    CircleSkill  shoot = new CircleSkill( "shoot", builder.attackAnimTick, builder.attackTriggerTick,
                            a->{},
                            a->{
                                if(plant.skills.canTrigger() && plant.getTarget()!= null && plant.getTarget().isAlive()){
                                    //tip 触发射击，生成弹幕
                                    Vec3 pos = plant.getTarget().getEyePosition();
//        Projectile arrow = new Arrow(level(), this, Items.ARROW.getDefaultInstance(), Items.ARROW.getDefaultInstance());
                                    Projectile arrow =new LineProj(plant,builder.attackDamage,builder.projSpeed);
                                    arrow.setOwner(plant);
                                    arrow.setPos(plant.getEyePosition().add(0,0.1F,0));
                                    Vec3 dir = pos.subtract(plant.getEyePosition());
                                    arrow.shoot(dir.x, dir.y, dir.z, builder.projSpeed, 1.0F);
                                    plant.level().addFreshEntity(arrow);
                                }
                            },
                            a->{}
                    );
                    plant.addSkill(idle);
                    plant.addSkill(shoot);
                })

        ;

    }
    public AbstractPlant.Builder getPrefab(){
        return builder;
    }


}
