import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;


public class WhiteboardServer {
   WhiteboardCanvas canvas;
   ServerSocket serverSocket;
   Thread listeningThread;
   int port;
   Collection<ServerClient> clients;
   
   protected class ServerClient { 
	   Socket socket;
	   ObjectOutputStream objstream;
	   ServerClient(Socket csock) throws IOException 
	   {
		   socket = csock;
		   objstream = new ObjectOutputStream(socket.getOutputStream());
	   }
   }
   
   public WhiteboardServer(WhiteboardCanvas canvas, int port) {
	   this.canvas = canvas;
	   this.port = port;
	   openSocket();
	   clients = new LinkedList<ServerClient>();
	   
	   canvas.addShapeListListener(new WhiteboardCanvas.ShapeListListener() {

		public void shapeAdded(DShape shape) {
			sendObjectToEachClient("add");
			sendObjectToEachClient(shape.getModel());
			flushEachClient();
			beginListeningToShape(shape.getModel());
		}

		public void shapeRemoved(DShape shape) {
			sendObjectToEachClient("remove");
			sendObjectToEachClient(shape.getModel());
			flushEachClient();
		}  
	   });
	   
	   canvas.addZOrderListener(new WhiteboardCanvas.ZOrderChangeListener() {

		   public void shapedMovedToBack(DShape shape) {
				sendObjectToEachClient("back");
				sendObjectToEachClient(shape.getModel());
				flushEachClient();
			}
	
			public void shapedMovedToFront(DShape shape) {
				sendObjectToEachClient("front");
				sendObjectToEachClient(shape.getModel());
				flushEachClient();
			}
	   });
	   
	   for (DShape shape : canvas.getShapes())
		   beginListeningToShape(shape.getModel());
   }
   
   protected void beginListeningToShape(DShapeModel model) {
	   model.addChangeListener(new DShapeModel.ModelListener() {

		public void modelChanged(DShapeModel model) {
			sendObjectToEachClient("change");
			sendObjectToEachClient(model);
			flushEachClient();
		}
		
	   });
   }
   
   /**
    * Propagates the object across  each client connection.
    * @param obj
    */
   protected void sendObjectToEachClient(Object obj) {
	   for (ServerClient client : clients)
	   {
		   try {
			client.objstream.writeUnshared(obj);
			
		} catch (IOException e) {
			handleException(e);
		}
	   }
   }
   
   /**
    * Propagates the object across  each client connection.
    * @param obj
    */
   protected void flushEachClient() {
	   for (ServerClient client : clients)
	   {
		   try {
			client.objstream.flush();
			
		} catch (IOException e) {
			handleException(e);
		}
	   }
   }
   
   synchronized protected void addNewClient(Socket csock) {
	   try {
		clients.add(new ServerClient(csock));
	} catch (IOException e) {
		handleException(e);
	}
   }
   
   protected void openSocket()
   {
	   try {
		   serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			handleException(e);
		}
		
	   listeningThread = new Thread() {
		   public void run() {
			   while (true) {
				   try {
					   // accept an incoming request as a new socket
					   Socket clientSocket = serverSocket.accept();
					   addNewClient(clientSocket);
					} catch (IOException e) {
						handleException(e);
					}
			   }
		   }
	   };
	   listeningThread.start();
   }
   

   
   public void stop() {
	   try {
		serverSocket.close();
	    listeningThread.join();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   protected void handleException(IOException e) {
	   e.printStackTrace();
   }
}
