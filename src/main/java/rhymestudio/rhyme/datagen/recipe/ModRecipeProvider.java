package rhymestudio.rhyme.datagen.recipe;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import rhymestudio.rhyme.core.registry.ModBlocks;
import rhymestudio.rhyme.core.registry.items.ArmorItems;
import rhymestudio.rhyme.core.registry.items.MaterialItems;

import java.util.concurrent.CompletableFuture;

import static rhymestudio.rhyme.Rhyme.MODID;

public class ModRecipeProvider extends RecipeProvider {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {


        //光萃台
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SUN_CREATOR_BLOCK.get())
                .pattern(" C ")
                .pattern("BAB")
                .pattern("AAA")
                .define('A',Items.IRON_INGOT)
                .define('B',Items.WHEAT_SEEDS)
                .define('C',Items.EMERALD)
                .unlockedBy("has_emerald",has(Items.EMERALD))
                .save(recipeOutput);
        //路障
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ArmorItems.CONE_HELMET.get())
                .pattern(" A ")
                .pattern("AAA")
                .define('A',Items.TERRACOTTA)
                .unlockedBy("has_terracotta",has(Items.TERRACOTTA))
                .save(recipeOutput);

        //僵尸的铁桶
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ArmorItems.IRON_BUCKET_HELMET.get())
                .pattern("AAA")
                .pattern("A A")
                .pattern(" A ")
                .define('A',Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot",has(Items.IRON_INGOT))
                .save(recipeOutput);


        // 万能种子配方
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MaterialItems.GENERAL_SEED.get())
                .requires(Items.WHEAT_SEEDS,2)
                .unlockedBy("has_wheat_seeds",has(Items.WHEAT_SEEDS))
                .save(recipeOutput);

        // 植物基因配方
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, MaterialItems.PLANT_GENE.get())
                .requires(Items.WHEAT_SEEDS,4)
                .unlockedBy("has_wheat_seeds",has(Items.WHEAT_SEEDS))
                .save(recipeOutput);



//        cookRecipes(recipeOutput, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, CampfireCookingRecipe::new, 100);

        //test
        // We use a builder pattern, therefore no variable is created. Create a new builder by calling
// ShapelessRecipeBuilder#shapeless with the recipe category (found in the RecipeCategory enum)
// and a result item, a result item and count, or a result item stack.
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Items.MUSHROOM_STEW)
                // Add the recipe ingredients. This can either accept Ingredients, TagKey<Item>s or ItemLikes.
                // Overloads also exist that additionally accept a count, adding the same ingredient multiple times.
                .requires(Blocks.BROWN_MUSHROOM, 2)
                .requires(Blocks.RED_MUSHROOM)
                .requires(Items.BOWL)
                // Creates the recipe advancement. While not mandated by the consuming background systems,
                // the recipe builder will crash if you omit this. The first parameter is the advancement name,
                // and the second one is the condition. Normally, you want to use the has() shortcut for the condition.
                // Multiple advancement requirements can be added by calling #unlockedBy multiple times.
                .unlockedBy("has_mushroom_stew", has(Items.MUSHROOM_STEW))
                .unlockedBy("has_bowl", has(Items.BOWL))
                .unlockedBy("has_brown_mushroom", has(Blocks.BROWN_MUSHROOM))
                .unlockedBy("has_red_mushroom", has(Blocks.RED_MUSHROOM))
                // Stores the recipe in the passed RecipeOutput, to be written to disk.
                // If you want to add conditions to the recipe, those can be set on the output.
                .save(recipeOutput);
    }

    protected static <T extends AbstractCookingRecipe> void cookRecipes(
            RecipeOutput recipeOutput, String cookingMethod, RecipeSerializer<T> cookingSerializer, AbstractCookingRecipe.Factory<T> recipeFactory, int cookingTime
    ) {
//        simpleCookingRecipe(recipeOutput, cookingMethod, cookingSerializer, recipeFactory, cookingTime, ModItems.BREAD_SWORD, ModItems.BREAD_SWORD_HOT, 0.35F);
//        simpleCookingRecipe(recipeOutput, cookingMethod, cookingSerializer, recipeFactory, cookingTime, ModItems.BREAD_SWORD_HOT, ModItems.BREAD_SWORD_VERY_HOT, 0.35F);
//

    }

    protected static <T extends AbstractCookingRecipe> void simpleCookingRecipe(
            RecipeOutput recipeOutput,
            String cookingMethod,
            RecipeSerializer<T> cookingSerializer,
            AbstractCookingRecipe.Factory<T> recipeFactory,
            int cookingTime,
            ItemLike material,
            ItemLike result,
            float experience
    ) {
        SimpleCookingRecipeBuilder.generic(Ingredient.of(material), RecipeCategory.FOOD, result, experience, cookingTime, cookingSerializer, recipeFactory)
                .unlockedBy(getHasName(material), has(material))
                .save(recipeOutput, ResourceLocation.fromNamespaceAndPath(MODID,getItemName(result) + "_from_" + cookingMethod));
    }
}
