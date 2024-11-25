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
import rhymestudio.rhyme.registry.items.ArmorItems;
import rhymestudio.rhyme.registry.items.MaterialItems;

import java.util.concurrent.CompletableFuture;

import static rhymestudio.rhyme.Rhyme.MODID;

public class ModRecipe extends RecipeProvider {

    public ModRecipe(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ArmorItems.CONE_HELMET.get())
                .pattern(" A ")
                .pattern(" A ")
                .pattern("AAA")
                .define('A',Items.TERRACOTTA)
                .unlockedBy("has_terracotta",has(Items.TERRACOTTA))
                .save(recipeOutput);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ArmorItems.IRON_BUCKET_HELMET.get())
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
