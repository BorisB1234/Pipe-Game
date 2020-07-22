package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainWindowLogic implements Initializable {

	char[][] pipesData = {
			{'F',' '},
			{'F','F'},
			
	};
	@FXML TextField txtIp;
	@FXML TextField txtPort;
	@FXML PipeGrid grid;
	@FXML BorderPane borderPane;

	
			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		grid.setPipeBoard(pipesData);
		
		LoadLevel();
		
		grid.widthProperty().addListener((obs,old,newval)->LoadLevel());
		grid.heightProperty().addListener((obs,old,newval)->LoadLevel());
	}
	
	
	
	
	public void LoadLevel()
	{
		grid.getChildren().clear();
		grid.load();
		
	}
	
	
	public void start()
	{

		ExecutorService exec;

		CompletableFuture<ArrayList<String>> future;
		
		
		exec = Executors.newSingleThreadExecutor();
		future = CompletableFuture.supplyAsync(()->{
			char[] line;
			ArrayList<String> actions = new ArrayList<String>(); 
			String Line2Read;
			Socket s = null;
			try {
				s = new Socket(txtIp.getText(),Integer.parseInt(txtPort.getText()));
			
				PrintWriter writer = new PrintWriter(s.getOutputStream());
				BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
				pipesData = grid.getPipeBoard();
				for(int i = 0 ; i < pipesData.length; i++)
				{
					line = pipesData[i];
					writer.println(String.valueOf(line));
				}
				writer.println("done");
				writer.flush();
				
				while((Line2Read = reader.readLine()) != null && !Line2Read.equals("done"))
				{
					actions.add(Line2Read);
				}
				reader.close();
				writer.close();
				
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return actions;
		},exec);
		future.thenAccept(array -> grid.SolvePuzzle(array));
		
	
	}
	
	
		
		
	




	public void OpenFile()
	{
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Pipes file");
		fc.setInitialDirectory(new File("./Levels"));
		fc.getExtensionFilters().addAll(new ExtensionFilter("JPG","*.txt"));
		File chosen = fc.showOpenDialog(null);
		if(chosen != null)
		{
			SetLevel(chosen);
		}
	}




	private void SetLevel(File chosen) {
		BufferedReader buffer;
		String line;
		int col;
		
		ArrayList<String> list = new ArrayList<String>();
		try {
			buffer = new BufferedReader(new FileReader(chosen));
			while((line = buffer.readLine()) != null && !line.equals("done"))
			{
				list.add(line);
			}
			if(list.isEmpty())
			{
				buffer.close();
				throw new IOException("Empty buffer");
			}
			col = getListStringSize(list);
			if(col == -1)
			{
				buffer.close();
				throw new IOException("Not a matrix");
			}
			pipesData = new char[list.size()][col];
			for(int i = 0; i < list.size(); i++)
			{
				pipesData[i] = list.get(i).toCharArray();
			}
			buffer.close();
			grid.setPipeBoard(pipesData);
			LoadLevel();
		} catch (IOException e) {
			
			System.out.println(e.getMessage());
		}
	}
	
	private int getListStringSize(ArrayList<String> list) throws IOException
	{
		int size = list.get(0).length();
		for(String s: list)
		{
			
			if(size!=s.length())
			{
				return -1;
			}
		}
		return size;
	}

	
}
