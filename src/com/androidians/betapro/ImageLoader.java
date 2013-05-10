package com.androidians.betapro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Currency;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * @author Kayvan
 *
 */
public class ImageLoader {
	private ImageView iv;
	/**
	 * Loads a given image URL to the image view using separated threat
	 * Sample usage: new ImageLoader((ImageView)findViewById(R.id.testiv), "http://www.androidians.comuf.com/BetaProFiles/icon.png");
	 * @param iv The targeted imageView
	 * @param imageUrl URL String of the image ex: http://www.androidians.comuf.com/BetaProFiles/icon.png
	 */
	public ImageLoader(ImageView iv, String imageUrl) {
		// TODO Auto-generated constructor stub
		this.iv = iv;
		new loadIcons().execute(imageUrl);
	}
	class loadIcons extends AsyncTask<String, String, Bitmap>{
		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			boolean notLoaded = true;
			File mFile = null;
			URL newurl;
			Bitmap mIcon_val= null;
			Long t = System.currentTimeMillis();
			while (mIcon_val==null) {
				try {
					newurl = new URL(params[0]);
					mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());	
				
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (System.currentTimeMillis()>t+1000) {
					try {
						newurl = new URL("http://192.168.1.3/BetaProFiles/error.png");
						mIcon_val = BitmapFactory.decodeStream(newurl.openConnection().getInputStream());
					}catch(MalformedURLException e){
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return mIcon_val;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			//ImageView iv = (ImageView)findViewById(R.id.testiv);
			iv.setImageBitmap(result);
			 
			//iv.setImageDrawable(Drawable.createFromPath());
			super.onPostExecute(result);
		}
		
	}
}
