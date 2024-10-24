package rhymestudio.rhyme.client.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.entity.AbstractPlant;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;

public class GeoNormalModel<T extends AbstractPlant> extends DefaultedEntityGeoModel<T> {
    public GeoNormalModel(String path) {
        super(new ResourceLocation(Rhyme.MODID, path));
    }

    @Override
    public RenderType getRenderType(T animatable, ResourceLocation texture) {
        return RenderType.entityTranslucent(getTextureResource(animatable));
    }
}