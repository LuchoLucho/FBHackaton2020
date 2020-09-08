package HoloU.Web.Actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;
import HoloU.InstagramPackager.Packager;

//LC: Great working example! : https://www.codejava.net/frameworks/struts/sample-struts2-application-for-downloading-files
public class DownloadFileAction extends ActionSupport implements SessionAware {
	 
    private InputStream inputStream;
    private String fileName;
    private long contentLength;
    
    private String name;
    
    private Map<String, Object> sessionMap;

    public void setSession(Map map)
    {
        this.sessionMap = map;
    }
 
    public String execute() throws Exception {
         
        File fileToDownload;
        if (this.getName().equals("girlTexture1"))
        {
        	//Girl Texture
        	if (this.sessionMap.containsKey(TextureManager.SESSION_VAR_GIRL_TEXTURE))
            {
        		String selectedGirl = (String) this.sessionMap.get(TextureManager.SESSION_VAR_GIRL_TEXTURE);
        		if (selectedGirl.equals("Instagramer Girl1"))
        		{
        			setValuesForStaticStreamResource("Instagramer1.jpg");
    		        return SUCCESS;
        		}
        		else if (selectedGirl.equals("Instagramer Girl2"))
        		{
        			setValuesForStaticStreamResource("NicolaBigote.jpg");
    		        return SUCCESS;
        		}
        		else
        		{
        			setValuesForStaticStreamResource("Nicola01.jpg");
    		        return SUCCESS;
        		}
            }
        	else
        	{
        		setValuesForStaticStreamResource("Nicola01.jpg");
		        return SUCCESS;
        	}
        }
        else
        {
        	//Background Texture
        	if (this.sessionMap.containsKey(TextureManager.SESSION_VAR_BGRD_TEXTURE))
            {
        		String selectedBackground = (String) this.sessionMap.get(TextureManager.SESSION_VAR_BGRD_TEXTURE);
        		if (selectedBackground.equals("Living 1"))
        		{
        			setValuesForStaticStreamResource("living-room-entrance-360.jpg");
    		        return SUCCESS;
        		}
        		else if (selectedBackground.equals("Living 2"))
        		{
        			setValuesForStaticStreamResource("living-room-entrance-360_02.jpg");
    		        return SUCCESS;
        		}
        		else
        		{
        			setValuesForStaticStreamResource("360_world.jpg");
        			return SUCCESS;
        		}
            }        	
        }
        //----
        setValuesForStaticStreamResource("360_world.jpg");
		return SUCCESS;
    }
    
    public String instagramPackagerDownloader() throws Exception
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
    	//setValuesForStaticStreamResource("360_world.jpg");
    	File initialFile = new File(pathToNewInstaPackage);
    	inputStream = new FileInputStream(initialFile);
    	fileName = initialFile.getName();
    	contentLength = inputStream.available();
		return SUCCESS;
	}
    
    private void setValuesForStaticStreamResource(String staticFileName) throws Exception
    {
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		inputStream = classLoader.getResourceAsStream(
		    "Resources/"+staticFileName);
        fileName = staticFileName;
        contentLength = inputStream.available();         
    }
     
    public long getContentLength() {
        return contentLength;
    }
 
    public String getFileName() {
        return fileName;
    }
 
    public InputStream getInputStream() {
        return inputStream;
    } 
    
    public String getName()
    {
    	return name;
    }
    
    public void setName(String newName)
    {
    	name = newName;
    }
}
