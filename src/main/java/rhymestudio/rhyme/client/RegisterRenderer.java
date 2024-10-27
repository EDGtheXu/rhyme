package rhymestudio.rhyme.client;

import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import rhymestudio.rhyme.client.render.entity.SunRenderer;
import rhymestudio.rhyme.client.render.entity.plant.PeaShooterRenderer;
import rhymestudio.rhyme.client.render.entity.plant.SunflowerRenderer;
import rhymestudio.rhyme.client.render.entity.proj.peaProjRenderer;
import rhymestudio.rhyme.registry.ModEntities;

public class RegisterRenderer {
    public static void register(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.SUN_ITEM_ENTITY.get(), SunRenderer::new);
// 植物
        event.registerEntityRenderer(ModEntities.SUN_FLOWER.get(), SunflowerRenderer::new);
        event.registerEntityRenderer(ModEntities.PEA_SHOOTER.get(), PeaShooterRenderer::new);
        event.registerEntityRenderer(ModEntities.DOUBLE_PEA_SHOOTER.get(), PeaShooterRenderer::new);


// 子弹
        event.registerEntityRenderer(ModEntities.PEA_PROJ.get(), peaProjRenderer::new);

    }
}
