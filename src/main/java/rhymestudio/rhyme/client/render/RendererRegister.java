package rhymestudio.rhyme.client.render;

import net.minecraftforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.client.render.entity.BasePlantRenderer;
import rhymestudio.rhyme.client.render.entity.SunRenderer;
import rhymestudio.rhyme.client.render.entity.plant.SunflowerRenderer;
import rhymestudio.rhyme.entity.plants.Pea;
import rhymestudio.rhyme.registry.ModEntities;

public class RendererRegister {
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SUN_ITEM_ENTITY.get(), SunRenderer::new);

        event.registerEntityRenderer(ModEntities.SUN_FLOWER.get(), SunflowerRenderer::new);
        event.registerEntityRenderer(ModEntities.PEA.get(), BasePlantRenderer<Pea>::new);



    }
}
