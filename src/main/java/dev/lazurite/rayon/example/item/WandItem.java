package dev.lazurite.rayon.example.item;

import dev.lazurite.rayon.example.RayonExampleMod;
import dev.lazurite.rayon.example.entity.StoneBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.Objects;

/**
 * A test item that spawns a {@link StoneBlockEntity}.
 */
public class WandItem extends Item {

    public WandItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var itemStack = user.getStackInHand(hand);
        var hitResult = raycast(world, user, RaycastContext.FluidHandling.NONE);

        if (!world.isClient()) {
            var entity = Objects.requireNonNull(RayonExampleMod.STONE_BLOCK_ENTITY.create(world));
            entity.setPosition(hitResult.getPos());
            entity.resetPosition();
            world.spawnEntity(entity);
            return TypedActionResult.success(itemStack);
        }

        return TypedActionResult.pass(itemStack);
    }

}