package com.opensource.FastDownloader;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.Test;

import com.opensource.FastDownloader.download.DownlodAndSave;
import com.opensource.FastDownloader.download.SegmentDownloadAndSave;
import com.opensource.FastDownloader.save.FileSaver;
import com.opensource.FastDownloader.save.NeoFileSaver;

public class TestDownloader {
	@Test
	public void testDownload() throws Exception{
		String url = "http://stackoverflow.com/questions/29309063/urlconnection-setrequestproperty-range-header-getting-http-416-curl-works";
		String path = "/home/naveen/Desktop/test/test.txt";
		FileSaver fileSaver = new NeoFileSaver(path);
		DownlodAndSave downlodAndSave = new SegmentDownloadAndSave(1, 1001, 2000, url, fileSaver);
		byte[] data = downlodAndSave.download();
		System.out.println(new String(data));
	}
	
	@Test
	public void downloadPartial() throws Exception{
		//String url = "http://stackoverflow.com/questions/29309063/urlconnection-setrequestproperty-range-header-getting-http-416-curl-works";
		//String url = "http://download2.o2tvseries.com/Arrow/Season%2004/Arrow%20-%20S04E22%20(TvShows4Mobile.Com).mp4"; // Get url from list of urls
		String url = "http://norvig.com/big.txt"; // Get url from list of urls
		
		URL murl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection)murl.openConnection();
		
		// set the range of byte to download
		
		String byteRange = 0 + "-" + 5000;
		
		conn.setRequestProperty("Range", "bytes=" + byteRange);
		System.out.println("bytes=" + byteRange);
		
		// connect to server
		conn.connect();
		System.out.println("Response code - "+conn.getResponseCode());
		System.out.println(conn.getHeaderField("Content-Range"));
		System.out.println(conn.getContentLength());
		// get the input stream
		
		InputStream in = new BufferedInputStream(conn.getInputStream());
		
		// open the output file and seek to the start location
		
		NeoFileSaver filesaver = new NeoFileSaver("/home/naveen/Desktop/test/test.txt");
		
		byte data[] = new byte[5001];
		byte buffer[] = new byte[1000];
		int readBytes;
		int start = 0;
		while( (readBytes = in.read(buffer,0,1000)) != -1){
			//System.out.println("start : "+start);
			//System.out.println("readBytes : "+readBytes);
			System.arraycopy(buffer, 0, data, start, readBytes);
			start = start + readBytes;
		}
		
		System.out.println(new String(data));
		filesaver.save(data, 0);
		
//		in.read(data,0,1000);
//		filesaver.save(data, 1000);
	}
}
