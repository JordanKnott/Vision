package net.thenightwolf.vision;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.thenightwolf.vision.log.Log;
import net.thenightwolf.vision.objects.Paper;
import net.thenightwolf.vision.objects.Printer;

public class Vision extends Application {

	public Map<String, Printer> boundPrinters = new HashMap<String,Printer>();
	
	public static final String VERSION = "1.1.0";
	
	public static String userURL = "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Vision";
  
	public static String printManagerPassword = "password01";
	
	public static String defaultURL = "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Vision\\vision.cfg";
	
	public static String url = "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Vision\\vision.cfg";
	public static String log = "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Vision\\Log\\";
	
	public static final String EAST_SERVER = "C:\\SERVER";
	
	public static String serverURL = "C:\\SERVER\\Vision\\server.cfg";
	
	//0 = Modena 1 = Caspian
	public static int style = 1;
	
	public static Label currentPaperName;
	public static Label currentPrinterName;

	public static boolean isServerUsed;
	
	public static Stage stage;
	
	public static Label currentCost;
	
	
	public static int helpIndex;
	
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void setPrinterName(String s)
	{
		currentPrinterName.setText(s);
	}
	
	public static void setPaperName(String s)
	{
		currentPaperName.setText(s);
	}
	
	public static void setCurrentCost(float s)
	{
	
		currentCost.setText("$" + Math.round(s * 100.0) / 100.0);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		Log.init();
		Log.log("Vision [" + VERSION + "] Starting");
		stage = primaryStage;

		stage.getIcons().add(new Image(Vision.class.getResource("/Vision.png").toString()));
		Log.log("Loading user config at ( " + userURL + " ) ");
		loadUserConfig();
		if(style == 1) {
			Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
			Log.log("Setting style to Caspian");
		} else {
			Log.log("Setting Style to Modena");
		}

		Log.log("Loading configs at ( " + url + " )");
		loadConfig(url);
		
		Log.log("Setting Shutdown Hooks");
		setShutdownProc();
		
		Log.log("Creating Calculator Window");
		stage.setScene(generateCalcWindow());

		stage.setTitle("Vision");
		stage.centerOnScreen();

		Log.log("Generating help pane");
		
		Log.log("Window Shown");
		stage.show();
	}
	
	public void loadUserConfig()
	{
		File f = new File(userURL);
		if(f.exists() && f.isDirectory())
		{
			Log.log(userURL + " found! Loading config");
			try {
				BufferedReader file = new BufferedReader(new FileReader(userURL + "\\user.cfg"));
				String line;
				while((line = file.readLine()) != null)
				{
					String parts[] = line.split(":");
					if(parts[0].equals("url"))
					{
						url = parts[1] + ":" +parts[2];
					}else if(parts[0].equals("style"))
					{
						style = Integer.parseInt(parts[1]);
					}else if(parts[0].equals("server")){
						isServerUsed = Boolean.parseBoolean(parts[1]);
						url = serverURL;
					}
				
					
					
				}
				
				file.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			
		}else {
			Log.log("AppData/Local/Vision Directory not found, Creating new one");
			f.mkdir();
				try {
					BufferedWriter file = new BufferedWriter(new FileWriter(userURL + "\\user.cfg"));
					
					file.write("# User Configuration File for Vision.exe [Version " + VERSION + "] by Jordan Knott (TheNightWolf)\n");
					file.write("# The path to the printer configuration file. The default name for this file is vision.cfg\n");
					file.write("url:" + url + "\n"); 
					file.write("server:" + true + "\n");
					file.write("# This determines the look of Vision. 0 is the Modena stylesheet, while 1 is Caspian\n");
					file.write("style:" + style + "\n");
					
					
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
		}
	
		
		
	}
	
	public void saveUserConfig()
	{
		Log.log("Saving user configuration");
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(userURL + "\\user.cfg"));
			file.write("# User Configuration File for Vision.exe [Version " + VERSION + "] by Jordan Knott (TheNightWolf)\n");
			file.write("# The path to the printer configuration file. The default name for this file is vision.cfg\n");
			if(isServerUsed)
			{
				file.write("url:" + "C:\\Users\\" + System.getProperty("user.name") + "\\AppData\\Local\\Vision\\vision.cfg" + "\n"); 	
				
			} else {
				file.write("url:" + url + "\n"); 
			}
			file.write("server:" + isServerUsed + "\n");
			file.write("# This determines the look of Vision. 0 is the Modena stylesheet, while 1 is Caspian\n");
			file.write("style:" + style + "\n");
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Scene generateCalcWindow()
	{
		Log.log("Generating calculator window");
		
		BorderPane core = new BorderPane();
		
		MenuBar menu = new MenuBar();
	
		for (Map.Entry<String, Printer> entry : boundPrinters.entrySet())
		{
			menu.getMenus().add(entry.getValue().getMenu());
		}
	
		
		Menu settings = new Menu("Settings");
		
		MenuItem user = new MenuItem("Preferences");
		user.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				Stage dialog = new Stage();
				dialog.initOwner(stage);
				
				GridPane grid = new GridPane();
				grid.setVgap(10);
				grid.setHgap(10);
				Label urlL = new Label("Printer.cfg file url");
				TextField urlF = new TextField();
				urlF.setText(url);
				urlF.setPrefWidth(75);
				
				Button openFC = new Button("...");
				openFC.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						FileChooser fileChooser = new FileChooser();
						fileChooser.setTitle("Open Preference File");
						urlF.setText(fileChooser.showOpenDialog(stage).toString());						
					}
					
				});
				grid.addRow(0, urlL, urlF, openFC);
				
