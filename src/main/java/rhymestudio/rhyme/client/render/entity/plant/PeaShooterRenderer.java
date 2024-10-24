package rhymestudio.rhyme.client.render.entity.plant;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import rhymestudio.rhyme.client.model.GeoNormalModel;
import rhymestudio.rhyme.client.render.entity.BasePlantRenderer;
import rhymestudio.rhyme.entity.plants.Pea;
import rhymestudio.rhyme.entity.plants.SunFlower;

public class PeaShooterRenderer extends BasePlantRenderer<Pea> {


    public PeaShooterRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GeoNormalModel<>("peashooter_new"));
    }


}
