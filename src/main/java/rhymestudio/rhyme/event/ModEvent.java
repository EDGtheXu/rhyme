package rhymestudio.rhyme.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import rhymestudio.rhyme.network.s2c.BuffPacketS2C;
import rhymestudio.rhyme.network.s2c.SunCountPacketS2C;

import static rhymestudio.rhyme.Rhyme.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModEvent {

    @SubscribeEvent
    public static void registerPayloadHandlers(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
        registrar.playToClient(SunCountPacketS2C.TYPE, SunCountPacketS2C.STREAM_CODEC, SunCountPacketS2C::handle);
        registrar.playToServer(BuffPacketS2C.TYPE, BuffPacketS2C.STREAM_CODEC, BuffPacketS2C::handle);
    }
}
