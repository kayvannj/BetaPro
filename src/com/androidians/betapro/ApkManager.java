package com.androidians.betapro;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

/**
 * @author Kayvan
 *
 */
public class ApkManager {

	public ApkManager() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Downloads the Apk file.
	 * <p>
	 * String fileLocation= ApkManager.downloadApk(context, appName, "http://www.androidians.comuf.com/BetaProFiles/"+appName);
	 * @param context Context
	 * @param AppName the name of the package that needs to be download. ex: myApk.apk
	 * @param APKAddress the URL address for the package to download from. ex: http://www.androidians.comuf.com/BetaProFiles/myApk.apk
	 * @return The path address for the downloaded file (in the external storage, assuming there is one)
	 */
	public static String downloadApk(Context context, String AppName,String APKAddress){
		
		
		DownloadManager.Request r = new DownloadManager.Request(Uri.parse(APKAddress));

		// This put the download in the same Download dir the browser uses
		r.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, AppName);
		
		// When downloading music and videos they will be listed in the player
		// (Seems to be available since Honeycomb only)
		// r.allowScanningByMediaScanner();

		// Notify user when download is completed
		// (Seems to be available since Honeycomb only)
		r.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
		
		// Start download
		DownloadManager dm = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
		dm.enqueue(r);
		
		//todo: check for external storage and handle exception
		
		return Environment.getExternalStorageDirectory()+"/"+Environment.DIRECTORY_DOWNLOADS+"/"+AppName;
	}
	
	/**
	 * Lunches an intent to install the downloaded application package
	 * <p>
	 * File application = new File(fileLocation);
	 * ApkManager.installApk(context,Uri.fromFile(application));
	 * @param ApkUri Uri object of the downloaded file. 
	 * @param context Context
	 */
	public static void installApk(Context context,Uri ApkUri){
		Intent promptInstall = new Intent(Intent.ACTION_VIEW);
		promptInstall.setDataAndType(ApkUri,"application/vnd.android.package-archive");
		promptInstall.addFlags(promptInstall.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(promptInstall); 
		
//		context.startActivity(new Intent(Intent.ACTION_VIEW, ApkUri));
	}
}
