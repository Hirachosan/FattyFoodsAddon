package net.minecraft.src;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.MinecraftServer;

public class FFADefs {
	public static final FFADefs instance = new FFADefs();
	
	private static final int
			id_sugarExtraction = 27001;

    public static Item sugarExtraction;
    private static HiraFatFoodAddon fatFoodAddon;
    
    
	private static final int
//			id_potionFFAheal= 21,
			id_potionFFAslowregen= 22,
			id_potionFFAslowfall= 23,
			id_potionFFAextraarmor= 24,
			id_potionFFAflight= 25;
		
	
//    public static Potion FFAheal;
    public static Potion FFAslowregen;
    public static Potion FFAslowfall;
    public static Potion FFAextraarmor;
    public static Potion FFAflight;

    
    public static DamageSource FFAthick;
	
    
    public static void FFAAddNewItems() 	
	{
		sugarExtraction = new FFASugarExtraction(id_sugarExtraction - 256);
	}
	public static void FFAAddNewRecipes() 	
	{
		FCRecipes.AddShapelessRecipe(new ItemStack(FFADefs.sugarExtraction, 1, 399), new Object[] {Item.reed, FCBetterThanWolves.fcItemChiselStone});
	}

	public static void FFAAddNewPotions() 
	{
		//TODO set color
	//	FFAheal =  (new FFApotion(id_potionFFAheal, false, 16269179)).setPotionName("potion.fatheal");
		FFAslowregen =  (new FFApotion(id_potionFFAslowregen, false, 16740429)).setPotionName("potion.slowregen");
		FFAslowfall =  (new FFApotion(id_potionFFAslowfall, false, 16776658)).setPotionName("potion.slowfall");
		FFAextraarmor = (new FFApotion(id_potionFFAextraarmor, false, 5855577)).setPotionName("potion.extraarmor");
		FFAflight = (new FFApotion(id_potionFFAflight, false, 6809599)).setPotionName("potion.flight");

	}
	
