package net.thenightwolf.vision.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.thenightwolf.vision.Vision;

public class Log {

	private static PrintWriter log_file;
	
	
	public static void init()
	{
		if(new File(Vision.log).isDirectory()){
		
			try {
				log_file = new PrintWriter(Vision.log + "Log-Report " + getTimeStamp() + ".txt", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
			}

		}else {
			new File(Vision.log).mkdir();
			try {
				log_file = new PrintWriter(Vision.log + "Log-Report " + getTimeStamp() + ".txt", "UTF-8");
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
			}
		}
		
	}
	
	
	
	public static void log(String i)
	{
		
		log_file.write("[" + getCurrentDateStamp() + "] " + i + "\n");
		
	}
	
	public static void close()
	{
		log_file.close();
	}
	
	/**
	 * Method taken from StackOverflow 
	 * 
	 * @return String : The current data and time formated in yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateStamp() {
	    SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");//dd/MM/yyyy
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}

	/**
	 * Method derived from {@link Log#getCurrentDateStamp()}
	 * @return String the current time formated HH-mm-ss
	 */
	public static String getTimeStamp() {
		
	    SimpleDateFormat sdfDate = new SimpleDateFormat("dd_MM_yyyy hhmma");//dd/MM/yyyy
	    
	    Date now = new Date();
	    String strDate = sdfDate.format(now);
	    return strDate;
	}
	
}
