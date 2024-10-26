package rhymestudio.rhyme.entity.ai;

import rhymestudio.rhyme.entity.AbstractPlant;

import java.util.function.Consumer;

public class BossSkill <T extends AbstractPlant>{

    public String skillID;
    public String skill;
    public int timeContinue;
    public int timeTrigger;

    public Consumer<AbstractPlant> stateInit;
    public Consumer<AbstractPlant> stateTick;
    public Consumer<AbstractPlant> stateOver;

    /**
     *
     * @param skillID 强制状态跳转标识  已废弃
     * @param skill 动画名称
     * @param timeContinue 状态持续时间
     * @param timeTrigger 逻辑触发时间
     */
    public BossSkill(String skillID, String skill, int timeContinue, int timeTrigger){
        this.skill = skill;
        this.timeContinue = timeContinue;
        this.timeTrigger = timeTrigger;
        this.skillID = skillID;
    }

    public BossSkill(String skillID, String animName, int timeContinue, int timeTrigger,
                     Consumer<AbstractPlant> stateInit,
                     Consumer<AbstractPlant> stateTick,
                     Consumer<AbstractPlant> stateOver
    ){
        this.skill = animName;
        this.timeContinue = timeContinue;
        this.timeTrigger = timeTrigger;
        this.skillID = skillID;
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
