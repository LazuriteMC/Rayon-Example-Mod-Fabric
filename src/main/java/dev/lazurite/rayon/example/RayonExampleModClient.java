package dev.lazurite.rayon.example;

import dev.lazurite.rayon.example.render.StoneBlockEntityModel;
import dev.lazurite.rayon.example.render.StoneBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class RayonExampleModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(RayonExampleMod.STONE_BLOCK_ENTITY, (context) -> new StoneBlockEntityRenderer(context, new StoneBlockEntityModel(12, 4, 12)));
    }

}
