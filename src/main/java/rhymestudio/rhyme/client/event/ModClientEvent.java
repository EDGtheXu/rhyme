package rhymestudio.rhyme.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.client.model.ModelUtils;
import rhymestudio.rhyme.client.post.PostUtil;

import java.io.File;
import java.io.FileNotFoundException;

import static rhymestudio.rhyme.client.model.ModelUtils.HEAD_MODEL_ITEMS;

@EventBusSubscriber(modid = Rhyme.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ModClientEvent {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {

            PostUtil.init();
        });

    }
    @SubscribeEvent
    public static void registerAdditionalModel(ModelEvent.RegisterAdditional event) {

        BuiltInRegistries.ITEM.forEach(item->{
            ResourceLocation location = ModelUtils.getHeadModelResourceLocation(item);
            ResourceManager provider = Minecraft.getInstance().getResourceManager();
            try{
                ResourceLocation location1 = BuiltInRegistries.ITEM.getKey(item);
                String name =  "models/item/" + location1.getPath() + "_head";
                location1 = ResourceLocation.fromNamespaceAndPath(location.getNamespace(), name+".json");
                provider.getResourceOrThrow(location1);

                var modelResourceLocation = ModelResourceLocation.standalone(location);

                event.register(modelResourceLocation);
                HEAD_MODEL_ITEMS.put(item, location);
            } catch (FileNotFoundException e) {
//                System.out.println("not exist");
            }

        });
    }
    private static void parseAll(File file){
        File[] fs = file.listFiles();
        if (fs != null) {
            for(File f: fs){
                if(f.isDirectory())	//若是目录，则递归打印该目录下的文件
                    parseAll(f);
                if(f.isFile())		//若是文件，直接打印
                    System.out.println(f);
            }
        }
    }
}
