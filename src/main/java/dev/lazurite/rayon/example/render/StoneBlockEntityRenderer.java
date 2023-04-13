package dev.lazurite.rayon.example.render;

import com.jme3.math.Quaternion;
import dev.lazurite.rayon.example.RayonExampleMod;
import dev.lazurite.rayon.example.entity.StoneBlockEntity;
import dev.lazurite.rayon.impl.bullet.math.Convert;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class StoneBlockEntityRenderer extends EntityRenderer<StoneBlockEntity> {

    private static final Identifier texture = new Identifier(RayonExampleMod.MODID, "textures/entity/stone_block.png");
    private final StoneBlockEntityModel model;

    public StoneBlockEntityRenderer(EntityRendererFactory.Context ctx, StoneBlockEntityModel model) {
        super(ctx);
        this.model = model;
        this.shadowRadius = 0.2F;
    }

    public void render(StoneBlockEntity cubeEntity, float yaw, float delta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        var rot = Convert.toMinecraft(cubeEntity.getPhysicsRotation(new Quaternion(), delta));
        var box = cubeEntity.getBoundingBox();

        matrixStack.push();
        matrixStack.multiply(rot);
        matrixStack.translate(box.getXLength() * -0.5, box.getYLength() * -0.5, box.getZLength() * -0.5);

        final var vertexConsumer = vertexConsumerProvider.getBuffer(model.getLayer(getTexture(cubeEntity)));
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStack.pop();

        super.render(cubeEntity, yaw, delta, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public boolean shouldRender(StoneBlockEntity entity, Frustum frustum, double d, double e, double f) {
        return true;
    }

    @Override
    public Identifier getTexture(StoneBlockEntity entity) {
        return this.texture;
    }

}