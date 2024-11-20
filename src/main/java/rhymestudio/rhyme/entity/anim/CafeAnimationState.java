package rhymestudio.rhyme.entity.anim;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.AnimationState;

import java.util.HashMap;
import java.util.Map;

public class CafeAnimationState {
    public record Tuple(AnimationState animationState, float duration, float animationSpeed, boolean isLooping){}
    public Map<String ,Tuple> animationMap;
    public String curName = "idle_on";
    public int tick = 0;
    public float globalAnimSpeed = 1;
    public CafeAnimationState(Map<String, Tuple> animationMap) {
        this.animationMap = animationMap;
    }

    public CafeAnimationState() {
        this.animationMap = new HashMap<>();
    }

    public void addAnimation(String name, AnimationDefinition anim, float speed) {
        if(anim!=null) this.animationMap.put(name, new Tuple(new AnimationState(),anim.lengthInSeconds(),speed,anim.looping()));
    }

    public float getDuration(String animationName) {
        return animationMap.get(animationName).duration;
    }

    public float getAnimSpeed() {
        return animationMap.get(curName).animationSpeed;
    }

    public boolean isLooping(String animationName) {
        return animationMap.get(animationName).isLooping;
    }

    public AnimationState getAnim(String name){
        return this.animationMap.get(name).animationState();
    }

    public void resetAnim(){
        this.animationMap.forEach((k,v)->v.animationState().stop());
    }

    public void playAnim(String name, int tick){
        if(!this.animationMap.isEmpty()){
            this.animationMap.get(name).animationState().start(tick);
            this.curName = name;
        }
    }

    public int getAnimTick(String name) {
        return this.tick;
    }

    public void stopAnim(String name){
        this.animationMap.get(name).animationState().stop();
    }




}
