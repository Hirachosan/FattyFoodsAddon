package net.minecraft.src;

public class FFApotion extends Potion
{
	//TODO add lang file
    private int potionTimer = 0;
	public FFApotion(int ID, boolean effectiveness, int Color) {
		super(ID, effectiveness, Color);

	}
    public void performEffect(EntityLiving par1EntityLiving, int par2)
    {
    	super.performEffect(par1EntityLiving, par2);
        if (this.id == FFADefs.FFAslowregen.id)
        {
            if (par1EntityLiving.getHealth() < par1EntityLiving.getMaxHealth())
            {
                par1EntityLiving.heal(1);
            }
        }
    }
    /**
     * checks if Potion effect is ready to be applied this tick.
     */
    public boolean isReady(int par1, int par2)
    {
        int var3;

            var3 = 400 >> par2;
            return var3 > 0 ? par1 % var3 == 0 : true;
    }
}
	
	

