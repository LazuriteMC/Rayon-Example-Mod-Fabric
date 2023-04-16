package dev.lazurite.rayon.example.entity;

import dev.lazurite.rayon.api.EntityPhysicsElement;
import dev.lazurite.rayon.impl.bullet.collision.body.EntityRigidBody;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

import java.util.List;

/**
 * A stone block entity that uses an internally stored {@link EntityRigidBody} to behave using realistic physics.
 */
public class StoneBlockEntity extends LivingEntity implements EntityPhysicsElement {

    private final EntityRigidBody rigidBody;

    public StoneBlockEntity(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
        this.rigidBody = new EntityRigidBody(this);
        this.rigidBody.setMass(20.0f); // 20 kg
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return List.of();
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return new ItemStack(Items.AIR);
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {
    }

    @Override
    public Arm getMainArm() {
        return null;
    }

    @Override
    public boolean isSilent() {
        return true;
    }

    @Override
    public EntityRigidBody getRigidBody() {
        return this.rigidBody;
    }

}