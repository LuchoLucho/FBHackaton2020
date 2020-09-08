package HoloU.InstagramPackager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FileUtils;

public class Packager
{
	public static String templatePath1 = "C:\\\\JavaWorkspaces\\\\raspberryAndMore\\\\fb-2020\\\\InstagramRubiaPijamaTemplate01";
	
	private String newTextureBitmapPathToReplaceInTemplate;
	private String newTextureKTXPathToReplaceInTemplate;
	private String tempOutputPath = null;
	
	public Packager(String pathToNewTextureBitmap, String PathToNewTextureKTX)
	{
		newTextureBitmapPathToReplaceInTemplate = pathToNewTextureBitmap;
		newTextureKTXPathToReplaceInTemplate = PathToNewTextureKTX;
	}
	
	private String getTempOuputPath() throws IOException {
		if (tempOutputPath == null)
		{
			String newTextureName = new File(newTextureBitmapPathToReplaceInTemplate).getName();
			Path tempDirWithPrefix = Files.createTempDirectory(newTextureName);
			tempOutputPath = tempDirWithPrefix.toString();
		}
		return tempOutputPath;
	}
	
	public String GeneratePackage() throws IOException
	{
        System.out.println( "Hello World!" );
        String templateCopyPath = generateNewCopyOfTemplate(templatePath1);
        replaceMainTextureWith(templateCopyPath,newTextureBitmapPathToReplaceInTemplate, newTextureKTXPathToReplaceInTemplate);
        return turnFolderPathIntoPackage(templateCopyPath);
    }

	private static void replaceMainTextureWith(String templateCopyPath, String texturePathBitmap, String texturePathKTX) throws IOException
	{
		if (!new File(texturePathBitmap).exists())
		{
			return;
		}
		if (!new File(texturePathKTX).exists())
		{
			return;
		}
		replaceFile(texturePathBitmap,templateCopyPath + "\\_WorldObject\\textures\\Nicola06_Body01_Textured03_Girl_PJ01.jpg");
		replaceFile(texturePathBitmap,templateCopyPath + "\\_WorldObject\\textures\\Nicola06_Body01_Textured03_Girl_PJ01(1).jpg");
		replaceFile(texturePathKTX,templateCopyPath + "\\_WorldObject@etc\\19dff9c08412ed04ccf26f56c839fe90.ktx");
		replaceFile(texturePathKTX,templateCopyPath + "\\_WorldObject@pvr\\21eafc8b75002e966f36e41219504fee.ktx");
		replaceFile(texturePathKTX,templateCopyPath + "\\_WorldObject@uncompressed\\8aef59b43f45b2e19f18d73d4271f55e.ktx");		
	}
	
	private static void replaceFile (String sourceF, String destF) throws IOException
	{
		File fileSrc = new File(sourceF);
		File fileDst = new File(destF);
		Files.copy(fileSrc.toPath(), fileDst.toPath(), StandardCopyOption.REPLACE_EXISTING);
	}

	private String generateNewCopyOfTemplate(String toCopyPath) throws IOException 
	{		
		String templateCopyDest = getTempOuputPath();
		copyAllFiles(templatePath1,templateCopyDest,false);
		return templateCopyDest;
	}

