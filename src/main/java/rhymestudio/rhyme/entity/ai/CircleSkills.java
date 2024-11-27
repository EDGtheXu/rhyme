package rhymestudio.rhyme.entity.ai;

import rhymestudio.rhyme.entity.AbstractPlant;

import java.util.ArrayList;
import java.util.List;

public class CircleSkills<T extends AbstractPlant> {
    public AbstractPlant owner;
    protected final List<CircleSkill> bossSkills = new ArrayList<>();


    public int tick = 0;
    public int index = 0;
    public boolean ifStateInit = false;

    public CircleSkills(AbstractPlant owner){ this.owner = owner;}

    public int count(){return bossSkills.size();};

    public boolean pushSkill(CircleSkill skill){
        bossSkills.add(skill);

        if(bossSkills.size()==1) tick = 0;
        return true;
    }


    public void tick(){
        if(bossSkills.isEmpty()) return ;
        this.tick++;

        if(bossSkills.get(index).stateTick !=null) {
            bossSkills.get(index).stateTick.accept(owner);
        }
        if(bossSkills.isEmpty())return;
        if(bossSkills.get(index).timeContinue < tick) forceEnd();
    }


    /** 强制结束当前状态 **/
    public void forceEnd(){
        tick = 0;
        int lastIndex = index;
        index = (index +1) % bossSkills.size();

        //状态结束
        if(bossSkills.get(lastIndex).stateOver!=null) bossSkills.get(lastIndex).stateOver.accept(owner);

        //初次进入状态
        if(bossSkills.get(index).stateInit!=null) bossSkills.get(index).stateInit.accept(owner);
        owner.getEntityData().set(AbstractPlant.DATA_CAFE_POSE_NAME,getCurSkill());

    }
    /** 强制跳转状态 **/
    public void forceStartIndex(int index){
        tick = 0;
        this.index = index;
        owner.getEntityData().set(AbstractPlant.DATA_CAFE_POSE_NAME,getCurSkill());
        owner.animState.playAnim(bossSkills.get(index).skill,owner.tickCount);
    }


    /** tick == triggerTime **/
    public boolean canTrigger(){
        if(bossSkills.isEmpty()) return false;
        return bossSkills.get(index).timeTrigger == this.tick;
    }
    /** tick > triggerTime **/
    public boolean canContinue(){
        if(bossSkills.isEmpty()) return false;
        return bossSkills.get(index).timeTrigger < this.tick;
    }
    public String getCurSkill(){
        if(!bossSkills.isEmpty())
            return bossSkills.get(index).skill;
        return "";
    }
    public int getCurAnimFullTick(){
        if(!bossSkills.isEmpty())
            return bossSkills.get(index).timeContinue;
        return -1;
    }



}
