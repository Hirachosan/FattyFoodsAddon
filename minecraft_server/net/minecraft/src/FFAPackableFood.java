package net.minecraft.src;

public class FFAPackableFood extends FCItemFood{

	
	
    public FFAPackableFood(int var1, int foodvalue, float fatvalue, boolean wolffood, String nonlocalname)
    {
        super(var1, foodvalue, fatvalue, wolffood, nonlocalname);

    }

    public void OnUsedInCrafting(EntityPlayer var1, ItemStack var2)
    {
        if (var1.m_iTimesCraftedThisTick == 0)
        {
            var1.playSound("mob.slime.attack", 0.5F, (var1.worldObj.rand.nextFloat() - var1.worldObj.rand.nextFloat()) * 0.1F + 0.7F);
        }
    }

    public boolean IsPistonPackable(ItemStack var1)
    {
        return true;
    }

    public int GetRequiredItemCountToPistonPack(ItemStack var1)
    {
    	if (this.itemID == FCBetterThanWolves.fcItemCreeperOysters.itemID)
        return 16;
    	else if  (this.itemID == Item.rottenFlesh.itemID)
        return 9;
    	else return 9;
    }

    public int GetResultingBlockIDOnPistonPack(ItemStack var1)
    {
    	if (this.itemID == FCBetterThanWolves.fcItemCreeperOysters.itemID)
    	    return FCBetterThanWolves.fcBlockCreeperOysters.blockID;

        else if  (this.itemID == Item.rottenFlesh.itemID)
        	return FCBetterThanWolves.fcBlockRottenFlesh.blockID;
        else return Block.dirt.blockID;

    }
	
}
