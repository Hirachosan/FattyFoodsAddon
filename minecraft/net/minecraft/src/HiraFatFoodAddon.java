package net.minecraft.src;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


import net.minecraft.client.Minecraft;

public class HiraFatFoodAddon extends FCAddOn
{
	private static HiraFatFoodAddon instance;
	
	public static int FoodDifficulty= 2;
	public static int StartingFat= 4;
	private static final Map m_FFAconfigMap= new HashMap();
	
	
	public HiraFatFoodAddon() {
		super("Fatty Foods", "1.2", "HiraFatFood");
	}
	public String GetLanguageFilePrefix()
    {
        return "FFA";
    }
	public static HiraFatFoodAddon getInstance() 
	{
		if (instance == null) {
			instance = new HiraFatFoodAddon();
		}
		return instance;
	}
	
	public void serverPlayerConnectionInitialized(NetServerHandler serverHandler, EntityPlayerMP playerMP)
	{
		super.serverPlayerConnectionInitialized(serverHandler, playerMP);
		FCUtilsWorld.SendPacketToPlayer(serverHandler, new Packet3Chat(
				"Food difficulty setting: " + FoodStats.GetFoodDifficulty()));
//		if (!MinecraftServer.getServer().isSinglePlayer())
	}
	
	@Override
	public void Initialize() {
		FCAddOnHandler.LogMessage(this.getName() + " Version " + this.getVersionString() + " Initializing...");
		ReadFFAConfigFile();
		
		FFADefs.FFAAddNewDamageType();
		FFADefs.FFAAddNewPotions();
		FFADefs.FFAReplaceItems();
		FFADefs.FFAAddNewItems();
		FFADefs.FFAAddNewRecipes();

		FCAddOnHandler.LogMessage(this.getName() + " Initialized");
	}
	private static void ReadFFAConfigFile()

	{
		// Client version
		File configFile = new File( Minecraft.getMinecraftDir(), "FFAConfig.txt" );
		// Server version
		//File configFile = new File( new File("."), "FFAConfig.txt" );
		
        try
        {
            if( !configFile.exists() )
            {
            	FCAddOnHandler.LogMessage( "FFA config file not found..." );
            	
                return;
            }
            
            FCAddOnHandler.LogMessage( "FFA reading custom config file..." );
        	
            BufferedReader bufferedreader = new BufferedReader( new FileReader( configFile ) );
            
            for ( String s = ""; (s = bufferedreader.readLine()) != null; )
            {
                String as[] = s.split("=");
                
                if ( as.length == 2 )
                {
                    for ( int iTempIndex = 0; iTempIndex < as.length; iTempIndex++ )
                    {
                    	as[iTempIndex] = as[iTempIndex].trim();
                    }
                    
                    m_FFAconfigMap.put( as[0], as[1] );
                    
                    //System.out.println( "Parsed " + as[0] + " " + as[1] );
                }                
            }
             
            bufferedreader.close();
        }
        catch ( Exception exception )
        {
            System.out.println( "Failed to load FFA config file" );
            exception.printStackTrace();
        }
        
       FoodDifficulty = ParseIntegerConfigSetting( "FoodDifficultyLevel", FoodDifficulty );
       FoodStats.SetBaseExhaustionCost( FoodDifficulty);

	}

    private static int ParseIntegerConfigSetting( String sName, int iDefault )
    {
    	String sValue = (String)m_FFAconfigMap.get( sName );
    	
    	if ( sValue != null )
    	{
            try
            {
            	int iValue = Integer.parseInt( sValue );
          
//            	if ( iDefault != iValue )
//            	{
//            		System.out.println( sName + " , " + iValue + " (" + iDefault + ") READ NON DEFAULT ****************" );
//            	}
//            	else
//            	{
//            		System.out.println( sName + " , " + iValue + " (" + iDefault + ") READ " );
//            	}
//                
            	return iValue;
            }
            catch ( Exception exception )
            {
                System.out.println( "Invalid FFA config file entry: " + sName + " , " + sValue );
                exception.printStackTrace();
            }
    	}
    	
        //System.out.println( sName + " , " + iDefault + " DEFAULT **************" );
        
    	return iDefault;
    }
}