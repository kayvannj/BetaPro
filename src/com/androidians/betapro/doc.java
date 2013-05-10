package com.androidians.betapro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Toast;



public class doc extends Activity {
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doc);
		HorizontalScrollView hs = (HorizontalScrollView) findViewById(R.id.scrollView);
		hs.setHorizontalScrollBarEnabled(false);
		Button back = (Button) findViewById(R.id.helpBackBtn);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
		
		//WebView webview =(WebView) findViewById(R.id.doc);
		//webview.setBackgroundColor(0);
		/*
		String baseURL ="<!DOCTYPE html>"+
		"<html>"+
		"<head>"+
		"<style>"+
		"nav{}"+
		"nav ul li {}"+

		"</style></head><body><nav>"+
		"<ul>"+
				"<li><a href='#1'>Reviewer</a>"+
					"<ul>"+
						"<li><a href='#2'>Download</a></li>"+
						"<li><a href='#3'>Write</a></li>"+
						"<li><a href='#4'>Get Paid</a></li>"+
					"</ul>"+
				"</li>"+
				"<li><a href='#5'>Developer</a>"+
				"	<ul>"+
					"	<li><a href='#6'>Upload</a></li>"+
					"	<li><a href='#7'>Read</a></li>"+
					"	<li><a href='#8'>Pay</a></li>"+
				"	</ul>"+
				"</li>"+
				
			"</ul>"+
		"</nav>"+
		"<div style='clear:both'></div> "+
		"<h2 id='h21'><a id='1'>Reviewer</a></h2>"+
		"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pulvinar gravida lorem, quis consectetur magna rhoncus vel. Donec scelerisque turpis vel elit tempor sollicitudin. Vivamus tellus turpis, molestie eget porta eget, mattis vitae arcu. Sed dignissim adipiscing ante sit amet consectetur. Aliquam nibh sapien, porta vitae feugiat id, elementum sed dui. Pellentesque habitant morbi tristique senectus et netus et"+ 
		"malesuada fames ac turpis egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean rutrum pulvinar nulla, eu vehicula turpis venenatis eget. Vivamus elit risus, sodales id accumsan id, elementum nec leo. Vivamus sed libero dui, ac dignissim metus. Fusce nunc odio, euismod vel porttitor blandit, convallis in augue. Curabitur adipiscing elementum scelerisque. Vivamus augue "+
		"justo, pharetra et convallis a, tincidunt vel quam.</p>"+

		"<h2><a id='2'>Download</a></h2>"+
		"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pulvinar gravida lorem, quis consectetur magna rhoncus vel. Donec scelerisque turpis vel elit tempor sollicitudin. Vivamus tellus turpis, molestie eget porta eget, mattis vitae arcu. Sed dignissim adipiscing ante sit amet consectetur. Aliquam nibh sapien, porta vitae feugiat id, elementum sed dui. Pellentesque habitant morbi tristique senectus et netus et "+
		"malesuada fames ac turpis egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean rutrum pulvinar nulla, eu vehicula turpis venenatis eget. Vivamus elit risus, sodales id accumsan id, elementum nec leo. Vivamus sed libero dui, ac dignissim metus. Fusce nunc odio, euismod vel porttitor blandit, convallis in augue. Curabitur adipiscing elementum scelerisque. Vivamus augue "+
		"justo, pharetra et convallis a, tincidunt vel quam.</p>"+

		"<h2><a id='3'>Write</a></h2>"+
		"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pulvinar gravida lorem, quis consectetur magna rhoncus vel. Donec scelerisque turpis vel elit tempor sollicitudin. Vivamus tellus turpis, molestie eget porta eget, mattis vitae arcu. Sed dignissim adipiscing ante sit amet consectetur. Aliquam nibh sapien, porta vitae feugiat id, elementum sed dui. Pellentesque habitant morbi tristique senectus et netus et "+
		"malesuada fames ac turpis egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean rutrum pulvinar nulla, eu vehicula turpis venenatis eget. Vivamus elit risus, sodales id accumsan id, elementum nec leo. Vivamus sed libero dui, ac dignissim metus. Fusce nunc odio, euismod vel porttitor blandit, convallis in augue. Curabitur adipiscing elementum scelerisque. Vivamus augue "+
		"justo, pharetra et convallis a, tincidunt vel quam.</p>"+

		"<h2><a id='4'>Get Paid</a></h2>"+
		"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pulvinar gravida lorem, quis consectetur magna rhoncus vel. Donec scelerisque turpis vel elit tempor sollicitudin. Vivamus tellus turpis, molestie eget porta eget, mattis vitae arcu. Sed dignissim adipiscing ante sit amet consectetur. Aliquam nibh sapien, porta vitae feugiat id, elementum sed dui. Pellentesque habitant morbi tristique senectus et netus et "+
		"malesuada fames ac turpis egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean rutrum pulvinar nulla, eu vehicula turpis venenatis eget. Vivamus elit risus, sodales id accumsan id, elementum nec leo. Vivamus sed libero dui, ac dignissim metus. Fusce nunc odio, euismod vel porttitor blandit, convallis in augue. Curabitur adipiscing elementum scelerisque. Vivamus augue "+
		"justo, pharetra et convallis a, tincidunt vel quam.</p>"+

		"<h2><a id='5'>Developer</a></h2>"+
		"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pulvinar gravida lorem, quis consectetur magna rhoncus vel. Donec scelerisque turpis vel elit tempor sollicitudin. Vivamus tellus turpis, molestie eget porta eget, mattis vitae arcu. Sed dignissim adipiscing ante sit amet consectetur. Aliquam nibh sapien, porta vitae feugiat id, elementum sed dui. Pellentesque habitant morbi tristique senectus et netus et "+
		"malesuada fames ac turpis egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean rutrum pulvinar nulla, eu vehicula turpis venenatis eget. Vivamus elit risus, sodales id accumsan id, elementum nec leo. Vivamus sed libero dui, ac dignissim metus. Fusce nunc odio, euismod vel porttitor blandit, convallis in augue. Curabitur adipiscing elementum scelerisque. Vivamus augue "+
		"justo, pharetra et convallis a, tincidunt vel quam.</p>"+

		"<h2><a id='6'>Upload</a></h2>"+
		"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pulvinar gravida lorem, quis consectetur magna rhoncus vel. Donec scelerisque turpis vel elit tempor sollicitudin. Vivamus tellus turpis, molestie eget porta eget, mattis vitae arcu. Sed dignissim adipiscing ante sit amet consectetur. Aliquam nibh sapien, porta vitae feugiat id, elementum sed dui. Pellentesque habitant morbi tristique senectus et netus et "+
		"malesuada fames ac turpis egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean rutrum pulvinar nulla, eu vehicula turpis venenatis eget. Vivamus elit risus, sodales id accumsan id, elementum nec leo. Vivamus sed libero dui, ac dignissim metus. Fusce nunc odio, euismod vel porttitor blandit, convallis in augue. Curabitur adipiscing elementum scelerisque. Vivamus augue"+ 
		"justo, pharetra et convallis a, tincidunt vel quam.</p>"+

		"<h2><a id='7'>Read</a></h2>"+
		"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pulvinar gravida lorem, quis consectetur magna rhoncus vel. Donec scelerisque turpis vel elit tempor sollicitudin. Vivamus tellus turpis, molestie eget porta eget, mattis vitae arcu. Sed dignissim adipiscing ante sit amet consectetur. Aliquam nibh sapien, porta vitae feugiat id, elementum sed dui. Pellentesque habitant morbi tristique senectus et netus et"+ 
		"malesuada fames ac turpis egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean rutrum pulvinar nulla, eu vehicula turpis venenatis eget. Vivamus elit risus, sodales id accumsan id, elementum nec leo. Vivamus sed libero dui, ac dignissim metus. Fusce nunc odio, euismod vel porttitor blandit, convallis in augue. Curabitur adipiscing elementum scelerisque. Vivamus augue "+
		"justo, pharetra et convallis a, tincidunt vel quam.</p>"+

		"<h2><a id='8'>Pay</a></h2>"+
		"<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean pulvinar gravida lorem, quis consectetur magna rhoncus vel. Donec scelerisque turpis vel elit tempor sollicitudin. Vivamus tellus turpis, molestie eget porta eget, mattis vitae arcu. Sed dignissim adipiscing ante sit amet consectetur. Aliquam nibh sapien, porta vitae feugiat id, elementum sed dui. Pellentesque habitant morbi tristique senectus et netus et "+
		"malesuada fames ac turpis egestas. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Aenean rutrum pulvinar nulla, eu vehicula turpis venenatis eget. Vivamus elit risus, sodales id accumsan id, elementum nec leo. Vivamus sed libero dui, ac dignissim metus. Fusce nunc odio, euismod vel porttitor blandit, convallis in augue. Curabitur adipiscing elementum scelerisque. Vivamus augue "+
		"justo, pharetra et convallis a, tincidunt vel quam.</p>"+

		"<p> <a href='#top' style='position:fixed;bottom:5px;right:5px;'>Back To Top</a></p>"+

		"</body>"+
		"</html>";
		
		webview.loadDataWithBaseURL("", baseURL, "text/html", "utf-8", "");
*/
	}
}