				Button save = new Button("Save");
				save.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						
						url = urlF.getText().trim();
						saveUserConfig();			
						dialog.close();
					}
					
				});
				
				grid.addRow(1, save);
				
				dialog.setScene(new Scene(grid, 375, 200));
				dialog.show();
				
			}
			
		});
		
		
		MenuItem config = new MenuItem("Printer Manager");
		config.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				
				Stage pswDialog = new Stage();
				pswDialog.initOwner(stage);
				
				GridPane gpane = new GridPane();
				gpane.setAlignment(Pos.CENTER);
				
				gpane.add(new Label("Print Manager is locked!"), 0, 0);
				
				HBox hbox = new HBox();
				Label passL = new Label("Password ");
				TextField passF = new TextField();
				hbox.getChildren().addAll(passL, passF);
				
				gpane.add(hbox, 0, 1);
				
				Scene scene = new Scene(gpane, 250, 150);
				scene.setOnKeyReleased(new EventHandler<KeyEvent>(){

					@Override
					public void handle(KeyEvent event) {
						if(event.getCode() == KeyCode.ENTER)
						{
							if(passF.getText().equals(printManagerPassword))
							{
								pswDialog.close();								
								stage.setScene(generateConfigPane());
								
							}
							else
							{
								GridPane gpane = new GridPane();
								gpane.setAlignment(Pos.CENTER);
								
								gpane.add(new Label("Incorrect Password!"), 0, 0);
								
								Scene tScene = new Scene(gpane, 250, 150);
								pswDialog.setScene(tScene);
							}
						}
					}
					
				});
				
				pswDialog.setScene(scene);
				pswDialog.show();
			}
			
		});
		
		MenuItem help = new MenuItem("Help");
		help.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				Stage help = new Stage();
				help.initOwner(stage);

				help.getIcons().add(new Image(Vision.class.getResource("/Vision.png").toString()));
				help.setScene(HelpPane.generateHelpWindow());
				
				help.show();
			}
			
		});
		
		Menu server = new Menu("Server");
		
		MenuItem east = new MenuItem("EAST Setup");
		east.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				Stage dialog = new Stage();
				dialog.initOwner(stage);
				
				GridPane core = new GridPane();
				
				Label curStatus = new Label("EAST Configuration Used: " + isServerUsed);
				core.setAlignment(Pos.CENTER);
				
				core.add(curStatus, 0, 0);
				
				
				HBox box = new HBox();
				box.setPadding(new Insets(15,0,0,0));
				box.setSpacing(10);
				Label warning = new Label("Would you like to...");
				Button yes = new Button("Enable");
				yes.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						Vision.isServerUsed = true;
						dialog.close();
						
						Stage tmpDialog = new Stage();
						tmpDialog.initOwner(stage);
						
						GridPane pane = new GridPane();
						pane.setVgap(15);
						pane.setAlignment(Pos.CENTER);
					
						Label warning2 = new Label("Application needs to restart!");
						Button ok = new Button("Okay");
						ok.setOnAction(new EventHandler<ActionEvent>(){

							@Override
							public void handle(ActionEvent arg0) {
								Platform.exit();
								System.exit(1);
							}
							
						});
						GridPane.setHalignment(warning2, HPos.CENTER);
						GridPane.setHalignment(ok, HPos.CENTER);
						
						pane.add(warning2, 0, 0);
						pane.add(ok,0, 1);
						
						Scene tmpScene = new Scene(pane, 175, 125);
						tmpDialog.setScene(tmpScene);
						tmpDialog.show();
					}
					
				});
				Button no = new Button("Disable");
				no.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						Vision.isServerUsed = false;
						dialog.close();
						
						Stage tmpDialog = new Stage();
						tmpDialog.initOwner(stage);
						
						GridPane pane = new GridPane();
						pane.setVgap(15);
						pane.setAlignment(Pos.CENTER);
					
						Label warning2 = new Label("Application needs to restart!");
						Button ok = new Button("Okay");
						ok.setOnAction(new EventHandler<ActionEvent>(){

							@Override
							public void handle(ActionEvent arg0) {
								Platform.exit();
								System.exit(1);
							}
							
						});
						GridPane.setHalignment(warning2, HPos.CENTER);
						GridPane.setHalignment(ok, HPos.CENTER);
						
						pane.add(warning2, 0, 0);
						pane.add(ok,0, 1);
						
						Scene tmpScene = new Scene(pane, 175, 125);
						tmpDialog.setScene(tmpScene);
						tmpDialog.show();
					}
					
				});
				box.getChildren().addAll(warning, yes, no);
				core.add(box, 0, 1);
				Scene scene = new Scene(core, 250, 150);
				dialog.setScene(scene);
				dialog.show();
				
			}
			
		});
		MenuItem manage = new MenuItem("Manage");
		
		
		server.getItems().addAll(east, manage);
		
		
		settings.getItems().addAll(user, config, server, help);
		
		
		
		menu.getMenus().addAll(settings);

		
		core.setTop(menu);

		GridPane localGridPane1 = new GridPane();
		GridPane localGridPane2 = new GridPane();
		localGridPane2.setHgap(10.0D);
		localGridPane2.setVgap(19.0D);
		localGridPane2.setPadding(new Insets(0.0D, 10.0D, 0.0D, 10.0D));
		localGridPane1.setHgap(10.0D);
		localGridPane1.setVgap(10.0D);
		localGridPane1.setPadding(new Insets(0.0D, 10.0D, 0.0D, 10.0D));
		Label localLabel1 = new Label("Length (in.)");
		localLabel1.setFont(Font.font("Arial", FontWeight.BOLD, 18.0D));
		localGridPane1.add(localLabel1, 3, 2);
		final TextField length = new TextField();
		localGridPane1.add(length, 4, 2);
		Label localLabel2 = new Label("Width (in.)");
		localLabel2.setFont(Font.font("Arial", FontWeight.BOLD, 18.0D));
		localGridPane1.add(localLabel2, 6, 2);
		final TextField width = new TextField();
		localGridPane1.add(width, 7, 2);
		currentCost = new Label("$0.00");
		currentCost.setAlignment(Pos.BOTTOM_LEFT);
		currentCost.setFont(Font.font("Arial", FontWeight.BOLD, 18.0D));
		localGridPane1.add(currentCost, 9, 2);
		Button localButton = new Button();
		localButton.setText("Calculate");
		localButton.setMinSize(65.0D, 50.0D);
		localButton.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				try { 
					Formula.runFormula(Float.parseFloat(length.getText()), Float.parseFloat(width.getText()));
	
				} catch (NumberFormatException e) {
					dialogBox("Text Fields either empty or invalid!");
				}
			}
			
		});
		localGridPane2.add(localButton, 1, 0);

		currentPrinterName = new Label("No Printer selected");
		currentPrinterName.setFont(Font.font("Arial", FontWeight.BOLD, 18.0D));
		localGridPane2.add(currentPrinterName, 0, 0);

		currentPaperName = new Label("No Paper selected");
		currentPaperName.setFont(Font.font("Arial", FontWeight.BOLD, 18.0D));

		localGridPane2.add(currentPaperName, 2, 0);
		currentPaperName.setAlignment(Pos.BASELINE_LEFT);
		currentPrinterName.setAlignment(Pos.BASELINE_RIGHT);
		ColumnConstraints localColumnConstraints1 = new ColumnConstraints();
		localColumnConstraints1.setPercentWidth(40.0D);
		ColumnConstraints localColumnConstraints2 = new ColumnConstraints();
		localColumnConstraints1.setHalignment(HPos.CENTER);
		localColumnConstraints2.setPercentWidth(20.0D);
		ColumnConstraints localColumnConstraints3 = new ColumnConstraints();
		localColumnConstraints2.setHalignment(HPos.CENTER);
		localColumnConstraints3.setPercentWidth(40.0D);
		localColumnConstraints3.setHalignment(HPos.CENTER);
		localGridPane2.getColumnConstraints().addAll(new ColumnConstraints[] { localColumnConstraints1, localColumnConstraints2, localColumnConstraints3 });
		localGridPane2.setAlignment(Pos.CENTER);

		core.setBottom(localGridPane2);
		core.setCenter(addAnchorPane(localGridPane1));


		Scene calc = new Scene(core, 750, 350);
		calc.setOnKeyReleased(new EventHandler<KeyEvent>(){

			@Override
			public void handle(KeyEvent arg0) {
				if(arg0.getCode() == KeyCode.ENTER)
				{
					Formula.runFormula(Float.parseFloat(length.getText()), Float.parseFloat(width.getText()));
				}
				
			}
			
		});
		return calc;


	}


	public AnchorPane addAnchorPane(GridPane paramGridPane)
	  {
	    AnchorPane localAnchorPane = new AnchorPane();
	    HBox localHBox = new HBox();
	    localHBox.setPadding(new Insets(0.0D, 10.0D, 10.0D, 10.0D));
	    localHBox.setSpacing(10.0D);
	    localHBox.setAlignment(Pos.BOTTOM_CENTER);
	    localAnchorPane.getChildren().addAll(new Node[] { paramGridPane, localHBox });
	    AnchorPane.setBottomAnchor(localHBox, Double.valueOf(8.0D));
	    AnchorPane.setRightAnchor(localHBox, Double.valueOf(5.0D));
	    AnchorPane.setTopAnchor(paramGridPane, Double.valueOf(10.0D));
	    return localAnchorPane;
	  }
	
	public void loadConfig(String url)
	{
		File f = new File(url);
		if(f.exists() && !f.isDirectory()){
			Log.log("Loading printer manager configuration");
			try {
				BufferedReader file = new BufferedReader(new FileReader(url));

				Map<String, Printer> map = new HashMap<String, Printer>();


				String line;
				while((line = file.readLine()) != null)
				{



					String[] parts = line.split(":");
					if(parts[0].equals("printer")){
						Log.log("Found printer: " + parts[1]);
						map.put(parts[1], new Printer(parts[1]));



					}else if(parts[0].equals("paper")){
						Log.log("Found paper: " + parts[2] + " Bound to: " + parts[1]);
						for (Map.Entry<String, Printer> entry : map.entrySet())
						{
							if(entry.getKey().equals(parts[1])){
								entry.getValue().addPaper(new Paper(parts[2], parts[1], Float.parseFloat(parts[3]), Float.parseFloat(parts[4]),Float.parseFloat( parts[5])));
							}
						}
					}



				}

				for (Map.Entry<String, Printer> entry : map.entrySet())
				{
					boundPrinters.put(entry.getValue().getName(), entry.getValue());
				}

				file.close();
			}
			catch (IOException e) {
				Log.log("IO Excecption: " + e.toString());
				dialogBox("Uh oh! Something went wrong, contact Admin");
				Platform.exit();
				System.exit(1);
			}
		} else {
			Log.log(url + " not found");
			dialogBox( url + " not found!");
		}




	}

	public void writeConfig(String url)
	{
		try {
			BufferedWriter file = new BufferedWriter(new FileWriter(url));

			file.write("# This is the printer configuration file for Vision.exe [Version "+ VERSION +"] , created by Jordan Knott (TheNightWolf) \n");
			file.write("# Printer values are stored as \"printer:[printer-name]\"\n");
			file.write("# Paper values are stored as \"paper:[bound-printer-name]:[paper-name]:[ink-cost]:[paper-cost]:[sqFtInPaper]\"\n");
			
			for(Printer p : boundPrinters.values())
			{	
				Log.log("Printer writen to config: " + p.getName());
				file.write("printer:" + p.getName() + "\n");
				
				for(Paper paper : p.getBoundPaper())
				{
					if(paper != null)
						Log.log("Paper writen to config: " + paper.toString());
						file.write(paper.toString() + "\n");
				}
				
			}
				
				
			
		
			file.close();
		
		} catch (IOException e) {
			Log.log("IO Exception: " + e.toString());
			e.printStackTrace();
		}
			
		
		
		
	}
	
	public void setShutdownProc()
	{
		Runtime.getRuntime().addShutdownHook(new Thread(){
			public void run()
			{
				Log.log("Client shutting down, closing log file and saving config");
				saveUserConfig();
				writeConfig(url);
				Log.log("Vision [" + VERSION + "] Exited");
				Log.close();
				
			}
		});
	}
	
	
	
	
	
	public Scene generateConfigPane()
	{
		Log.log("Generating config pane");
		GridPane config = new GridPane();
		
		config.setHgap(10);
		config.setVgap(10);
		
		
		
		int iter = 0;
		for (Map.Entry<String, Printer> entry : boundPrinters.entrySet())
		{
			
			ImageView tmpView = new ImageView(new Image(Vision.class.getResource("/printer.png").toString()));
			tmpView.setFitHeight(24);
			tmpView.setFitWidth(24);
			config.add(tmpView, 0, iter);
			config.add(new Label(entry.getValue().getName()), 1, iter);
			
			Button edit = new Button("Edit");
			edit.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					Stage dialog = new Stage();
					dialog.initOwner(stage);
					dialog.getIcons().add(new Image(Vision.class.getResource("/Vision.png").toString()));
					HBox fields = new HBox();
					Label nameL = new Label("(Warning: Children will be lost) Enter new name: ");
					TextField nameF = new TextField();
					Button confirm = new Button("Confirm");
					
					confirm.setOnAction(new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
							boundPrinters.remove(entry.getKey());
							boundPrinters.put(nameF.getText().trim(), new Printer(nameF.getText().trim()));
							dialog.close();
							stage.setScene(generateConfigPane());
						}
						
					});
					fields.getChildren().addAll( nameL, nameF, confirm);
					fields.setAlignment(Pos.CENTER);
					Scene dialogS = new Scene(fields, 450, 125);

					dialogS.setOnKeyReleased(new EventHandler<KeyEvent>(){

						@Override
						public void handle(KeyEvent arg0) {
							if(arg0.getCode() == KeyCode.ENTER)
							{
								boundPrinters.remove(entry.getKey());
								boundPrinters.put(nameF.getText().trim(), new Printer(nameF.getText().trim()));
								dialog.close();
								stage.setScene(generateConfigPane());
							}
							
						}
						
					});
					dialog.setScene(dialogS);
					dialog.show();
					
				}
				
			});
			config.add(edit, 2, iter);
			
			
			Button delete = new Button("Delete");
			delete.setOnAction(new EventHandler<ActionEvent>(){

				@Override
				public void handle(ActionEvent event) {
					Stage dialog = new Stage();
					dialog.initOwner(stage);
					dialog.getIcons().add(new Image(Vision.class.getResource("/Vision.png").toString()));
					VBox fields = new VBox();
					fields.setAlignment(Pos.CENTER);
					Label warning = new Label("Warning: All children will be lost");
					Button confirm = new Button("Confirm");
					confirm.setOnAction(new EventHandler<ActionEvent>(){

						@Override
						public void handle(ActionEvent arg0) {
	
							boundPrinters.remove(entry.getKey());
							
							Log.log("Printer deleted: " + entry.getKey());
							
							dialog.close();
							stage.setScene(generateConfigPane());
						}
						
					});
					fields.getChildren().addAll(warning, confirm);
					
					Scene dialogS = new Scene(fields, 350, 125);
					dialogS.setOnKeyReleased(new EventHandler<KeyEvent>(){

						@Override
						public void handle(KeyEvent arg0) {
							if(arg0.getCode() == KeyCode.ENTER)
							{
								boundPrinters.remove(entry.getKey());
								Log.log("Printer deleted: " + entry.getKey());
								dialog.close();
								stage.setScene(generateConfigPane());
							}
							
						}
						
					});
					dialog.setScene(dialogS);
					dialog.show();
					
				}
				
			});
			config.add(delete, 3, iter);
			
			iter++;
			
			for(Paper p : entry.getValue().getBoundPaper())
			{
				
				ImageView tmpViewP = new ImageView(new Image(Vision.class.getResource("/paper.png").toString()));
				tmpViewP.setFitHeight(32);
				tmpViewP.setFitWidth(32);
				config.add(tmpViewP, 0, iter);
				Label papername = new Label(p.name);
				ContextMenu menu = new ContextMenu();
				menu.getItems().addAll(new MenuItem("Bound Printer: " + p.boundPrinter), new MenuItem("Paper Cost: " + p.paperCost), new MenuItem("Ink Cost: " + p.inkCost), new MenuItem("SqFt: " + p.paperInSqFt));
				papername.setOnMouseEntered(new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent event) {
						menu.setX(event.getScreenX() + 25);
						menu.setY(event.getScreenY() + 25);
						menu.show(stage);
						
					}
					
				});
				papername.setOnMouseExited(new EventHandler<MouseEvent>(){

					@Override
					public void handle(MouseEvent event) {
						menu.hide();
						
					}
					
				});
				config.add(papername, 1, iter);
				
				Button editPaper = new Button("Edit");
				editPaper.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						Stage dialog = new Stage();
						dialog.initOwner(stage);
						dialog.getIcons().add(new Image(Vision.class.getResource("/Vision.png").toString()));
						GridPane fields = new GridPane();
						fields.setHgap(10);
						fields.setVgap(10);
						fields.setAlignment(Pos.CENTER);
						Label printerL = new Label("Available Printers: ");
						
						ComboBox<String> printers = new ComboBox<String>();
						printers.setValue(p.getBoundPrinter());
						
						
						for(Printer p : boundPrinters.values())
						{
							printers.getItems().add(p.getName());
						}

						fields.addRow(0, printerL, printers);
						
						Label nameL = new Label("Enter name: ");
						TextField nameF = new TextField();
						nameF.setText(p.getName());
						
						fields.addRow(1, nameL, nameF);
						
						Label costInkL = new Label("Enter cost of ink: ");
						TextField costInkF = new TextField();
						costInkF.setText(p.getInkCost()  + "");
					
						fields.addRow(2, costInkL, costInkF);
						
						Label costPaperL = new Label("Enter cost of paper: ");
						TextField costPaperF = new TextField();
						costPaperF.setText(p.getPaperCost() + "");
						
						fields.addRow(3, costPaperL, costPaperF);
						
						Label sqFtPaperL = new Label("Enter square feet in paper: ");
						TextField sqFtPaperF = new TextField();
						sqFtPaperF.setText(p.getPaperInSqFt() + "");
						
						fields.addRow(4, sqFtPaperL, sqFtPaperF);
						
						
						
						Button confirm = new Button("Confirm");
						confirm.setOnAction(new EventHandler<ActionEvent>(){

							@Override
							public void handle(ActionEvent arg0) {
								
								boundPrinters.get(printers.getValue()).addPaper(new Paper(nameF.getText().trim() , printers.getValue().toString(), Float.parseFloat(costInkF.getText().trim()), Float.parseFloat(costPaperF.getText().trim()), Float.parseFloat(sqFtPaperF.getText().trim())));
								
								dialog.close();
								stage.setScene(generateConfigPane());
							}
							
						});
						fields.add(confirm, 0, 5);
						
						Scene dialogS = new Scene(fields, 350, 175);
						
						dialogS.setOnKeyReleased(new EventHandler<KeyEvent>(){

							@Override
							public void handle(KeyEvent arg0) {
								if(arg0.getCode() == KeyCode.ENTER)
								{
									boundPrinters.get(printers.getValue()).removePaper(p);
									boundPrinters.get(printers.getValue()).addPaper(new Paper(nameF.getText().trim() , printers.getValue().toString(), Float.parseFloat(costInkF.getText().trim()), Float.parseFloat(costPaperF.getText().trim()), Float.parseFloat(sqFtPaperF.getText().trim())));
									
									dialog.close();
									stage.setScene(generateConfigPane());
								}
								
							}
							
						});
						
						dialog.setScene(dialogS);
						dialog.show();
					}
					
				});
				
				config.add(editPaper, 2, iter);
				
				Button paperDelete = new Button("Delete");
				paperDelete.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent event) {
						Stage dialog = new Stage();
						dialog.initOwner(stage);
						dialog.getIcons().add(new Image(Vision.class.getResource("/Vision.png").toString()));
						VBox fields = new VBox();
						fields.setAlignment(Pos.CENTER);
						Label warning = new Label("Warning: All children will be lost");
						Button confirm = new Button("Confirm");
						confirm.setOnAction(new EventHandler<ActionEvent>(){

							@Override
							public void handle(ActionEvent arg0) {
		
								entry.getValue().getBoundPaper().remove(p);
								Log.log("Paper deleted: " + p.toString());
								dialog.close();
								stage.setScene(generateConfigPane());
							}
							
						});
						fields.getChildren().addAll(warning, confirm);
						
						Scene dialogS = new Scene(fields, 350, 125);
						dialogS.setOnKeyReleased(new EventHandler<KeyEvent>(){

							@Override
							public void handle(KeyEvent arg0) {
								if(arg0.getCode() == KeyCode.ENTER)
								{
									entry.getValue().getBoundPaper().remove(p);
									Log.log("Paper deleted: " + p.toString());
									dialog.close();
									stage.setScene(generateConfigPane());
								}
								
							}
							
						});
						dialog.setScene(dialogS);
						dialog.show();
						
					}
					
				});
				config.add(paperDelete, 3, iter);
				iter++;
			}
			
			
		
		}
		
		Button add = new Button("Add Printer");
		add.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				Stage dialog = new Stage();
				dialog.initOwner(stage);
				dialog.getIcons().add(new Image(Vision.class.getResource("/Vision.png").toString()));
				HBox fields = new HBox();
				fields.setAlignment(Pos.CENTER);
				Label nameL = new Label("Enter name: ");
				TextField nameF = new TextField();
				Button confirm = new Button("Confirm");
				confirm.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						boundPrinters.put(nameF.getText().trim(), new Printer(nameF.getText().trim()));
						Log.log("New Printer: " + nameF.getText().trim());
						dialog.close();
					}
					
				});
				fields.getChildren().addAll(nameL, nameF, confirm);
				
				Scene dialogS = new Scene(fields, 350, 125);
				dialogS.setOnKeyReleased(new EventHandler<KeyEvent>(){

					@Override
					public void handle(KeyEvent arg0) {
						if(arg0.getCode() == KeyCode.ENTER)
						{
							boundPrinters.put(nameF.getText().trim(), new Printer(nameF.getText().trim()));
							Log.log("New Printer: " + nameF.getText().trim());
							dialog.close();
							stage.setScene(generateConfigPane());
						}
						
					}
					
				});
				dialog.setScene(dialogS);
				dialog.show();
			}
			
		});
		
		config.add(add, 0, iter);
		
		Button addPaper = new Button("Add Paper");
		addPaper.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				Stage dialog = new Stage();
				dialog.initOwner(stage);
				dialog.getIcons().add(new Image(Vision.class.getResource("/Vision.png").toString()));
				GridPane fields = new GridPane();
				fields.setVgap(10);
				fields.setHgap(10);
				fields.setAlignment(Pos.CENTER);
				Label printerL = new Label("Available Printers: ");
				
				ComboBox<String> printers = new ComboBox<String>();
				
				for(Printer p : boundPrinters.values())
				{
					printers.getItems().add(p.getName());
				}

				fields.addRow(0, printerL, printers);
				
				Label nameL = new Label("Enter name: ");
				TextField nameF = new TextField();
				
				fields.addRow(1, nameL, nameF);
				
				Label costInkL = new Label("Enter cost of ink: ");
				TextField costInkF = new TextField();
			
				fields.addRow(2, costInkL, costInkF);
				
				Label costPaperL = new Label("Enter cost of paper: ");
				TextField costPaperF = new TextField();
			
				fields.addRow(3, costPaperL, costPaperF);
				
				Label sqFtPaperL = new Label("Enter square feet in paper: ");
				TextField sqFtPaperF = new TextField();
			
				fields.addRow(4, sqFtPaperL, sqFtPaperF);
				
				
				
				Button confirm = new Button("Confirm");
				confirm.setOnAction(new EventHandler<ActionEvent>(){

					@Override
					public void handle(ActionEvent arg0) {
						boundPrinters.get(printers.getValue()).addPaper(new Paper(nameF.getText().trim() , printers.getValue().toString(), Float.parseFloat(costInkF.getText().trim()), Float.parseFloat(costPaperF.getText().trim()), Float.parseFloat(sqFtPaperF.getText().trim())));
						Log.log("New Paper: " + nameF.getText().trim() + " : " + printers.getValue().toString() + " : " +  Float.parseFloat(costInkF.getText().trim()) + " : " + Float.parseFloat(costPaperF.getText().trim()) + " : " +  Float.parseFloat(sqFtPaperF.getText().trim()));
						dialog.close();
						stage.setScene(generateConfigPane());
					}
					
				});
				fields.add(confirm, 0, 5);
				
				Scene dialogS = new Scene(fields, 350, 200);
				dialogS.setOnKeyReleased(new EventHandler<KeyEvent>(){

					@Override
					public void handle(KeyEvent arg0) {
						if(arg0.getCode() == KeyCode.ENTER)
						{
							boundPrinters.get(printers.getValue()).addPaper(new Paper(nameF.getText().trim() , printers.getValue().toString(), Float.parseFloat(costInkF.getText().trim()), Float.parseFloat(costPaperF.getText().trim()), Float.parseFloat(sqFtPaperF.getText().trim())));
							Log.log("New Paper: " + nameF.getText().trim() + " : " + printers.getValue().toString() + " : " +  Float.parseFloat(costInkF.getText().trim()) + " : " + Float.parseFloat(costPaperF.getText().trim()) + " : " +  Float.parseFloat(sqFtPaperF.getText().trim()));
							dialog.close();
							stage.setScene(generateConfigPane());
						}
						
					}
					
				});
				
				dialog.setScene(dialogS);
				dialog.show();
			}
			
		});
		
		config.add(addPaper, 1, iter);
		
		Button back = new Button("Back to Calc");
		back.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent event) {
				stage.setScene(generateCalcWindow());
			}
			
		});
		config.add(back, 2, iter);
		config.setAlignment(Pos.CENTER);
		
		ScrollPane scroll = new ScrollPane(config);
		scroll.setFitToHeight(true);
		scroll.setFitToWidth(true);
		scroll.setPrefSize(750, 350);
		GridPane tmp = new GridPane();
		tmp.add(scroll, 0, 0);
		tmp.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(tmp, 750, 350);
		scene.getRoot().setStyle("");
		return scene;
		
	}
	
	public void dialogBox(String message)
	{
		JOptionPane.showMessageDialog(null, message);
	}
	
	public void dialogBoxFX(String message)
	{
		Stage dialogBox = new Stage();
		dialogBox.initOwner(stage);
		dialogBox.getIcons().add(new Image(Vision.class.getResource("/Vision.png").toString()));
		GridPane core = new GridPane();
		core.setAlignment(Pos.CENTER);
		core.add(new Label(message), 0, 0);
		Button button = new Button("Okay");
		button.setOnAction(new EventHandler<ActionEvent>(){

			@Override
			public void handle(ActionEvent arg0) {
				dialogBox.close();
			}
			
		});
		core.add(button, 1, 0);
		dialogBox.show();
	}
		
}
