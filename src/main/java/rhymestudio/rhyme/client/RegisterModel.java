package rhymestudio.rhyme.client;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.client.model.DoublePeaModel;
import rhymestudio.rhyme.client.model.PeaModel;
import rhymestudio.rhyme.client.model.PeaProjModel;
import rhymestudio.rhyme.client.model.SunflowerModel;

public class RegisterModel {
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(PeaProjModel.LAYER_LOCATION, PeaProjModel::createBodyLayer);
        evt.registerLayerDefinition(PeaModel.LAYER_LOCATION, PeaModel::createBodyLayer);
        evt.registerLayerDefinition(SunflowerModel.LAYER_LOCATION, SunflowerModel::createBodyLayer);
        evt.registerLayerDefinition(DoublePeaModel.LAYER_LOCATION, DoublePeaModel::createBodyLayer);
    }
}
