package net.minecraft.src;

public class FFABlockcake extends FCBlockCake
{
    public FFABlockcake(int ID)
    {
        super(ID);
        this.setStepSound(soundClothFootstep);
        this.setUnlocalizedName("cake");
        this.disableStats();
        this.setHardness(0.5F);
		
    }
    @Override
    public boolean onBlockActivated( World world, int i, int j, int k, EntityPlayer player, int iFacing, float fXClick, float fYClick, float fZClick )
    {
        EatCakeSliceLocal( world, i, j, k, player );
        
        return true;
    }
    
    public void EatCakeSliceLocal(World var1, int var2, int var3, int var4, EntityPlayer var5)
    {
        if (var5.canEat(true))
        {
            var5.getFoodStats().addStats(1, 5.0F);

            var5.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 600, 2));
            int var6 = this.GetEatState(var1, var2, var3, var4) + 1;

            if (var6 >= 6)
            {
                var1.setBlockWithNotify(var2, var3, var4, 0);
            }
            else
            {
                this.SetEatState(var1, var2, var3, var4, var6);
            }
        }
        else
        {
            var5.OnCantConsume();
        }
    }
    
    
    
    
}
