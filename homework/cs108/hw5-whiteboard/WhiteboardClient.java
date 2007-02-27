import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class WhiteboardClient {
   WhiteboardCanvas canvas;
   Socket clientSocket;
   Thread listeningThread;
   int port;
   String host;
   
   public WhiteboardClient(WhiteboardCanvas canvas, String host, int port) throws UnknownHostException, IOException {
	   this.canvas = canvas;
	   this.port = port;
	   openSocket();
   }
   
   synchronized protected void addNewClient(Socket csock) {
	   
   }
   
   protected void openSocket() throws UnknownHostException, IOException
   {
	   clientSocket = new Socket(host, port);
		
	   listeningThread = new Thread() {
		   public void run() {
			   listenForIncomingCommands();
			   while (true) {
			   }
		   }
	   };
	   listeningThread.start();
   }
   
   protected void processCommand(String command, DShapeModel model) {
	   DShape localShape = canvas.findShapeById(model.getId());
	   if (command.equals("add"))
	   {
		   canvas.addShape(model);
	   }
	   else if (command.equals("remove"))
	   {
		   canvas.removeShape(localShape);
	   }
	   else if (command.equals("front"))
	   {
		   canvas.moveShapeToFront(localShape);
	   }
	   else if (command.equals("back"))
	   {
		   canvas.moveShapeToBack(localShape);
	   }
	   else if (command.equals("change"))
	   {
		   localShape.getModel().mimic(model);
	   }
   }
   
   protected void listenForIncomingCommands() {
	   try {
		   ObjectInputStream istream = new ObjectInputStream(clientSocket.getInputStream());
		   while (true) {
			   String command = (String) istream.readObject(); // read the command
			   DShapeModel model = (DShapeModel) istream.readObject(); // read the model
			   processCommand(command, model);
		   }
	   
	   } catch (IOException e) {
		  handleException(e);
	   } catch (ClassNotFoundException e) {
		e.printStackTrace();
	   }
   }
   
   protected void handleException(IOException e) {
	   e.printStackTrace();
   }
}
