package rhymestudio.rhyme.entity;

import rhymestudio.rhyme.entity.anim.CafeAnimationState;

public interface ICafeMob {
    CafeAnimationState getCafeAnimState();

    default boolean canAnimate(){return true;}



}
