package HoloU.Web.Actions;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;  

import org.apache.struts2.dispatcher.SessionMap;  
import org.apache.struts2.interceptor.SessionAware;  
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TextureManager extends ActionSupport implements SessionAware
{
	private Map<String, Object> sessionMap;
	private List<String> availableTextures;
	private List<String> availableBackground;
	private String selectedGirl;
	private String selectedBackground;
	
	public static String SESSION_VAR_GIRL_TEXTURE = "SESSION_VAR_GIRL_TEXTURE";
	public static String SESSION_VAR_BGRD_TEXTURE = "SESSION_VAR_BGRD_TEXTURE";

    public void setSession(Map map)
    {
        this.sessionMap = map;
    }
	
	public List<String> getAvailableTextures()
	{
		if (availableTextures == null)
		{
			availableTextures = new ArrayList<String>();
			availableTextures.add("Default");
			availableTextures.add("Instagramer Girl1");
			availableTextures.add("Instagramer Girl2");
		}		
		return availableTextures;
	}
	
	public void setSelectedGirl(String newValue)
	{
		selectedGirl = newValue;
	}
	
	public String getSelectedGirl()
	{
		if (selectedGirl == null)
		{
			return "DEFAULT";
		}
		return selectedGirl;
	}
	
	public void setSelectedBackground(String newValue)
	{
		this.selectedBackground = newValue;
	}
	
	public String getSelectedBackground()
	{
		if (this.selectedBackground == null)
		{
			return "DEFAULT";
		}
		return this.selectedBackground;
	}
	
	public List<String> getAvailableBackground() {
		if (availableBackground == null)
		{
			availableBackground = new ArrayList<String>();
			availableBackground.add("360");
			availableBackground.add("Living 1");
			availableBackground.add("Living 2");
		}	
		return availableBackground;
	}
	
	public String texturesQueryJsonExecute()
	{			
		return SUCCESS;
	}
	
	public String texturesSetExecute()
	{			
		sessionMap.put(SESSION_VAR_GIRL_TEXTURE, getSelectedGirl());
		sessionMap.put(SESSION_VAR_BGRD_TEXTURE, getSelectedBackground());
		return SUCCESS;
	}
	
	public String backgroundQueryJsonExecute()
	{			
		sessionMap.put(SESSION_VAR_GIRL_TEXTURE, getSelectedGirl());
		sessionMap.put(SESSION_VAR_BGRD_TEXTURE, getSelectedBackground());
		return SUCCESS;
	}

}
