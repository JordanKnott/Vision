package net.thenightwolf.vision.objects;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Menu;

public class Printer{
	
	
	private Menu menu;
	
	private List<Paper> boundPaper;
 	
	private String name;
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public List<Paper> getBoundPaper()
	{
		return boundPaper;
	}
	
	public Printer(String name)
	{
		this.name = name;
		boundPaper = new ArrayList<Paper>();
		menu = new Menu();
		menu.setText(name);
		
	}
	
	public Menu getMenu(){
		menu.getItems().clear();
		for(Paper p : boundPaper)
		{
			menu.getItems().add(p.getItem());
		}
		
		return menu;
	}
	
	
	public void addPaper(Paper paper)
	{
		boundPaper.add(paper);
	}
	
	public void removePaper(Paper paper)
	{
		boundPaper.remove(paper);
	}
	
	
}
