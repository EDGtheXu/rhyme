package rhymestudio.rhyme.datagen.recipe;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.crafting.IngredientType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import rhymestudio.rhyme.Rhyme;

import java.util.function.Supplier;

import static rhymestudio.rhyme.Rhyme.MODID;

public final class ModRecipes {
    public static final DeferredRegister<IngredientType<?>> INGREDIENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.INGREDIENT_TYPES, MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, MODID);

    public static final Supplier<IngredientType<AmountIngredient>> AMOUNT_INGREDIENT_TYPE = INGREDIENT_TYPES.register("amount_ingredient", () -> new IngredientType<>(AmountIngredient.CODEC, AmountIngredient.STREAM_CODEC));

    public static final Supplier<RecipeType<SunCreatorRecipe>> SUN_CREATOR_TYPE = RECIPE_TYPES.register("sun_creator", () -> RecipeType.simple(Rhyme.space("sun_creator")));
    public static final Supplier<RecipeSerializer<SunCreatorRecipe>> SUN_CREATOR_SERIALIZER = RECIPE_SERIALIZERS.register("sun_creator", SunCreatorRecipe.Serializer::new);

    public static void register(IEventBus eventBus) {
        INGREDIENT_TYPES.register(eventBus);
        RECIPE_TYPES.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
