package HoloU.InstagramPackager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String bitmapTexture = "C:\\\\JavaWorkspaces\\\\raspberryAndMore\\\\fb-2020\\\\TexturasPrueba\\8aef59b43f45b2e19f18d73d4271f55e.jpg";
    	String ktxTexture = "C:\\\\JavaWorkspaces\\\\raspberryAndMore\\\\fb-2020\\\\TexturasPrueba\\19dff9c08412ed04ccf26f56c839fe90.ktx";
    	Packager p = new Packager(bitmapTexture,ktxTexture);
    	String pathToNewInstaPackage = "";
    	try
    	{
    		pathToNewInstaPackage = p.GeneratePackage();
    	}
    	catch (Exception e)
    	{
    		System.out.printf("Exception in generatePackage: " + e.toString());
    	}
    }
	
}
