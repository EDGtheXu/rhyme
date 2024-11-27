package rhymestudio.rhyme.client.model;

import rhymestudio.rhyme.entity.AbstractPlant;

public abstract class AbstractPlantModel<T extends AbstractPlant> extends AbstractAnimModel<T>{
    public boolean isRotatingZ() {
        return true;
    }
}