package xinghuangxu.lingpipe.sentiment.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private static Log log;

	private final String dir;
	
	private File file;
	
	private final String date;

	private BufferedWriter bw;

	// private BufferedWriter bw;

	private Log(String dir) throws IOException {
		date=getDate();
		this.dir=dir+date;
		File folder=new File(this.dir);
		folder.mkdir();
	}

	private static String getDate() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(System
				.currentTimeMillis()));
	}

	public static void createInstance(String dir) throws IOException {
		log = new Log(dir);
	}

	public static Log getInstance() {
		return log;
	}
	
	public static void createFile(String name){
		log.setName(name);
	}

	private void setName(String name) {
		file=new File(dir,date+"-"+name+".txt");
	}

	private void log(String info) throws IOException {
		try {
			bw = new BufferedWriter(new FileWriter(file,true));
			bw.write(info+"\n");
			System.out.println(info);
		} finally {
			bw.close();
		}
	}


	public static void info(String info) throws IOException {
		log.log(info);
	}

	public static void createFolder(String logDir) {
		new File(logDir).mkdir();
		
	}

}
