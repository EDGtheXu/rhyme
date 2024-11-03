package rhymestudio.rhyme.entity.ai;

import net.minecraft.world.entity.Mob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.function.Consumer;

public class CircleSkill {


    private static final Logger log = LoggerFactory.getLogger(CircleSkill.class);
    public String skill;
    public int timeContinue;
    public int timeTrigger;

    public Consumer<Mob> stateInit;
    public Consumer<Mob> stateTick;
    public Consumer<Mob> stateOver;

    /**
     * @param skill 动画名称
     * @param timeContinue 状态持续时间
     * @param timeTrigger 逻辑触发时间
     */
    public CircleSkill(String skill, int timeContinue, int timeTrigger){
        this.skill = skill;
        this.timeContinue = timeContinue;
        this.timeTrigger = timeTrigger;

    }

    public CircleSkill(String animName, int timeContinue, int timeTrigger,
                       Consumer<Mob> stateInit,
                       Consumer<Mob> stateTick,
                       Consumer<Mob> stateOver
    ){
        this.skill = animName;
        this.timeContinue = timeContinue;
        this.timeTrigger = timeTrigger;

        this.stateInit = stateInit;
        this.stateTick = stateTick;
        this.stateOver = stateOver;
    }

    public void addStateReset(Consumer<Mob> stateTick){
        this.stateTick = stateTick;
    };
    public void addStateInit (Consumer<Mob> stateInit){
        this.stateInit = stateInit;
    };
    public void addStateOver (Consumer<Mob> stateOver){
        this.stateOver = stateOver;
    };
}
