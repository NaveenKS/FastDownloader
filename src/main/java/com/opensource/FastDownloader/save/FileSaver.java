package com.opensource.FastDownloader.save;

public interface FileSaver {
	public boolean save(byte[] data,long segmentStart) throws Exception;
}
