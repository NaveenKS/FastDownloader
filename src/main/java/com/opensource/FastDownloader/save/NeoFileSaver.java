package com.opensource.FastDownloader.save;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class NeoFileSaver implements FileSaver{
	private File file;
	public NeoFileSaver(String path) throws IOException{
		file = new File(path);
		if(!file.exists()){
			file.createNewFile();
		}
	}

	public synchronized boolean save(byte[] data,long segmentStart) throws Exception {
		long pointer = segmentStart;
		boolean success = false;
		RandomAccessFile randomAccessFile = null;
		try{
			randomAccessFile = new RandomAccessFile(file, "rw");
			randomAccessFile.seek(pointer);
			randomAccessFile.write(data);
			success = true;
		}finally{
			if(randomAccessFile!=null){
				randomAccessFile.close();
			}
		}
		return success;
	}
}
