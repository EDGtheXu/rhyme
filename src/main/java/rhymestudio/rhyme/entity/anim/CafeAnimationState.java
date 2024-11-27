package rhymestudio.rhyme.entity.anim;

import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Mob;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CafeAnimationState {
    public record Tuple(AnimationDefinition animDef ,float duration, float animationSpeed, boolean isLooping){}
    public Map<String ,Tuple> animationMap;

    public int tick = 0;
    public float globalAnimSpeed = 1;
    public String curName = "idle";
    public String prevName = "idle";
    public AnimationState curAnimState = new AnimationState();
    public AnimationDefinition curAnimDef = null;
    public Mob mob;

    public CafeAnimationState(Mob mob,Map<String, Tuple> animationMap) {
        this.mob = mob;
        this.animationMap = animationMap;
    }

    public CafeAnimationState(Mob mob) {
        this.animationMap = new HashMap<>();
        this.mob = mob;
    }

    public void addAnimation(String name, AnimationDefinition anim, float speed) {
        if(anim!=null) this.animationMap.put(name, new Tuple(anim,anim.lengthInSeconds(),speed,anim.looping()));
    }
    public void addAnimation(String name, AnimationDefinition anim) {addAnimation(name, anim, 1);    }

    public float getDuration(String animationName) {
        return animationMap.get(animationName).duration;
    }

    public float getAnimSpeed() {
        return animationMap.get(curName).animationSpeed;
    }

    public boolean isLooping(String animationName) {
        return animationMap.get(animationName).isLooping;
    }



    public void stopAnim(){
        this.curAnimState.stop();
    }

    public void playAnim(String name, int tick){
        if(!this.animationMap.isEmpty()){
            curAnimState.start(tick);
            this.curAnimDef = this.animationMap.get(name).animDef;
            this.prevName = this.curName;
            this.curName = name;
            this.tick = tick;
        }
    }

    public int getAnimTick(String name) {
        return this.tick;
    }





}
