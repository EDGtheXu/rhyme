package rhymestudio.rhyme.datagen.loot;


import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.BinomialDistributionGenerator;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredHolder;
import rhymestudio.rhyme.core.registry.entities.Zombies;
import rhymestudio.rhyme.core.registry.items.ArmorItems;
import rhymestudio.rhyme.core.registry.items.MaterialItems;

import java.util.stream.Stream;

public class ModEntityLootProvider extends EntityLootSubProvider {
    public ModEntityLootProvider( HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }



    @Override
    public void generate() {

        this.add(Zombies.NORMAL_ZOMBIE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(BinomialDistributionGenerator.binomial(1, 0.75F))
                        .add(LootItem.lootTableItem(MaterialItems.GENERAL_SEED)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(BinomialDistributionGenerator.binomial(1, 0.5F))
                        .add(LootItem.lootTableItem(MaterialItems.PLANT_GENE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F)))
                .withPool(LootPool.lootPool()
                        .setRolls(BinomialDistributionGenerator.binomial(1, 0.3F))
                        .add(LootItem.lootTableItem(MaterialItems.PEA_GENE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
                        .when(LootItemRandomChanceCondition.randomChance(0.5F))));

        this.add(Zombies.CONE_ZOMBIE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(BinomialDistributionGenerator.binomial(1, 0.1F))
                        .add(LootItem.lootTableItem(ArmorItems.CONE_HELMET)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())));

        this.add(Zombies.IRON_BUCKET_ZOMBIE.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(BinomialDistributionGenerator.binomial(1, 0.1F))
                        .add(LootItem.lootTableItem(ArmorItems.IRON_BUCKET_HELMET)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())));

/*
        this.add(EntityType.BOGGED, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.ARROW)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.BONE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.TIPPED_ARROW)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))
                                        .setLimit(1))
                                .apply(SetPotionFunction.setPotion(Potions.POISON)))
                        .when(LootItemKilledByPlayerCondition.killedByPlayer())));


*/


//        this.add(ModEntities.KE_YAN.get(), LootTable.lootTable()
//                .withPool(LootPool.lootPool()
//                        .setRolls(ConstantValue.exactly(1.0F))
//                        .add(LootItem.lootTableItem(ModItems.XING_HONG_DING)
//                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(8.0F, 10.0F)))
//                                .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F)))
//                                .when(LootItemKilledByPlayerCondition.killedByPlayer()))));


    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return Zombies.ZOMBIES.getEntries().stream().map(DeferredHolder::get);
    }
}
