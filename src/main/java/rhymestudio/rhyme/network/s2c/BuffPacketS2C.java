package rhymestudio.rhyme.network.s2c;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import org.jetbrains.annotations.NotNull;
import rhymestudio.rhyme.Rhyme;
import rhymestudio.rhyme.core.registry.ModAttachments;

public record BuffPacketS2C(int id,int frozenTime, int duration) implements CustomPacketPayload {
    public static final Type<BuffPacketS2C> TYPE = new Type<>(Rhyme.space("buff_packet_s2c"));
    public static final StreamCodec<ByteBuf, BuffPacketS2C> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, BuffPacketS2C::id,
            ByteBufCodecs.INT, BuffPacketS2C::frozenTime,
            ByteBufCodecs.INT, BuffPacketS2C::duration,
            BuffPacketS2C::new
    );

    @Override
    public @NotNull Type<BuffPacketS2C> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
            Entity entity = context.player().level().getEntity(id);
            if (entity!= null) {
                entity.getData(ModAttachments.BUFF_STORAGE).frozenTime = frozenTime;
                entity.getData(ModAttachments.BUFF_STORAGE).frozenDuration = duration;
            }
        }).exceptionally(e -> {
            context.disconnect(Component.translatable("neoforge.network.invalid_flow", e.getMessage()));
            return null;
        });
    }
}
