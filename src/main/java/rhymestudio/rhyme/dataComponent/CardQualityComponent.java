package rhymestudio.rhyme.dataComponent;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.Nullable;
import rhymestudio.rhyme.registry.ModDataComponentTypes;
import rhymestudio.rhyme.registry.items.IconItems;

import java.util.Map;

public record CardQualityComponent(int level) implements DataComponentType<CardQualityComponent> {
    static int maxLevel = 4;

    public static final CardQualityComponent COPPER = new CardQualityComponent(0);//铜
    public static final CardQualityComponent SILVER = new CardQualityComponent(1);//银
    public static final CardQualityComponent GOLD = new CardQualityComponent(2);//金
    public static final CardQualityComponent EMERALD = new CardQualityComponent(3);//翡翠
    public static final CardQualityComponent DIAMOND = new CardQualityComponent(4);//钻石

    private record Tuple(DeferredItem<Item> qualityItem, CardQualityComponent component) { }
    private static final Map<Integer, Tuple> _levels = Map.of(
            0, new Tuple(IconItems.COPPER, COPPER),
            1, new Tuple(IconItems.SILVER, SILVER),
            2, new Tuple(IconItems.GOLD, GOLD),
            3, new Tuple(IconItems.EMERALD, EMERALD),
            4, new Tuple(IconItems.DIAMOND, DIAMOND)
    );

    public CardQualityComponent(int level) {
        if (level < 0) this.level = 0;
        else this.level = Math.min(level, maxLevel);
    }

    public CardQualityComponent ofLevel(int level) {
        if(level < 0 || level > maxLevel) return this;
        return _levels.get(level).component;
    }

    public CardQualityComponent increaseLevel() {
        if (level < maxLevel) return _levels.get(level + 1).component;
        return this;
    }

    public CardQualityComponent decreaseLevel() {
        if (level > 0) return _levels.get(level - 1).component;
        return this;
    }

    public Item getQualityItem() {return _levels.get(level).qualityItem.get();}

    public static boolean tryUpLevel(ItemStack stack){
        var data = stack.getComponents().get(ModDataComponentTypes.CARD_QUALITY.get());
        if(data == null) return false;
        if(data.increaseLevel() == data) return false;
        stack.set(ModDataComponentTypes.CARD_QUALITY.get(), data.increaseLevel());
        return true;
    }

    public static boolean tryDownLevel(ItemStack stack){
        var data = stack.getComponents().get(ModDataComponentTypes.CARD_QUALITY.get());
        if(data == null) return false;
        if(data.decreaseLevel() == data) return false;
        stack.set(ModDataComponentTypes.CARD_QUALITY.get(), data.decreaseLevel());
        return true;
    }


    public static final Codec<CardQualityComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("card_level").forGetter(CardQualityComponent::level)
    ).apply(instance, CardQualityComponent::new));


    public static final StreamCodec<ByteBuf, CardQualityComponent> STREAM_CODEC =
            ByteBufCodecs.INT.map(CardQualityComponent::new, CardQualityComponent::level);

    @Override
    public @Nullable Codec<CardQualityComponent> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<? super RegistryFriendlyByteBuf, CardQualityComponent> streamCodec() {
        return STREAM_CODEC;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CardQualityComponent component)) return false;

        return component.level == level;
    }


}
