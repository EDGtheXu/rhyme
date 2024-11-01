package rhymestudio.rhyme.client.animation;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Mob;
import rhymestudio.rhyme.client.render.entity.CafeEntity;

import java.util.HashMap;
import java.util.Map;

public class CafeAnimationState {
    public record Tuple(AnimationState animationState, int duration, float animationSpeed, boolean isLooping){}
    public Map<String ,Tuple> animationMap;
    public String curName = "";
    public int tick = 0;

    public CafeAnimationState(Map<String, Tuple> animationMap) {
        this.animationMap = animationMap;

    }
    public CafeAnimationState() {
        this.animationMap = new HashMap<>();
    }

    public void addAnimation(String animationName, AnimationState animationState, int duration, float animationSpeed, boolean isLooping) {
        animationMap.put(animationName, new Tuple(animationState, duration, animationSpeed, isLooping));
    }

    public AnimationState getAnimationState(String animationName) {
        return animationMap.get(animationName).animationState;
    }

    public int getDuration(String animationName) {
        return animationMap.get(animationName).duration;
    }

    public float getAnimationSpeed(String animationName) {
        return animationMap.get(animationName).animationSpeed;
    }

    public boolean isLooping(String animationName) {
        return animationMap.get(animationName).isLooping;
    }

}
