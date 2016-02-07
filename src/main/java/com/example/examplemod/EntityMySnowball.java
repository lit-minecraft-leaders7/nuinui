package com.example.examplemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnowBlock;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

/**
 * Created by nui on 16/02/07.
 */
public class EntityMySnowball extends EntitySnowball {
    private float damage = 2;

    public EntityMySnowball(World world) {
        super(world);
    }

    public EntityMySnowball(World world, EntityLivingBase entity) {
        super(world, entity);
    }

    public EntityMySnowball(World world, double x, double y, double z) {
        super(world, x, y, z);
    }

    @Override
    protected void onImpact(MovingObjectPosition position) {
        if (position.entityHit != null) {
            position.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
        }
        if (position.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            switch (position.sideHit) {
                case 2:
                case 3:
                case 4:
                case 5:
                    Block block = worldObj.getBlock(position.blockX, position.blockY, position.blockZ);
                    if (block instanceof BlockSnowBlock) {
                        worldObj.setBlock(position.blockX, position.blockY, position.blockZ, Blocks.air);
                    }
            }
        }
        for (int i = 0; i < 8; i++) {
            this.worldObj.spawnParticle("snowballpoof", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        if (!this.worldObj.isRemote) {
            this.setDead();
        }
    }
}
