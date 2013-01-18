package xinghuangxu.lingpipe.sentiment.logging;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Take care of output to file & Console
 * @author xinghuang
 *
 */
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
	
	/**
	 * Static mehtod to Create new file call method setName
	 * @param name
	 */
	public static void createFile(String name){
		log.setName(name);
	}
	
	/**
	 * Create file
	 * @param name
	 */
	private void setName(String name) {
		file=new File(dir,date+"-"+name+".txt");
	}
	
	/**
	 * Out put info to both File and Console
	 * @param info
	 * @throws IOException
	 */
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

	/**
	 * Create a new Folder
	 * @param logDir
	 */
	public static void createFolder(String logDir) {
		new File(logDir).mkdir();
		
	}

}
