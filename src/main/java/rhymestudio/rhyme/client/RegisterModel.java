package rhymestudio.rhyme.client;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.client.model.peaProjModel;
import rhymestudio.rhyme.client.render.entity.SunRenderer;
import rhymestudio.rhyme.client.render.entity.plant.PeaShooterRenderer;
import rhymestudio.rhyme.client.render.entity.plant.SunflowerRenderer;
import rhymestudio.rhyme.client.render.entity.proj.peaProjRenderer;
import rhymestudio.rhyme.registry.ModEntities;

public class RegisterModel {
    public static void register(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(peaProjModel.LAYER_LOCATION, peaProjModel::createBodyLayer);

    }
}
