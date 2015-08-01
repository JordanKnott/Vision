package net.thenightwolf.vision.objects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import net.thenightwolf.vision.Formula;
import net.thenightwolf.vision.Vision;

public class Paper {

	
	public float inkCost;
	public float paperCost;
	public float paperInSqFt;
	public String name;
	
	public MenuItem item;
	
	public String boundPrinter;
	
	public void setAttributes(String name, String boundPrinter, float inkCost, float paperCost, float paperInSqFt)
	{
		this.boundPrinter = boundPrinter;
		this.name = name;
		this.inkCost = inkCost;
		this.paperCost = paperCost;
		this.paperInSqFt = paperInSqFt;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getBoundPrinter()
	{
		return boundPrinter;
	}
	
	public float getInkCost()
	{
		return inkCost;
	}
	
	public float getPaperCost()
	{
		return paperCost;
	}
	
	public float getPaperInSqFt()
	{
		return paperInSqFt;
	}
	
	
	public Paper(String name, String boundPrinter, float inkCost, float paperCost, float paperInSqFt)
	{
		this.boundPrinter = boundPrinter;
		this.name = name;
		this.inkCost = inkCost;
		this.paperCost = paperCost;
		this.paperInSqFt = paperInSqFt;
		item = new MenuItem();
	}
	
	
	public String toString()
	{
		return "paper:" + boundPrinter + ":" + name + ":" + inkCost + ":" + paperCost + ":" + paperInSqFt;
	}
	
	
	public MenuItem getItem()
	{
		item.setText(name);
		item.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				Formula.currentInkCost = inkCost;
				Formula.currentPaperCost = paperCost;
				Formula.currentPaperInSqFt = paperInSqFt;
				Vision.setPaperName(name);
				Vision.setPrinterName(boundPrinter);
			}
			
		});
		
		return item;
		
	}

	
	
}
