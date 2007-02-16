import java.io.*;
import java.net.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class WebWorker extends Thread {
	
	public class Status {
		protected String description;
		protected int totalBytes;
		protected int downloadedBytes;
		protected ErrorState errorState;
		public static enum ErrorState { OK, INTERRUPTED, IOERROR, ERROR };
		/**
		 * 
		 * @return number between 0 and 1 indicating the percentage of the download
		 * that's complete
		 */
		public float getCompletionRatio()
		{
			return (float)downloadedBytes / (float)totalBytes;
		}
		public String toString()
		{
		}
	}
	
	protected String url;
	WebFrame frame;
	int row;
	protected Status downloadStatus;
	
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
 		String status = "default status";
		InputStream input = null;
		StringBuilder contents = null;
		try {
			Date timeStart = new Date();
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			int sizeOfContent = connection.getContentLength();
		
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
				if (isInterrupted())
					throw new InterruptedException();
			}
			
			// Successful download if we get here
			int byteCount = contents.length();
			Date timeEnd = new Date();
			long elapsedMilliseconds = timeEnd.getTime() - timeStart.getTime();
			String formattedTimeEnd = new SimpleDateFormat("h:mm").format(timeEnd);
			status = formattedTimeEnd + " " + elapsedMilliseconds + "ms "
				+ byteCount + " bytes";
		
			frame.registerDownloaderCeased(this, true);
			
		}
		// Otherwise control jumps to a catch...
		catch(MalformedURLException ignored) {
			frame.registerDownloaderCeased(this, false);
			status = "Malformed URL";
		}
		catch(IOException ignored) {
			frame.registerDownloaderCeased(this, false);
			status = "err, io exception";
		}
		catch(InterruptedException exception) {
			frame.registerDownloaderCeased(this, false);
			status = "interrupted";
			// TODO: is there anything I really need to put here? YOUR CODE HERE
		}
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