	public static void FFAAddNewDamageType()
	{
    	FFAthick = (new DamageSource("thick")).setDamageBypassesArmor();
	}
	public static void FFAReplaceItems() 
	{
		/*
		 * 3 food types (maybe 4th ?)
		 * low/no fat high food  	= chicken mutton vegetables
		 * equal fat food			= beef mystery meat
		 * high fat low/no food 	= pork wolf
		 *  
		 *  after .class int:1= half shank / float 1 = .5 roll
		 */
		
		// Meats
		Item.porkRaw = ((FCItemFood) Item.replaceItem(
				Item.porkRaw.itemID, FCItemFood.class, fatFoodAddon, 1, 2.0F, true, "porkchopRaw", true))
				.SetMediumFoodPoisoningEffect();
		
		Item.porkCooked = Item.replaceItem(
				Item.porkCooked.itemID, FCItemFood.class, fatFoodAddon, 3, 2.0F, true, "porkchopCooked");
		
		Item.chickenRaw = ((FCItemFood) Item.replaceItem(
				Item.chickenRaw.itemID, FCItemFood.class, fatFoodAddon, 3, 0.125F, true, "chickenRaw", true))
				.SetMediumFoodPoisoningEffect();
		
		Item.chickenCooked = Item.replaceItem(
				Item.chickenCooked.itemID, FCItemFood.class, fatFoodAddon, 5, 0.125F, true, "chickenCooked");
		
		Item.beefRaw = ((FCItemFood) Item.replaceItem(
				Item.beefRaw.itemID, FCItemFood.class, fatFoodAddon, 3, 1.0F, true, "beefRaw", true))
				.SetLowFoodPoisoningEffect();
		
		Item.beefCooked = Item.replaceItem(
				Item.beefCooked.itemID, FCItemFood.class, fatFoodAddon, 5, 1.0F, true, "beefCooked");
		
		FCBetterThanWolves.fcItemMuttonRaw = ((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemMuttonRaw.itemID, FCItemFood.class, fatFoodAddon, 3, 0.125F, true, "fcItemMuttonRaw", true))
				.SetLowFoodPoisoningEffect();
		
		FCBetterThanWolves.fcItemMuttonCooked = Item.replaceItem(
				FCBetterThanWolves.fcItemMuttonCooked.itemID, FCItemFood.class, fatFoodAddon, 5, 0.125F, true, "fcItemMuttonCooked");
		
		FCBetterThanWolves.fcItemMeatCured = Item.replaceItem(
				FCBetterThanWolves.fcItemMeatCured.itemID, FCItemFoodCured.class, fatFoodAddon, 3, 0F, "fcItemMeatCured");
		
		FCBetterThanWolves.fcItemMeatBurned = Item.replaceItem(
				FCBetterThanWolves.fcItemMeatBurned.itemID, FCItemFood.class, fatFoodAddon, 2, 0F, true, "fcItemMeatBurned");
		
		Item.fishRaw = ((FCItemFood) Item.replaceItem(
				Item.fishRaw.itemID, FCItemFood.class, fatFoodAddon, 3, 0.125F, false, "fishRaw"))
				.SetStandardFoodPoisoningEffect();
		
		Item.fishCooked = Item.replaceItem(
				Item.fishCooked.itemID, FCItemFood.class, fatFoodAddon, 5, 0.125F, false, "fishCooked");
		
		// Eggs
		FCBetterThanWolves.fcItemRawEgg = ((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemRawEgg.itemID, FCItemFood.class, fatFoodAddon, 1, 1.0F, false, "fcItemEggRaw"))
				.SetStandardFoodPoisoningEffect().SetFilterableProperties(2);
		
		FCBetterThanWolves.fcItemFriedEgg = ((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemFriedEgg.itemID, FCItemFood.class, fatFoodAddon, 2, 1.0F, false, "fcItemEggFried"))
				.SetFilterableProperties(2);
		
		FCBetterThanWolves.fcItemHardBoiledEgg = ((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemHardBoiledEgg.itemID, FCItemFood.class, fatFoodAddon, 2, 1.0F, false, "fcItemEggPoached"))
				.SetFilterableProperties(2).SetNeutralBuoyant();

		//Vegetables	
		FCBetterThanWolves.fcItemCookedCarrot = Item.replaceItem(
				FCBetterThanWolves.fcItemCookedCarrot.itemID, FCItemFood.class, fatFoodAddon, 3, 0.0F, false, "fcItemCarrotCooked")
				.SetAsBasicPigFood().SetFilterableProperties(2);
		
		FCBetterThanWolves.fcItemBoiledPotato = Item.replaceItem(
				FCBetterThanWolves.fcItemBoiledPotato.itemID, FCItemFood.class, fatFoodAddon, 3, 0.0F, false, "fcItemPotatoBoiled")
				.SetAsBasicPigFood().SetFilterableProperties(2);
		
		Item.bakedPotato = ((FCItemFood) Item.replaceItem(
				Item.bakedPotato.itemID, FCItemFood.class, fatFoodAddon, 3, 0.0F, false, "potatoBaked"))
				.SetAsBasicPigFood().SetFilterableProperties(2);
		
		FCBetterThanWolves.fcItemMushroomRed = ((FCItemMushroom) Item.replaceItem(
				FCBetterThanWolves.fcItemMushroomRed.itemID, FCItemMushroom.class, fatFoodAddon, 1, 0.0F, "fcItemMushroomRed", Block.mushroomRed.blockID))
				.setPotionEffect(Potion.poison.id, 5, 0, 1.0F).SetFilterableProperties(2).setPotionEffect(PotionHelper.spiderEyeEffect);
		
		FCBetterThanWolves.fcItemMushroomBrown = ((FCItemMushroom) Item.replaceItem(
				FCBetterThanWolves.fcItemMushroomBrown.itemID, FCItemMushroom.class, fatFoodAddon, 1, 0.0F, "fcItemMushroomBrown", Block.mushroomBrown.blockID))
				.SetFilterableProperties(2);

		Item.pumpkinSeeds = ((FCItemSeedFood) Item.replaceItem(
				Item.pumpkinSeeds.itemID, FCItemSeedFood.class, fatFoodAddon, 1, 0.0F, Block.pumpkinStem.blockID))
				.SetAsBasicChickenFood().SetBellowsBlowDistance(2).SetFilterableProperties(8).setUnlocalizedName("seeds_pumpkin");
		
		Item.carrot = ((FCItemSeedFood) Item.replaceItem(
				Item.carrot.itemID, FCItemSeedFood.class, fatFoodAddon, 3, 0.0F, Block.carrot.blockID))
				.SetFilterableProperties(2).SetAsBasicPigFood().setUnlocalizedName("carrots");
		
		FCBetterThanWolves.fcItemCarrot = ((FCItemSeedFood) Item.replaceItem(
				FCBetterThanWolves.fcItemCarrot.itemID, FCItemSeedFood.class, fatFoodAddon, 3, 0.0F, FCBetterThanWolves.fcBlockCarrotFlowers.blockID))
				.SetFilterableProperties(Item.m_iFilterable_Small).SetAsBasicPigFood().setUnlocalizedName("fcItemCarrot");
		
		Item.potato = ((FCItemSeedFood) Item.replaceItem(
				Item.potato.itemID, FCItemSeedFood.class, fatFoodAddon, 3, 0.0F, Block.potato.blockID))
				.SetFilterableProperties(2).SetAsBasicPigFood().setUnlocalizedName("potato");

		//new food items
		/*
		 pumpkin soup pumpkin + milk + bowl ? 2x 2.5shank 0.5roll?
		 melon (ham)sandwich -> ham bread melon slice = 3x jump level 1? 60 seconds? 2 roll 2shank
		 Green curry -> pumpkin potato carrot milk bowl
		 add coconut?
		 fcItemTallow = (new Item(ParseID("fcTallowID", 248))).SetBuoyant().SetIncineratedInCrucible().setUnlocalizedName("fcItemTallow").setCreativeTab(CreativeTabs.tabMaterials);

		 */
		
		//delicacy
		FCBetterThanWolves.fcItemBeastLiverRaw = ((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemBeastLiverRaw.itemID, FCItemFood.class, fatFoodAddon, 4, 3.0F, true, "fcItemBeastLiverRaw"))
				.SetStandardFoodPoisoningEffect();
		
		FCBetterThanWolves.fcItemBeastLiverCooked = ((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemBeastLiverCooked.itemID, FCItemFood.class, fatFoodAddon, 6, 3.0F, true, "fcItemBeastLiverCooked"))
				.setPotionEffect(Potion.damageBoost.id, 30, 0, 1.0F);

		//bucketdrinks => doesn't triple food value so x3 in this
		Item.bucketMilk = Item.replaceItem(
				Item.bucketMilk.itemID, FFAItemBucketMilk.class, fatFoodAddon, 6, 1.25F, "milk");
		
		FCBetterThanWolves.fcItemBucketMilkChocolate = Item.replaceItem(
				FCBetterThanWolves.fcItemBucketMilkChocolate.itemID, FFAItemBucketMilk.class, fatFoodAddon, 9, 1.5F, "fcItemBucketChocolateMilk");
	
		// desserts 
		Item.sugar = ((FCItemFood) Item.replaceItem(
				Item.sugar.itemID, FCItemFood.class, fatFoodAddon, 0, 0.5F, false, "sugar"))
				.setPotionEffect(Potion.moveSpeed.id, 5, 1, 1.0F).setAlwaysEdible().SetBellowsBlowDistance(3).SetFilterableProperties(8);
		
		Item.appleRed = ((FCItemFood) Item.replaceItem(
				Item.appleRed.itemID, FCItemFood.class, fatFoodAddon, 1, 1.0F, false, "apple"))
				.setPotionEffect(FFADefs.FFAslowfall.id, 22, 0, 1.0F).setAlwaysEdible().SetAsBasicPigFood().SetFilterableProperties(2);
		
		Item.appleGold = ((ItemAppleGold) Item.replaceItem(
				Item.appleGold.itemID, FFAItemAppleGold.class, fatFoodAddon, 1, 1.0F, false))
				.setAlwaysEdible().setPotionEffect(Potion.regeneration.id, 20, 0, 1.0F)
				.SetNonBuoyant().SetNotIncineratedInCrucible().SetFilterableProperties(2).setUnlocalizedName("appleGold");
		
		Item.melon = ((FCItemFoodHighRes) Item.replaceItem(
				Item.melon.itemID, FCItemFoodHighRes.class, fatFoodAddon, 1, 0.5F, false, "melon"))
				.setPotionEffect(Potion.jump.id, 5, 0, 1.0F).setAlwaysEdible().SetFilterableProperties(2);
		
		FCBetterThanWolves.fcItemMelonMashed = ((FCItemFoodHighRes) Item.replaceItem(
				FCBetterThanWolves.fcItemMelonMashed.itemID, FCItemFoodHighRes.class, fatFoodAddon, 1, 0.5F, false, "fcItemMelonMashed"))
				.setPotionEffect(Potion.jump.id, 5, 0, 1.0F).setAlwaysEdible().SetFilterableProperties(Item.m_iFilterable_Fine);

		FCBetterThanWolves.fcItemCocoaBeans = ((FFAItemCocoaBeans) Item.replaceItem(
				FCBetterThanWolves.fcItemCocoaBeans.itemID, FFAItemCocoaBeans.class, fatFoodAddon, 1, 0.25F, true, "fcItemCocoaBeans"))
				.setPotionEffect(FFADefs.FFAslowregen.id, 42, 0, 1.0F);
		
		Item.cookie = ((FCItemFoodHighRes) Item.replaceItem(
				Item.cookie.itemID, FCItemFoodHighRes.class, fatFoodAddon, 1, 1.75F, false, "cookie"))
				.setPotionEffect(FFADefs.FFAslowregen.id, 82, 0, 1.0F).setAlwaysEdible().SetFilterableProperties(2);
		
		Item.pumpkinPie = ((FCItemFood) Item.replaceItem(
				Item.pumpkinPie.itemID, FCItemFood.class, fatFoodAddon, 1, 4.0F, false, "pumpkinPie"))
				.setPotionEffect(FFADefs.FFAextraarmor.id, 30, 2, 1.0F).setAlwaysEdible();
		
		FCBetterThanWolves.fcItemDonut = ((FCItemFoodHighRes) Item.replaceItem(
				FCBetterThanWolves.fcItemDonut.itemID, FCItemFoodHighRes.class, fatFoodAddon, 1, 1.0F, false, "fcItemDonut"))
				.setPotionEffect(Potion.moveSpeed.id, 12, 1, 1.0F).setAlwaysEdible().SetFilterableProperties(2);
		
		FCBetterThanWolves.fcItemChocolate = ((FCItemFoodHighRes) Item.replaceItem(
				FCBetterThanWolves.fcItemChocolate.itemID, FCItemFoodHighRes.class, fatFoodAddon, 3, 2.5F, true, "fcItemChocolate"))
				.setPotionEffect(FFADefs.FFAslowregen.id, 42, 1, 1.0F).setAlwaysEdible().SetFilterableProperties(2);

		//Cake + block
		Item.cake = ((ItemReed) Item.replaceItem(
				Item.cake.itemID, ItemReed.class, fatFoodAddon, Block.cake)).SetBuoyant().SetIncineratedInCrucible().setMaxStackSize(16).setUnlocalizedName("cake").setCreativeTab(CreativeTabs.tabFood);
		Block.cake = Block.replaceBlock(
				Block.cake.blockID, FFABlockcake.class, fatFoodAddon);

	
		//special case icon things go to FFAItemFood => add specific icon there
		FCBetterThanWolves.fcItemWolfRaw = ((FFAItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemWolfRaw.itemID, FFAItemFood.class, fatFoodAddon, 2, 4.0F, false, "fcItemWolfChopRaw", true))
				.SetMediumFoodPoisoningEffect();
		
		FCBetterThanWolves.fcItemWolfCooked = Item.replaceItem(
				FCBetterThanWolves.fcItemWolfCooked.itemID, FFAItemFood.class, fatFoodAddon, 3, 4.0F, false, "fcItemWolfChopCooked");
		
		FCBetterThanWolves.fcItemRawMysteryMeat = ((FFAItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemRawMysteryMeat.itemID, FFAItemFood.class, fatFoodAddon, 5, 2.0F, true, "fcItemMysteryMeatRaw", true))
				.SetStandardFoodPoisoningEffect();
		
		FCBetterThanWolves.fcItemCookedMysteryMeat = Item.replaceItem(
				FCBetterThanWolves.fcItemCookedMysteryMeat.itemID, FFAItemFood.class, fatFoodAddon, 6, 2.0F, true, "fcItemMysteryMeatCooked");

		//Food Recipes
		Item.bread = Item.replaceItem(
				Item.bread.itemID, FCItemFood.class, fatFoodAddon, 3, 0.0F, false, "bread");
		
		FCBetterThanWolves.fcItemHamAndEggs = Item.replaceItem(
				FCBetterThanWolves.fcItemHamAndEggs.itemID, FCItemFood.class, fatFoodAddon, 4, 4.5F, true, "fcItemHamAndEggs"); 
		
		FCBetterThanWolves.fcItemTastySandwich = Item.replaceItem(
				FCBetterThanWolves.fcItemTastySandwich.itemID, FCItemFood.class, fatFoodAddon, 6, 0.125F, true, "fcItemSandwichTasty");
		
		FCBetterThanWolves.fcItemSteakAndPotatoes = Item.replaceItem(
				FCBetterThanWolves.fcItemSteakAndPotatoes.itemID, FCItemFood.class, fatFoodAddon, 6, 2.0F, true, "fcItemSteakAndPotatoes");
		
		FCBetterThanWolves.fcItemSteakDinner = Item.replaceItem(
				FCBetterThanWolves.fcItemSteakDinner.itemID, FCItemFood.class, fatFoodAddon, 8, 2.0F, true, "fcItemDinnerSteak");
		
		FCBetterThanWolves.fcItemPorkDinner = Item.replaceItem(
				FCBetterThanWolves.fcItemPorkDinner.itemID, FCItemFood.class, fatFoodAddon, 5, 5.0F, true, "fcItemDinnerPork");
		
		FCBetterThanWolves.fcItemWolfDinner = Item.replaceItem(
				FCBetterThanWolves.fcItemWolfDinner.itemID, FCItemFood.class, fatFoodAddon, 5, 5.0F, true, "fcItemDinnerWolf");
		
		FCBetterThanWolves.fcItemRawKebab = ((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemRawKebab.itemID, FCItemFood.class, fatFoodAddon, 8, 0.125F, true, "fcItemKebabRaw"))
				.SetStandardFoodPoisoningEffect();
		
		FCBetterThanWolves.fcItemCookedKebab = Item.replaceItem(
				FCBetterThanWolves.fcItemCookedKebab.itemID, FCItemFood.class, fatFoodAddon, 10, 0.125F, true, "fcItemKebabCooked");
		
		FCBetterThanWolves.fcItemChickenSoup = Item.replaceItem(
				FCBetterThanWolves.fcItemChickenSoup.itemID, FCItemSoup.class, fatFoodAddon, 10, 0.125F, true, "fcItemSoupChicken");
		
		FCBetterThanWolves.fcItemFishSoup = Item.replaceItem(
				FCBetterThanWolves.fcItemFishSoup.itemID, FCItemSoup.class, fatFoodAddon, 6, 0.5F, false, "fcItemChowder");
		
		FCBetterThanWolves.fcItemHeartyStew = Item.replaceItem(
				FCBetterThanWolves.fcItemHeartyStew.itemID, FCItemSoup.class, fatFoodAddon, 12, 0.5F, true, "fcItemStewHearty");
		
		Item.bowlSoup = Item.replaceItem(
				Item.bowlSoup.itemID, FCItemSoup.class, fatFoodAddon, 3, 0.5F, true, "mushroomStew");

		FCBetterThanWolves.fcItemRawMushroomOmelet = ((FCItemFood)Item.replaceItem(
				FCBetterThanWolves.fcItemRawMushroomOmelet.itemID, FCItemFood.class, fatFoodAddon, 4, 1.0F, false, "fcItemMushroomOmletRaw")).SetStandardFoodPoisoningEffect();
		FCBetterThanWolves.fcItemCookedMushroomOmelet = Item.replaceItem(
				FCBetterThanWolves.fcItemCookedMushroomOmelet.itemID, FCItemFood.class, fatFoodAddon, 5, 1.0F, false, "fcItemMushroomOmletCooked");
		FCBetterThanWolves.fcItemRawScrambledEggs =((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemRawScrambledEggs.itemID, FCItemFood.class, fatFoodAddon, 2, 1.75F, false, "fcItemEggScrambledRaw")).SetStandardFoodPoisoningEffect();
		FCBetterThanWolves.fcItemCookedScrambledEggs = Item.replaceItem(
				FCBetterThanWolves.fcItemCookedScrambledEggs.itemID, FCItemFood.class, fatFoodAddon, 3, 1.75F, false, "fcItemEggScrambledCooked");
		
		
		//monster drops	/ misc	
		FCBetterThanWolves.fcItemDogFood =((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemDogFood.itemID, FCItemFood.class, fatFoodAddon, 3, 0.125F, true, "fcItemKibble"))
				.SetStandardFoodPoisoningEffect().SetFilterableProperties(2);
		
		FCBetterThanWolves.fcItemBatWing =((FCItemFood) Item.replaceItem(
				FCBetterThanWolves.fcItemBatWing.itemID, FCItemFood.class, fatFoodAddon, 2, 0.125F, false, "fcItemBatWing"))
				.SetIncreasedFoodPoisoningEffect().SetBellowsBlowDistance(3).SetFilterableProperties(18);
		
		FCBetterThanWolves.fcItemCreeperOysters =((FFAPackableFood) Item.replaceItem(
				FCBetterThanWolves.fcItemCreeperOysters.itemID, FFAPackableFood.class, fatFoodAddon, 1, 0.125F, false, "fcItemCreeperOysters"))
				.setPotionEffect(Potion.poison.id, 5, 0, 1.0F).SetBellowsBlowDistance(1).SetFilterableProperties(2);
		
		Item.rottenFlesh =((FFAPackableFood) Item.replaceItem(
				Item.rottenFlesh.itemID, FFAPackableFood.class, fatFoodAddon, 4, 0.125F, true, "rottenFlesh"))
				.SetIncreasedFoodPoisoningEffect();
	}
}
