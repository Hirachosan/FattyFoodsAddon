package net.minecraft.src;

public class FFASugarExtraction extends FCItemCraftingProgressive
{
    public static final int m_iWickerWeavingMaxDamage = 300;

    public FFASugarExtraction(int var1)
    {
        super(var1);
        this.SetBuoyant();
        this.SetBellowsBlowDistance(2);
        this.SetIncineratedInCrucible();
        this.SetFurnaceBurnTime(FCEnumFurnaceBurnTime.WICKER_PIECE);
        this.SetFilterableProperties(16);
        this.setUnlocalizedName("sugar_stone");
    }

    protected void PlayCraftingFX(ItemStack var1, World var2, EntityPlayer var3)
    {
        var3.playSound("step.grass", 0.25F + 0.25F * (float)var2.rand.nextInt(2), (var2.rand.nextFloat() - var2.rand.nextFloat()) * 0.25F + 1.75F);
        if (var3.worldObj.isRemote)
        {

                Vec3 var5 = var3.worldObj.getWorldVec3Pool().getVecFromPool(
                		((double)var3.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
                
                var5.rotateAroundX(-var3.rotationPitch * (float)Math.PI / 180.0F);
                var5.rotateAroundY(-var3.rotationYaw * (float)Math.PI / 180.0F);
                
                Vec3 var6 = var3.worldObj.getWorldVec3Pool().getVecFromPool(
                		((double)var3.rand.nextFloat() - 0.5D) * 0.3D,
                		(double)(-var3.rand.nextFloat()) * 0.6D - 0.3D, 0.6D);
                
                var6.rotateAroundX(-var3.rotationPitch * (float)Math.PI / 180.0F);
                var6.rotateAroundY(-var3.rotationYaw * (float)Math.PI / 180.0F);
                
                var6 = var6.addVector(var3.posX, var3.posY + (double)var3.getEyeHeight(), var3.posZ);
                
                var3.worldObj.spawnParticle("iconcrack_" + this.itemID, 
                		var6.xCoord, var6.yCoord, var6.zCoord, 
                		var5.xCoord, var5.yCoord + 0.05D, var5.zCoord);
            
        }
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        var2.playSoundAtEntity(var3, "step.grass", 1.0F, var2.rand.nextFloat() * 0.1F + 0.9F);
        return new ItemStack(Item.sugar, 1, 0);
     
    }

    /**
     * Called when item is crafted/smelted. Used only by maps so far.
     */
    public void onCreated(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (var3.m_iTimesCraftedThisTick == 0 && var2.isRemote)
        {
            var3.playSound("step.grass", 1.0F, var2.rand.nextFloat() * 0.1F + 0.9F);
        }

        super.onCreated(var1, var2, var3);
    }

    public boolean GetCanBeFedDirectlyIntoCampfire(int var1)
    {
        return false;
    }

    public boolean GetCanBeFedDirectlyIntoBrickOven(int var1)
    {
        return false;
    }
    protected void PerformUseEffects(EntityPlayer var1)
    {
        var1.playSound("random.eat", 0.5F + 0.5F * (float)var1.rand.nextInt(2), var1.rand.nextFloat() * 0.25F + 1.75F);

        if (var1.worldObj.isRemote)
        {

                Vec3 var3 = var1.worldObj.getWorldVec3Pool().getVecFromPool(((double)var1.rand.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, 0.0D);
                var3.rotateAroundX(-var1.rotationPitch * (float)Math.PI / 180.0F);
                var3.rotateAroundY(-var1.rotationYaw * (float)Math.PI / 180.0F);
                Vec3 var4 = var1.worldObj.getWorldVec3Pool().getVecFromPool(((double)var1.rand.nextFloat() - 0.5D) * 0.3D, (double)(-var1.rand.nextFloat()) * 0.6D - 0.3D, 0.6D);
                var4.rotateAroundX(-var1.rotationPitch * (float)Math.PI / 180.0F);
                var4.rotateAroundY(-var1.rotationYaw * (float)Math.PI / 180.0F);
                var4 = var4.addVector(var1.posX, var1.posY + (double)var1.getEyeHeight(), var1.posZ);
                var1.worldObj.spawnParticle("iconcrack_" + this.itemID, var4.xCoord, var4.yCoord, var4.zCoord, var3.xCoord, var3.yCoord + 0.05D, var3.zCoord);
            
        }
    }

    
    protected int GetProgressiveCraftingMaxDamage()
    {
        return 400;
    }
}
