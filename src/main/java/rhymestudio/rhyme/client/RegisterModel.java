package rhymestudio.rhyme.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.client.model.peaModel;
import rhymestudio.rhyme.client.model.peaProjModel;

public class RegisterModel {
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(peaProjModel.LAYER_LOCATION, peaProjModel::createBodyLayer);
        evt.registerLayerDefinition(peaModel.LAYER_LOCATION, peaModel::createBodyLayer);

    }
}