	private String turnFolderPathIntoPackage(String templatePath) throws IOException {
		String packageOutputPath = getTempOuputPath() + "\\output";
		File currentDirectory = new File(packageOutputPath);
		if(!currentDirectory.exists())
        {
        	currentDirectory.mkdir();
        }
        //----CREATING DIRECTORIES:
		File file = File.createTempFile("temp", null);
		System.out.println(file.getAbsolutePath());
        String outputFilePath = file.getAbsolutePath() + new File(this.newTextureBitmapPathToReplaceInTemplate).getName() + ".arexport"; // new File(packageOutputPath + "\\..\\" + "NicoleRubiaPijama01_JAVA_HACK!.arexport").getAbsolutePath();
        String currentDirectoryString = packageOutputPath + "\\" + "Dir1";
        currentDirectory = new File(currentDirectoryString);
        if(!currentDirectory.exists())
        {
        	currentDirectory.mkdir();
        }
        //-------------------------
        try
        {
        	//----_WorldObject
        	compressZipfile(templatePath + "\\_WorldObject\\\\_WorldObject\\",
        			currentDirectoryString +  "\\WorldObject.arproj");
        	copyAllFiles(templatePath + "\\_WorldObject",currentDirectoryString,true);// The TRUE is to IGNORE the _ in the templates
        	compressZipfile(currentDirectoryString,packageOutputPath + "\\" + "WorldObject.arprojpkg");
        	//Clean directory        	
        	FileUtils.deleteDirectory(new File(currentDirectoryString));
        	//----_WorldObject@etc:
        	currentDirectoryString = templatePath + "\\" + "_WorldObject@etc";
        	compressZipfile(currentDirectoryString,packageOutputPath + "\\" + "WorldObject@etc.arfx");
        	//----_WorldObject@pvr:
        	currentDirectoryString = templatePath + "\\" + "_WorldObject@pvr";
        	compressZipfile(currentDirectoryString,packageOutputPath + "\\" + "WorldObject@pvr.arfx");
        	//----_WorldObject@uncompressed.arfx:
        	currentDirectoryString = templatePath + "\\" + "_WorldObject@uncompressed";
        	compressZipfile(currentDirectoryString,packageOutputPath + "\\" + "WorldObject@uncompressed.arfx");        	
        	//Simple Json copy!
        	Files.copy(new File(templatePath + "\\export.json").toPath(), new File(packageOutputPath + "\\export.json").toPath(), StandardCopyOption.REPLACE_EXISTING);
        	//Final File:
        	currentDirectoryString = packageOutputPath;        	
        	compressZipfile(currentDirectoryString,outputFilePath);        	
        }
        catch(Exception e)
        {
        	System.out.printf("Exception: %s\r\n",e.toString());
        	outputFilePath = null;
        }
        System.out.println( "End!" );
        return outputFilePath;
	}

    
    private static void copyAllFiles(String sourceDir,String destDir, boolean ignoreUnderScore) throws IOException
    {
    	for (File file : new File(sourceDir).listFiles()) 
    	{
    		System.out.printf("File %s\n",file.getName());
            if (file.isDirectory())  
            {            	
            	if ((!ignoreUnderScore) || (!file.getName().startsWith("_")))// Ignoring _
            	{
	            	String currentDirectoryString = destDir + "\\" +  file.getName();
	                File currentDirectory = new File(currentDirectoryString);
	                if(!currentDirectory.exists())
	                {
	                	currentDirectory.mkdir();
	                }
	                copyAllFiles(sourceDir + "\\" + file.getName(),  destDir + "\\" + file.getName(),ignoreUnderScore);
            	}
            } else
            {
            	 Files.copy(file.toPath(), new File(destDir + "\\" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
    
    private static void compressZipfile(String sourceDir, String outputFile) throws IOException, FileNotFoundException {
        ZipOutputStream zipFile = new ZipOutputStream(new FileOutputStream(outputFile));
        compressDirectoryToZipfile(sourceDir, sourceDir, zipFile);
        IOUtils.closeQuietly(zipFile);
    }

    private static void compressDirectoryToZipfile(String rootDir, String sourceDir, ZipOutputStream out) throws IOException, FileNotFoundException {
        for (File file : new File(sourceDir).listFiles()) {
            if (file.isDirectory()) {
                compressDirectoryToZipfile(rootDir, sourceDir + File.separator + file.getName(), out);
            } else {
            	String entryString = sourceDir.replace(rootDir, "") +  "\\" + file.getName();
            	if (entryString.startsWith("\\"))
            	{
            		entryString = entryString.substring(1);
            	}
            	System.out.printf("%s => ",entryString);
                ZipEntry entry = new ZipEntry(entryString);
                out.putNextEntry(entry);
                String completePath = sourceDir + "\\" + file.getName();
                System.out.printf("%s\n",completePath);
                FileInputStream in = new FileInputStream(completePath);
                IOUtils.copy(in, out);
                IOUtils.closeQuietly(in);
            }
        }
    }

}
