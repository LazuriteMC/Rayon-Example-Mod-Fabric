package dev.lazurite.rayon.example;

import dev.lazurite.rayon.api.event.collision.ElementCollisionEvents;
import dev.lazurite.rayon.example.entity.StoneBlockEntity;
import dev.lazurite.rayon.example.item.WandItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RayonExampleMod implements ModInitializer {

    public static final String MODID = "rayon-example-mod-fabric";
    public static final Logger LOGGER = LogManager.getLogger("Rayon Example Mod");

    public static EntityType<StoneBlockEntity> STONE_BLOCK_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MODID, "stone_block_entity"),
                FabricEntityTypeBuilder.createLiving()
                        .entityFactory(StoneBlockEntity::new)
                        .spawnGroup(SpawnGroup.MISC)
                        .defaultAttributes(LivingEntity::createLivingAttributes)
                        .dimensions(EntityDimensions.fixed(0.75f, 0.25f))
                        .trackRangeBlocks(80)
                        .build()
        );

    public static WandItem WAND_ITEM = Registry.register(Registries.ITEM, new Identifier(MODID, "wand_item"), new WandItem(new Item.Settings().maxCount(1)));

    @Override
    public void onInitialize() {
        /* An example of a block collision event */
        ElementCollisionEvents.BLOCK_COLLISION.register((element, terrainObject, impulse) -> {
            if (element instanceof StoneBlockEntity) {
                if (terrainObject.getBlockState().getBlock().equals(Blocks.BRICKS)) {
                    LOGGER.info("Touching bricks!!" + impulse);
                    ((StoneBlockEntity) element).kill();
                }
            }
        });

        // Item group registration
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> content.add(WAND_ITEM));
    }

}