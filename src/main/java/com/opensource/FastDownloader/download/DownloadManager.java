package com.opensource.FastDownloader.download;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.opensource.FastDownloader.resource.Segment;
import com.opensource.FastDownloader.save.FileSaver;
import com.opensource.FastDownloader.save.NeoFileSaver;

public class DownloadManager {
	private int noOfThreads;
	private long segmentSize;
	private long fileSize;
	private String url;
	public DownloadManager(int noOfThreads,long segmentSize,long fileSize,String url){
		this.noOfThreads = noOfThreads;
		this.segmentSize = segmentSize;
		this.fileSize = fileSize;
		this.url = url;
	}
	
	public void manageDownload(){
		System.out.println("Segment size : "+segmentSize);
		double noOfSegments = (double)fileSize / segmentSize;
		System.out.println("segments in float : "+noOfSegments);
		int segmentsInInt = (int) noOfSegments;
		System.out.println("no of segments in int : "+segmentsInInt);
		if(noOfSegments - segmentsInInt > 0d){
			noOfSegments =(int)noOfSegments + 1;
		}
		
		System.out.println("no of segments : "+noOfSegments);
		List<Future<Segment>> futureList = new ArrayList<Future<Segment>>();
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(noOfThreads);
		FileSaver fileSaver = null;
		try {
			fileSaver = new NeoFileSaver("/home/naveen/Desktop/test/movie.mp4");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long segmentStart = 0l;
		long segmentEnd = 0l;
		long startTime = System.currentTimeMillis();
		for(int segmentNo=1;segmentNo<=noOfSegments;segmentNo++){
			segmentEnd = segmentStart + segmentSize - 1;
			if(segmentEnd > fileSize){
				segmentEnd = fileSize;
			}
			
			SegmentDownloadAndSave downloader = new SegmentDownloadAndSave(segmentNo,segmentStart, segmentEnd,url,fileSaver);
			Future<Segment> future = executor.submit(downloader);
			futureList.add(future);
			segmentStart = segmentEnd + 1;
		}
		for(Future<Segment> future : futureList){
			try {
				Segment segment = future.get();
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		long elapsedTime = System.currentTimeMillis() - startTime;
		System.out.println("Time taken : "+elapsedTime);
		executor.shutdown();
	}
}
