package net.minecraft.src;

public class FFAItemFood extends FCItemFood {
	//Hiracho: food types that use special register icons
    public FFAItemFood(int var1, int var2, float var3, boolean var4, String var5)
    {

    	super(var1, var2, var3, var4, var5);

    }

    public FFAItemFood(int var1, int var2, float var3, boolean var4, String var5, boolean var6)
    {
        super(var1, var2, var3, var4, var5, var6);


    }
     
	    public void registerIcons(IconRegister var1)
	    {
	    	if (this.itemID == FCBetterThanWolves.fcItemWolfRaw.itemID)
	        this.itemIcon = var1.registerIcon("porkchopRaw");
	    	else if (this.itemID == FCBetterThanWolves.fcItemWolfCooked.itemID)
	        this.itemIcon = var1.registerIcon("porkchopCooked");
	    	else if (this.itemID == FCBetterThanWolves.fcItemRawMysteryMeat.itemID)
	        this.itemIcon = var1.registerIcon("beefRaw");
	    	else if (this.itemID == FCBetterThanWolves.fcItemCookedMysteryMeat.itemID)
	        this.itemIcon = var1.registerIcon("beefCooked");
	        //TODO make it check the super registericons
	        else this.itemIcon = var1.registerIcon("fcItemCreeperOysters");
	}
}