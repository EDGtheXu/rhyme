package rhymestudio.rhyme.entity.ai;

import rhymestudio.rhyme.entity.AbstractPlant;

import java.util.function.Consumer;

public class CircleSkill {


    public String skill;
    public int timeContinue;
    public int timeTrigger;

    public Consumer<AbstractPlant> stateInit;
    public Consumer<AbstractPlant> stateTick;
    public Consumer<AbstractPlant> stateOver;

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
                       Consumer<AbstractPlant> stateInit,
                       Consumer<AbstractPlant> stateTick,
                       Consumer<AbstractPlant> stateOver
    ){
        this.skill = animName;
        this.timeContinue = timeContinue;
        this.timeTrigger = timeTrigger;

        this.stateInit = stateInit;
        this.stateTick = stateTick;
        this.stateOver = stateOver;
    }

    public void addStateReset(Consumer<AbstractPlant> stateTick){
        this.stateTick = stateTick;
    };
    public void addStateInit (Consumer<AbstractPlant> stateInit){
        this.stateInit = stateInit;
    };
    public void addStateOver (Consumer<AbstractPlant> stateOver){
        this.stateOver = stateOver;
    };
}
