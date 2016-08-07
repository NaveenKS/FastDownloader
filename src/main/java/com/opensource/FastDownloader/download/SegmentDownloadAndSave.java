package com.opensource.FastDownloader.download;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.opensource.FastDownloader.resource.Segment;
import com.opensource.FastDownloader.save.FileSaver;

public class SegmentDownloadAndSave implements DownlodAndSave{
	private long segmentStart;
	private long segmentEnd;
	private String url;
	private FileSaver fileSaver;
	private int segmentNo;
	private int BUFFER_SIZE = 1000;
	public SegmentDownloadAndSave(int segmentNo,long segmentStart,long segmentEnd,String url,FileSaver fileSaver){
		this.segmentStart = segmentStart;
		this.segmentEnd = segmentEnd;
		this.url = url;
		this.fileSaver = fileSaver;
		this.segmentNo = segmentNo;
	}
	
	public byte[] download() throws Exception{
		System.out.println("Segment no : "+segmentNo+"download started");
		URL linkUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) linkUrl.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Range", "bytes="+segmentStart+"-"+segmentEnd);
		connection.connect();
		
		System.out.println("Response :"+connection.getResponseCode());
//		if(connection.getResponseCode()/100!=2){
//			//error
//		}
		
		InputStream in = new BufferedInputStream(connection.getInputStream());
		System.out.println("segment no : "+segmentNo+" Segment end : "+segmentEnd+" segment start :"+segmentStart);
		long segmentSize = segmentEnd - segmentStart + 1;
		System.out.println("segment size :"+segmentSize);
		
		byte data[] = new byte[(int)segmentSize];
		byte buffer[] = new byte[BUFFER_SIZE];
		int readBytes;
		int start = 0;
		while( (readBytes = in.read(buffer,0,1000)) != -1){
			System.arraycopy(buffer, 0, data, start, readBytes);
			start = start + readBytes;
		}
		
		in.close();
		connection.disconnect();
		System.out.println("Segment no : "+segmentNo+"download ended");
		return data;
	}

	public Segment call() throws Exception {
		Segment segment = null;
		try{
			byte[] data = download();
			boolean success = save(data);
			segment = new Segment();
			segment.setSegmentStart(segmentStart);
			segment.setSegmentNo(segmentNo);
			long size = segmentEnd - segmentStart;
			segment.setSegmentSize(size);
			segment.setWrittenToFile(success);
		}catch(Exception e){
			e.printStackTrace();
		}
		return segment;
	}

	public boolean save(byte[] data) throws Exception {
		System.out.println("Segment no : "+segmentNo+"File save started");
		boolean success = false;
		success = fileSaver.save(data, segmentStart);
		System.out.println("Segment no : "+segmentNo+"File save ended");
		return success;
	}

	
}
