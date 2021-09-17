package net.minecraft.src;

public class FFAItemBucketMilk extends FCItemBucketDrinkable
{
    public FFAItemBucketMilk(int ID, int healvalue, float fatvalue, String unlocalizedname)
    {
        super(ID, healvalue, fatvalue);
        this.setUnlocalizedName(unlocalizedname);
    }

    /**
     * Hiracho: currently holds blockID for chocolate and regular milk, safety check shows empty bucket
     */
    public int getBlockID()
    {
        if (this.itemID == Item.bucketMilk.itemID)
        	return FCBetterThanWolves.fcBlockBucketMilk.blockID;
        else if (this.itemID == FCBetterThanWolves.fcItemBucketMilkChocolate.itemID)
            return FCBetterThanWolves.fcBlockBucketMilkChocolate.blockID;
        else return FCBetterThanWolves.fcBlockBucketEmpty.blockID;
    }

    public ItemStack onEaten(ItemStack var1, World var2, EntityPlayer var3)
    {
        if (!var2.isRemote)
        {
            var3.clearActivePotions();
        }

        return super.onEaten(var1, var2, var3);
    }
    public boolean DoesConsumeContainerItemWhenCrafted(Item var1)
    {
        return var1.itemID == Item.bucketEmpty.itemID;
    }
}
