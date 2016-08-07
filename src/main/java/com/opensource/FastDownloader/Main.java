package com.opensource.FastDownloader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import com.opensource.FastDownloader.download.DownloadManager;

public class Main {
	public static void main(String[] args) throws IOException{
		int noOfThreads = 4;
		//video file
		String url = "http://download2.o2tvseries.com/Arrow/Season%2004/Arrow%20-%20S04E22%20(TvShows4Mobile.Com).mp4"; // Get url from list of urls
		//text file
		//String url = "http://norvig.com/big.txt";
		URL linkUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) linkUrl.openConnection();
		connection.connect();
		long fileSize = connection.getContentLength() - 1;
		System.out.println("filesize : "+fileSize);
		connection.disconnect();
		long segmentSize = 1000000l; // 1 KB
		DownloadManager downloadManager = new DownloadManager(noOfThreads,segmentSize,fileSize,url);
		downloadManager.manageDownload();
	}
}
