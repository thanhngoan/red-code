import java.io.*;
import java.net.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class WebWorker extends Thread {
	
	protected String url;
	WebFrame frame;
	int row;
	public WebWorker(WebFrame frame, String url, int row)
	{
		this.row = row;
		this.frame = frame;
		this.url = url;
	}
	
	public void run()
	{
		download(url);
	}
	
	protected int getTimeout() { return 5000; }
 	public void download(String urlString) {
 		String status = "error";
		InputStream input = null;
		StringBuilder contents = null;
		try {
			Date timeStart = new Date();
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
		
			// Set connect() to throw an IOException
			// if connection does not succeed in this many msecs.
			connection.setConnectTimeout(getTimeout());
			
			connection.connect();
			input = connection.getInputStream();

			BufferedReader reader  = new BufferedReader(new InputStreamReader(input));
		
			char[] array = new char[1000];
			int len;
			contents = new StringBuilder(1000);
			while ((len = reader.read(array, 0, array.length)) > 0) {
				contents.append(array, 0, len);
				Thread.sleep(100);
			}
			
			// Successful download if we get here
			int byteCount = contents.length();
			Date timeEnd = new Date();
			long elapsedMilliseconds = timeEnd.getTime() - timeStart.getTime();
			String formattedTimeEnd = new SimpleDateFormat("h:mm").format(timeEnd);
			status = formattedTimeEnd + " " + elapsedMilliseconds + "ms "
				+ byteCount + " bytes";
			
		}
		// Otherwise control jumps to a catch...
		catch(MalformedURLException ignored) {}
		catch(InterruptedException exception) {
			// YOUR CODE HERE
			// deal with interruption
		}
		catch(IOException ignored) {}
		// "finally" clause, to close the input stream
		// in any case
		finally {
			frame.setStatus(row, status);
			try{
				if (input != null) input.close();
			}
			catch(IOException ignored) {}
		}
 	}
}
