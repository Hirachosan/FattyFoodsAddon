package net.minecraft.src;

public class FoodStats
{
    /** The player's food level. */
	// FCMOD: Code change to increase food meter resolution
	/*
    private int foodLevel = 20;
    */
    private int foodLevel = 60;
    // END FCMOD

    /** The player's food saturation. */
    //FFA Re-enabled fat at start
    private float foodSaturationLevel = 4.0F;


    /** The player's food exhaustion. */
    private float foodExhaustionLevel;

    /** The player's food timer value. */
    private int foodTimer = 0;
    
	// FCMOD: Code change to increase food meter resolution
	/*
    private int prevFoodLevel = 20;
    */
    private int prevFoodLevel = 60;
    // END FCMOD
    
    //FFA: added stroke as punishment for overeating TODO add colorchange to fat to indicate the severity
    private boolean Stroke = false;
    private int Strokecount = 0;
    //FFA: added for difficulty modes, this sets how much exhaustion gets removed by food (exhaustion cleared per food while nofat)
    private static float BaseExhaustionCost = 0.8F;
//    having fat means hunger drain is slowed by this factor (exhaustion cleared per food while having fat)
    private static float FatExhaustionCost = BaseExhaustionCost*2F;
//    amount of fat that gets drained per calculation cycle (per food while having fat)
    private static float FatCost = 0.07F; // 1 shank = 6food, 0.07 is between 1/4 and 1/5 roll per shank
    
    private static String FoodDifficulty = "Default";
    
    public static void SetBaseExhaustionCost(int Difficulty)
    {
    	if (Difficulty == 0) { BaseExhaustionCost = 1.2F; FoodDifficulty = "Chillmode";}
    	else if (Difficulty == 1) {BaseExhaustionCost = 0.9F; FoodDifficulty = "Easy";}
    	else if (Difficulty == 2) { BaseExhaustionCost = 0.8F; FoodDifficulty = "Default";}
    	else if (Difficulty == 3) {BaseExhaustionCost = 0.7F; FoodDifficulty = "Hardcore starvation";}
    	else {BaseExhaustionCost = 0.7F; FoodDifficulty = "Default";}
    }
    public static  String GetFoodDifficulty()
    {
    	return FoodDifficulty;
    }

    /**
     * Eat some food.
     */
    public void addStats(ItemFood par1ItemFood)
    {
    	// FCMOD: Code change
    	/*
        this.addStats(par1ItemFood.getHealAmount(), par1ItemFood.getSaturationModifier());
        */
        this.addStats(par1ItemFood.GetHungerRestored(), par1ItemFood.getSaturationModifier());
        // END FCMOD
    }

    /**
     * Reads food stats from an NBT object.
     */
    public void readNBT(NBTTagCompound par1NBTTagCompound)
    {
        if (par1NBTTagCompound.hasKey("foodLevel"))
        {
            this.foodLevel = par1NBTTagCompound.getInteger("foodLevel");
            this.foodTimer = par1NBTTagCompound.getInteger("foodTickTimer");
            this.foodSaturationLevel = par1NBTTagCompound.getFloat("foodSaturationLevel");
            this.foodExhaustionLevel = par1NBTTagCompound.getFloat("foodExhaustionLevel");
            
            // FCMOD: Code added
            if ( !par1NBTTagCompound.hasKey("fcFoodLevelAdjusted"))
            {
            	foodLevel = foodLevel * 3;
            	foodSaturationLevel = 0F;
            }
            
            // sanity check the values as apparently they can get fucked up when importing from vanilla
            
            if ( foodLevel > 60 || foodLevel < 0 )
            {
            	foodLevel = 60;
            }
            
            if ( foodSaturationLevel > 20F || foodSaturationLevel < 0F )
            {
            	foodSaturationLevel = 20F;
            }
            // END FCMOD
        }
    }

    /**
     * Writes food stats to an NBT object.
     */
    public void writeNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("foodLevel", this.foodLevel);
        par1NBTTagCompound.setInteger("foodTickTimer", this.foodTimer);
        par1NBTTagCompound.setFloat("foodSaturationLevel", this.foodSaturationLevel);
        par1NBTTagCompound.setFloat("foodExhaustionLevel", this.foodExhaustionLevel);
        
