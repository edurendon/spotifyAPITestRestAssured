package com.got.utils;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import org.apache.log4j.FileAppender;

public class CustomFileAppender extends FileAppender {

	/** The timestamp. */
	public static String FILE_PATH_TIMESTAMP;
	public static String TIMESTAMP;

	@Override
	public void setFile(String fileName) {
		if (FILE_PATH_TIMESTAMP == null) {
			if (fileName.indexOf("%timestamp") >= 0) {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmm");
				String currentDate = format.format(GregorianCalendar.getInstance().getTime());
				fileName = fileName.replaceAll("%timestamp", currentDate);
				FILE_PATH_TIMESTAMP = fileName;
				TIMESTAMP = currentDate;
			}
			super.setFile(fileName);
		} else {
			super.setFile(FILE_PATH_TIMESTAMP);
		}
	}
}