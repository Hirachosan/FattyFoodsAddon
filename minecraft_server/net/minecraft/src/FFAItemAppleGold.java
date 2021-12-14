package net.minecraft.src;

public class FFAItemAppleGold extends ItemAppleGold
{

	public FFAItemAppleGold(int par1, int par2, float par3, boolean par4) {
		super(par1, par2, par3, par4);

	}
	
	@Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (itemStack.getItemDamage() > 0)
        {
            if (!world.isRemote)
            {
            	player.addPotionEffect(new PotionEffect(FFADefs.FFAflight.id, 60*20));
            }
        }
        else
        {
            super.onFoodEaten(itemStack, world, player);
        }
    }

}
