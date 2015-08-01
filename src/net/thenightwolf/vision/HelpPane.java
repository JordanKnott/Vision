package net.thenightwolf.vision;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import net.thenightwolf.vision.log.Log;

public class HelpPane {

	public static GridPane helpPane = new GridPane();
	
	public static Scene generateHelpWindow()
	{
		Log.log("Generating help window");
		BorderPane window = new BorderPane();
		
		
		VBox legend = new VBox();
		legend.setStyle("-fx-background-color: #DED7DC; -fx-border-width: 1pt; -fx-border-color: #000000");
		
		legend.setSpacing(10);
		
		Label welcomeL = new Label("Welcome");
		welcomeL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
		
		welcomeL.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				welcomeL.setStyle("-fx-text-fill: #000000; -fx-underline: true;");
			}
			
		});
		welcomeL.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				welcomeL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
			}
			
		});
		welcomeL.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				welcomeL.setStyle("-fx-text-fill: #0000ff; -fx-underline: false;");
				setHelpPane(1);
			}
			
		});
		
		
		
		Label userInterfaceL = new Label("User Interface");
		userInterfaceL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
		
		userInterfaceL.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				userInterfaceL.setStyle("-fx-text-fill: #000000; -fx-underline: true;");
			}
			
		});
		userInterfaceL.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				userInterfaceL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
			}
			
		});
		userInterfaceL.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				userInterfaceL.setStyle("-fx-text-fill: #0000ff; -fx-underline: false;");
				setHelpPane(2);
			}
			
		});
		
		Label printerManagerL = new Label("Printer Manager");
		printerManagerL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
		
		printerManagerL.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				printerManagerL.setStyle("-fx-text-fill: #000000; -fx-underline: true;");
			}
			
		});
		printerManagerL.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				printerManagerL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
			}
			
		});
		printerManagerL.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				printerManagerL.setStyle("-fx-text-fill: #0000ff; -fx-underline: false;");
				setHelpPane(3);
			}
			
		});
		
		Label newPrinterL = new Label("Adding a New Printer Group");
		newPrinterL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
		
		newPrinterL.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				newPrinterL.setStyle("-fx-text-fill: #000000; -fx-underline: true;");
			}
			
		});
		newPrinterL.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				newPrinterL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
			}
			
		});
		newPrinterL.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				newPrinterL.setStyle("-fx-text-fill: #0000ff; -fx-underline: false;");
				setHelpPane(4);
			}
			
		});
		
		Label newPaperL = new Label("Adding a New Paper Type");
		newPaperL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
		
		newPaperL.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				newPaperL.setStyle("-fx-text-fill: #000000; -fx-underline: true;");
			}
			
		});
		newPaperL.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				newPaperL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
			}
			
		});
		newPaperL.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				newPaperL.setStyle("-fx-text-fill: #0000ff; -fx-underline: false;");
				setHelpPane(5);
			}
			
		});
		
		Label editingL = new Label("Editing and Deleting in PrintManager");
		editingL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
		
		editingL.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				editingL.setStyle("-fx-text-fill: #000000; -fx-underline: true;");
			}
			
		});
		editingL.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				editingL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
			}
			
		});
		editingL.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				editingL.setStyle("-fx-text-fill: #0000ff; -fx-underline: false;");
				setHelpPane(6);
			}
			
		});
		
		Label preferencesL = new Label("Preferences");
		preferencesL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
		
		preferencesL.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				preferencesL.setStyle("-fx-text-fill: #000000; -fx-underline: true;");
			}
			
		});
		preferencesL.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				preferencesL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
			}
			
		});
		preferencesL.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				preferencesL.setStyle("-fx-text-fill: #0000ff; -fx-underline: false;");
				setHelpPane(7);
			}
			
		});
		
		Label technicalL = new Label("Technical Details");
		technicalL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
		
		technicalL.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				technicalL.setStyle("-fx-text-fill: #000000; -fx-underline: true;");
			}
			
		});
		technicalL.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				technicalL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
			}
			
		});
		technicalL.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				technicalL.setStyle("-fx-text-fill: #0000ff; -fx-underline: false;");
				setHelpPane(8);
			}
			
		});
		
		Label adminL = new Label("Administration");
		adminL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
		
		adminL.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				adminL.setStyle("-fx-text-fill: #000000; -fx-underline: true;");
			}
			
		});
		adminL.setOnMouseExited(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				adminL.setStyle("-fx-text-fill: #0000ff; -fx-underline: true;");
			}
			
		});
		adminL.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				adminL.setStyle("-fx-text-fill: #0000ff; -fx-underline: false;");
				setHelpPane(9);
			}
			
		});
		
		legend.getChildren().addAll(welcomeL, userInterfaceL, printerManagerL, newPrinterL, newPaperL, editingL, preferencesL, technicalL, adminL);
		
		helpPane = new GridPane();
		
		helpPane.setAlignment(Pos.CENTER);
		
		setHelpPane(1);
		
		window.setLeft(legend);
		ScrollPane sp = new ScrollPane(helpPane);
		sp.setPadding(new Insets(15, 15, 15,15));
		window.setCenter(sp);
		Scene scene = new Scene(window, 950, 550);
		return scene;
		
	}
	
	
	public static void setHelpPane(int i)
	{
		Log.log("Setting the help pane to " + i);
		helpPane.getChildren().clear();
		if(i == 1){
			Label title = new Label("Welcome to Vison [" + Vision.VERSION + "] help window!");
			title.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
			helpPane.add(title, 0, 0);
			
			helpPane.add(new Label(" To the left, there are several topics, which when clicked on"), 0, 1);
			helpPane.add(new Label("will take you to a help window, covering said topic."), 0, 2);
			helpPane.add(new Label(""), 0, 3);
			helpPane.add(new Label("Brief Overview"), 0, 4);
			
			helpPane.add(new Label("Welcome: Shows this screen"), 0, 5);
			helpPane.add(new Label("User Interace: Gives help over the user interface (the screen you see) "), 0, 6);
			helpPane.add(new Label("Printer Manager: What is it and why use it?"), 0, 7);
			helpPane.add(new Label("New Printer: How to add a new printer"),  0, 8);
			helpPane.add(new Label("New Paper: How to add a new paper type"), 0, 9);
			helpPane.add(new Label("Editing: How to make changes to created items"), 0, 10);
			helpPane.add(new Label("Preferences: What they mean and how to change them"), 0, 11);
			helpPane.add(new Label("Technical: For the geeks"), 0, 12);
			helpPane.add(new Label("Admin: For the admins"), 0, 13);
			
		}
		if(i == 2){
			Label title = new Label("The User Interface and You");
			title.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
			helpPane.add(title, 0, 0);
			helpPane.add(new Label("The User Interface (UI) is the screen you see and how you interact with it"), 0, 1);
			helpPane.add(new Label("The menu bar is where you go to select different paper from printer groups"), 0, 2);
			helpPane.add(new Label(""), 0, 2);
			ImageView menuBarPNG = new ImageView(new Image("help/ui/MenuBar.PNG"));
			helpPane.add(menuBarPNG, 0, 3);
			helpPane.add(new Label(""), 0, 4);
			helpPane.add(new Label("Paper types are grouped into different drop down menus, where"), 0, 5);
			helpPane.add(new Label("the printer name, is the name of the drop down."), 0, 6);
			helpPane.add(new Label(""), 0, 7);
			helpPane.add(new Label("The Calculator window is where the actual calculating of costs occur."), 0, 8);
			helpPane.add(new Label(""), 0, 9);
			ImageView calcWinPNG = new ImageView(new Image("help/ui/CalcWindow.PNG"));
			//calcWinPNG.setFitHeight(256);
			//calcWinPNG.setFitWidth(512);
			helpPane.add(calcWinPNG, 0, 10);
			helpPane.add(new Label(""), 0, 11);
			helpPane.add(new Label("The Width field is used to input the paper's width."), 0, 12);
			helpPane.add(new Label("The Length field is where the paper's length."), 0, 13);
			helpPane.add(new Label("The cost will appear where the dollar sign is."), 0, 14);
			helpPane.add(new Label("At the bottom, is where the button to calculate the cost ( you can also hit Enter )."), 0, 15);
			helpPane.add(new Label("The selected printer and paper type are also shown here as well"), 0, 16);

			helpPane.autosize();

		}
		
	}
	
}
