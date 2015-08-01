package net.thenightwolf.vision;

import net.thenightwolf.vision.log.Log;

public class Formula {

	public static String currentPrinterName;
	public static String currentPaperName;
	
	public static float currentInkCost;
	public static float currentPaperCost;
	public static float currentPaperInSqFt;
	
	
	public static float currentCost;
	public static float currentEndCost;
	
	public static void runFormula(float length, float width)
	{

		float d1 = length / 12.0F * (width / 12.0F);
		float d2 = currentPaperInSqFt;
		float d3 = currentPaperCost / d2;
		float d4 = d3 + currentInkCost;
		currentCost = d1 * d4;
		
		Log.log("Running formula: paper="+ Vision.currentPaperName.getText() + " ink=" + currentInkCost + " paper=" + currentPaperCost + " paperSqFt="+ currentPaperInSqFt + " length/width=" + length + "/"+ width + " total cost: " + currentCost);
				
		
		Vision.setCurrentCost(currentCost);
	}

}
