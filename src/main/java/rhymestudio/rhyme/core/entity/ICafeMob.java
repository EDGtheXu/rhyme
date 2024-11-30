package rhymestudio.rhyme.core.entity;

import rhymestudio.rhyme.core.entity.anim.CafeAnimationState;

public interface ICafeMob {
    CafeAnimationState getCafeAnimState();

    default boolean canAnimate(){return true;}



}