        // FCMOD: Code added
        par1NBTTagCompound.setBoolean( "fcFoodLevelAdjusted", true );
        // END FCMOD
    }

    /**
     * Get the player's food level.
     */
    public int getFoodLevel()
    {
        return this.foodLevel;
    }

    public int getPrevFoodLevel()
    {
        return this.prevFoodLevel;
    }

    /**
     * If foodLevel is not max.
     */
    public boolean needFood()
    {
    	// FCMOD: Code changed
    	/*
        return this.foodLevel < 20;
        */
        return this.foodLevel < 60;    	
        // END FCMOD
    }

    /**
     * adds input to foodExhaustionLevel to a max of 40
     */
    public void addExhaustion(float par1)
    {
        this.foodExhaustionLevel = Math.min(this.foodExhaustionLevel + par1, 40.0F);        
    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel()
    {
        return this.foodSaturationLevel;
    }

    public void setFoodLevel(int par1)
    {
        this.foodLevel = par1;
    }

    public void setFoodSaturationLevel(float par1)
    {
        this.foodSaturationLevel = par1;
    }
     
    //FFA: added new food+fat gain, no longer needs full hunger for fat to be gained
    public void addStats(int iFoodGain, float fFatMultiplier)
    {
    	

        foodLevel = Math.min(iFoodGain + foodLevel, 60);

        foodSaturationLevel = Math.min(fFatMultiplier + foodSaturationLevel, 20.0F);
        if (foodSaturationLevel == 20.0F)
        {
        	Stroke = true;
        }


    }
    
    
    //FFA: added new checks for hungerupdates ()
    public void onUpdate(EntityPlayer par1EntityPlayer)
    {
        int var2 = par1EntityPlayer.worldObj.difficultySetting;
        prevFoodLevel = foodLevel;

        if (var2 > 0)
        {


        	while (foodLevel > 0 && foodExhaustionLevel > FatExhaustionCost)
            {
//           	vanilla BTW base exhaustion would have been 1. (now standardmode=0.8 so 20% faster drain)
            	if (foodSaturationLevel <= 0F) 
            	{
                	foodExhaustionLevel -= BaseExhaustionCost;
                	foodLevel = Math.max(foodLevel - 1, 0);
            	}
            	else 
            	{

            		foodExhaustionLevel -= FatExhaustionCost; 
            		foodLevel = Math.max(foodLevel - 1, 0);  
//            		speed at which fat drains
            		foodSaturationLevel = Math.max(foodSaturationLevel - (FatCost), 0.0F);

            	}
      
            }
        }
                        
        else
        {
            this.foodExhaustionLevel = 0.0F;
        }
        //added reduced healtime for good fat levels
        if (this.Stroke == true)
        {
            if (var2 > 0)
            {
            	++this.Strokecount;
            	int strokedamage = this.Strokecount*this.Strokecount;
            	//TODO set color/texture for fat change
                par1EntityPlayer.attackEntityFrom(FFADefs.FFAthick, strokedamage);
                par1EntityPlayer.addPotionEffect(new PotionEffect(Potion.confusion.id, this.Strokecount*100, 0, true));
            }
            this.Stroke = false;
        }
        if (Strokecount >0 && foodSaturationLevel<12F) //reset strokecount when below statuseffects
        {
        	Strokecount=0;
        }
        	
        
        
        if (this.foodLevel > 24 && par1EntityPlayer.shouldHeal())
        {
        	if(this.foodSaturationLevel >= 12F)
        	{
        		++this.foodTimer;
            	int balancelevel = ((int)this.foodSaturationLevel -12);
            	int healtime = 300 + 75 * balancelevel;

            	if (this.foodTimer >= healtime)
            	{
                	par1EntityPlayer.heal(1);
                	this.foodTimer = 0;

            	}
        	}
            
        	else
        		{
        		++this.foodTimer;
        		int balancelevel = ((int)this.foodSaturationLevel -12);
        		int healtime = 300 - 25 * balancelevel;

        		if (this.foodTimer >= healtime)
        		{
        			par1EntityPlayer.heal(1);
        			this.foodTimer = 0;
        		}
        		}
        }
        //removed check for saturation
        else if (this.foodLevel <= 0 )
        {
            ++this.foodTimer;

            if (this.foodTimer >= 80)
            {
                if (var2 > 0)
                {
                    par1EntityPlayer.attackEntityFrom(DamageSource.starve, 1);
                }

                this.foodTimer = 0;
            }

            this.foodExhaustionLevel = 0.0F;
        }
        else
        {
            this.foodTimer = 0;
        }
    }
}
