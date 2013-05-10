package com.androidians.betapro;

import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferListener;

import java.io.File;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * @author Kayvan
 *
 */
public class MyFTP {
	/*private String USERNAME = "a9082436";
	private String PASSWORD = "admin@androidians";
	private String SERVER   = "androidians.comuf.com";*/
	private String SERVER   = "192.168.1.3";
	private String USERNAME = "admin";
	private String PASSWORD = "pass";
	private String SERVER_PATH = "BetaProFiles";
	private String CLIENT_PATH = "BetaPro";
	private String download_to = "";
	private File file;
	private FTPClient f;
	
	ProgressDialog pd;
	Context context;
	
	public MyTransferListener listener;
	
	/**
	 * For uploading files using FTP connection
	 * <p>
	 * new MyFTP(context).uploadFile(new File(path));
	 * @param con
	 */
	public MyFTP(Context con) {
		// TODO Auto-generated constructor stub
		
		context = con;
		
	}
	
	/**
	 * @param file
	 */
	public void uploadFile(File file){
		this.file = file;
		pd = new ProgressDialog(context);
		pd.setTitle("Upload");
		pd.setMessage("Uploading, please wait.");
		pd.setCancelable(true);
		//pd.setDismissMessage("Upload completed");
		pd.show();
		new uploadTask().execute();
	}
	/**
	 * @param fileName
	 * @return
	 */
/*	public File downloadFile(String fileName){
		File root = android.os.Environment.getExternalStorageDirectory();               

        File dir = new File(root.getAbsolutePath() + CLIENT_PATH);
        
        if(dir.exists()==false) {
             dir.mkdirs();
        }
		download_to = root.getAbsolutePath() + CLIENT_PATH;
		
		//new downloadTask().execute(fileName);
		File downloadedFile = new File(download_to+"/"+fileName);
		
		return downloadedFile;
	}*/

	class uploadTask extends AsyncTask<String, String, String>{
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			FTPClient f = new FTPClient();
			try {
				f.connect(SERVER);
			    f.login(USERNAME, PASSWORD);
			    try {
			    	f.changeDirectory(SERVER_PATH);
				} catch (Exception e) {
					// TODO: handle exception
					f.createDirectory(SERVER_PATH);
					f.changeDirectory(SERVER_PATH);
				}
			    //f.changeDirectory(SERVER_PATH);
			    listener = new MyTransferListener();
			    f.upload(file, listener);
			    f.setPassive(true);//no incoming signal required for passive mode
			    f.disconnect(false);//with out sending advice to server
			    return "success";//f.list().toString();
			} catch (Exception e) {
				// TODO: handle exception
			    Log.e("FTP", e.toString()) ;
			    return "failed :"+e.toString();
			}
			
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			if (result.equals("success")) {
				pd.setTitle("Uploaded");
				pd.dismiss();
			}else{
				//pd.setTitle("Error");
				//pd.setMessage(result);
				pd.dismiss();
			}
			
			super.onPostExecute(result);
			
		}
	}
	/*class downloadTask extends AsyncTask<String, String, String>{
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			FTPClient f = new FTPClient();
			try {
				f.connect(SERVER);
			    f.login(USERNAME, PASSWORD);
			    //f.createDirectory("BetaProFiles");
			    f.changeDirectory(SERVER_PATH);
			    f.download(params[0],new File(download_to+"/"+params[0]), listener);
			    f.disconnect(false);
			    return "";//f.list().toString();
			} catch (Exception e) {
				// TODO: handle exception
			    return e.toString();
			}
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pd.dismiss();
		}
	}*/

	public class MyTransferListener implements FTPDataTransferListener {

		public void started() {
			// Transfer started
			Log.d("FTP","Transfer Started");
			//Toast.makeText(context, "Transfer Started", Toast.LENGTH_SHORT);
			
		}

		public void transferred(int length) {
			// Yet other length bytes has been transferred since the last time this
			// method was called
			Log.d("FTP","Transfer "+length);
			pd.setProgress(length);
		}

		public void completed() {
			// Transfer completed
			Log.d("FTP","Transfer Completed");
			pd.dismiss();
			//Toast.makeText(context, "uploaded", Toast.LENGTH_LONG).show();
		}

		public void aborted() {
			// Transfer aborted
			Log.d("FTP","Transfer Aborted");
		}

		public void failed() {
			// Transfer failed
			Log.d("FTP","Transfer Failed");
		}

	}
}
