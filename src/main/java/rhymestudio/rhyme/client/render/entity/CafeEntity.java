package rhymestudio.rhyme.client.render.entity;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Mob;
import rhymestudio.rhyme.client.animation.CafeAnimationState;
import rhymestudio.rhyme.entity.ai.CircleSkill;
import rhymestudio.rhyme.entity.ai.CircleSkills;

public interface CafeEntity<T extends Mob> {
    CafeAnimationState cafeAnimationState = new CafeAnimationState();

    default void initCafe(Mob entity){

    }

    default AnimationState cafeGetAnimationState(String name){
        return cafeAnimationState.animationMap.get(name).animationState();
    }

    default void cafeResetPose(){
        cafeAnimationState.animationMap.forEach((k,v)->v.animationState().stop());

    }

    default void cafePlayAnimation(String name,int tick){
        cafeAnimationState.animationMap.get(name).animationState().start(tick);
        cafeAnimationState.curName = name;
    }

    default int cafeGetAnimTick(String name) {
        return cafeAnimationState.tick;
    }

    default void cafeStopAnimation(String name){
        cafeAnimationState.animationMap.get(name).animationState().stop();
    }

    default void cafeAddAnimation(String name, int duration, float animationSpeed, boolean isLooping) {
        cafeAnimationState.animationMap.put(name, new CafeAnimationState.Tuple(new AnimationState(), duration, animationSpeed, isLooping));
    }


    void cafeDefineAnimations();


    void addSkills();


}
