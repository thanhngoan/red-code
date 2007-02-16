import java.io.*;
import java.net.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;

public class WebWorker extends Thread {
	public final static int SLEEP_TIME = 300;
	public static enum ErrorState { OK, INTERRUPTED, ERROR };
	
	public class Status {
		protected String description;
		protected int totalBytes;
		protected int downloadedBytes;
		protected Date timeStart;
		protected Date timeEnd;
		protected ErrorState errorState;
		protected boolean finished;
		/**
		 * 
		 * @return number between 0 and 1 indicating the percentage of the download
		 * that's complete
		 */
		public float getCompletionRatio()
		{
			if (totalBytes > 0)
			{
				return (float)downloadedBytes / (float)totalBytes;
			}
			else
			{
				if (finished)
					return 1;
				else
					return 0;
			}
		}
		protected void setFinished()
		{
			finished = true;
			timeEnd = new Date();
			long elapsedMilliseconds = timeEnd.getTime() - timeStart.getTime();
			String formattedTimeEnd = new SimpleDateFormat("h:mm").format(timeEnd);
			description =  formattedTimeEnd + " " + elapsedMilliseconds + "ms "
				           + downloadedBytes + " bytes";
		}
		public String toString()
		{
			return description;
		}
		public boolean getWasInterrupted() { return errorState == ErrorState.INTERRUPTED; }
		public boolean getEncounteredError() { return errorState == ErrorState.ERROR; }
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
		downloadStatus = new Status();
		
	}
	
	public void run()
	{
		download(url);
	}
	
	protected int getTimeout() { return 5000; }
 	public void download(String urlString) {
		InputStream input = null;
		StringBuilder contents = null;
		try {
			downloadStatus.timeStart = new Date();
			downloadStatus.errorState = ErrorState.OK;
			URL url = new URL(urlString);
			URLConnection connection = url.openConnection();
			downloadStatus.totalBytes = connection.getContentLength();
		
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
				downloadStatus.downloadedBytes = contents.length();
				frame.updateStatus(row, downloadStatus);
				
				Thread.sleep(SLEEP_TIME);
				if (isInterrupted())
					throw new InterruptedException();
			}
			
			// Successful download if we get here
			downloadStatus.setFinished();
		
			frame.registerDownloaderCeased(this, true);
			
		}
		// Otherwise control jumps to a catch...
		catch(MalformedURLException ignored) {
			downloadStatus.errorState = ErrorState.ERROR;
			downloadStatus.description = "Malformed URL";
			frame.registerDownloaderCeased(this, false);
		}
		catch(IOException ignored) {
			downloadStatus.errorState = ErrorState.ERROR;
			downloadStatus.description  = "err, io exception";
			frame.registerDownloaderCeased(this, false);
		}
		catch(InterruptedException exception) {
			downloadStatus.errorState = ErrorState.INTERRUPTED;
			downloadStatus.description = "interrupted";
			frame.registerDownloaderCeased(this, false);
		}
		catch (OutOfMemoryError exception)
		{
			downloadStatus.errorState = ErrorState.ERROR;
			downloadStatus.description  = "Out of memory.  Large download?";
			frame.registerDownloaderCeased(this, false);
		}
		// "finally" clause, to close the input stream
		// in any case
		finally {
			frame.updateStatus(row, downloadStatus);
			try{
				if (input != null) input.close();
			}
			catch(IOException ignored) {}
		}
 	}
}
