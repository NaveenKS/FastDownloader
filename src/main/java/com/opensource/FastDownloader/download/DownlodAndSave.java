package com.opensource.FastDownloader.download;

import java.util.concurrent.Callable;

import com.opensource.FastDownloader.resource.Segment;

public interface DownlodAndSave extends Callable<Segment>{
	public byte[] download() throws Exception;
	public boolean save(byte[] data) throws Exception;
}
