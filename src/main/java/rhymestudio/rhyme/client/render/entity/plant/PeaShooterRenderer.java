package rhymestudio.rhyme.client.render.entity.plant;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import rhymestudio.rhyme.client.model.peaModel;
import rhymestudio.rhyme.client.render.entity.BasePlantRenderer;
import rhymestudio.rhyme.entity.plants.PeaNew;

public class PeaShooterRenderer extends BasePlantRenderer<PeaNew,peaModel> {

    public PeaShooterRenderer(EntityRendererProvider.Context cnt) {
        super(cnt,new peaModel(cnt.bakeLayer(peaModel.LAYER_LOCATION)));
    }

}
