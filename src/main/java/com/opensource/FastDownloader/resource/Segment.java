package com.opensource.FastDownloader.resource;

public class Segment {
	private long segmentStart;
	private long segmentEnd;
	private long segmentSize;
	private boolean isWrittenToFile;
	private int segmentNo;
	public long getSegmentStart() {
		return segmentStart;
	}
	public void setSegmentStart(long segmentStart) {
		this.segmentStart = segmentStart;
	}
	public long getSegmentEnd() {
		return segmentEnd;
	}
	public void setSegmentEnd(long segmentEnd) {
		this.segmentEnd = segmentEnd;
	}
	public long getSegmentSize() {
		return segmentSize;
	}
	public void setSegmentSize(long segmentSize) {
		this.segmentSize = segmentSize;
	}
	public boolean isWrittenToFile() {
		return isWrittenToFile;
	}
	public void setWrittenToFile(boolean isWrittenToFile) {
		this.isWrittenToFile = isWrittenToFile;
	}
	public int getSegmentNo() {
		return segmentNo;
	}
	public void setSegmentNo(int segmentNo) {
		this.segmentNo = segmentNo;
	}
	
}
