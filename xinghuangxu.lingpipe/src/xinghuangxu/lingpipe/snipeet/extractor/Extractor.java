package xinghuangxu.lingpipe.snipeet.extractor;

import java.io.File;
import java.io.IOException;

import xinghuangxu.lingpipe.tutorial.sentiment.PolarityBasic;


/**
 * Main entrance
 * @author xinghuang xu
 * 
 */
public class Extractor {

	public String getPath(String FileName) {
		String current;
		try {
			current = new File(".").getCanonicalPath();
		} catch (IOException e) {
			return "";
		}
		// return current + DIR_NAME + "\\"+CORPUS_DIR_NAME+"\\" +
		// CORPUS_FILE_NAME;
		return current + "\\" + FileName;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Extractor extractor = new Extractor();
		try {
			extractor.run(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int run(String[] args) throws Exception {
		
		if (args.length < 1) {
			System.out.println("Usage: - l + r");
			return -1;
		}

		int left = 0;
		int right = 0;
		
		for (int i = 0; i < args.length; i++) {
			if ("-".equals(args[i])) {
				left = Integer.parseInt(args[i + 1]);
				i++;
			} else if ("+".equals(args[i])) {
				right = Integer.parseInt(args[i + 1]);
				i++;
			}
		}
		
		System.out.println("SnippetSize: "+"-"+left+" +"+right);


		String dir = getPath("Sentiment_DB");
		String reviewDir = dir + "\\Review_DB";
		String aspDir = dir + "\\Aspects_DB";
		String snippetDir = dir + "\\Snippet_DB";
		String trainingDir=dir+"\\Training_DB";
		File sentimentFolder = new File(dir);
		sentimentFolder.mkdir();
		File reviewFolder = new File(reviewDir);
		reviewFolder.mkdir();
		File snippetFolder = new File(snippetDir);
		snippetFolder.mkdir();
		File aspFolder = new File(aspDir);
		aspFolder.mkdir();

		// Load all the reviews
		File[] reviewFiles = reviewFolder.listFiles();
		Review review = XMLParser.getReviews(reviewFiles);
		System.out.println("\nLoad Reviews From Directory: "+ reviewDir);
		System.out.println("Review Size: "+review.size());

		
		
		// Load all the aspects
		System.out.println("\nLoad Aspects From Directory: "+ aspDir);
		File[] aspFiles = aspFolder.listFiles();
		AspDictionary aspDictionary = XMLParser.getAspectsDictionary(aspFiles);
		System.out.println("Aspect Size: "+aspDictionary.size());
		
		
		// Create Snippet Dictionary acording to (- m + n)
		System.out.println("\nCreate Snippet Dictionary");
		SnippetFactory snippetFactory=SnippetFactory.newInstance();
		SnippetDictionary snippetDictionary=snippetFactory.newSnippetDictionary(review,aspDictionary);
		snippetDictionary.trimAll(left, right);
		System.out.println("Snippet Dictionary Size: "+snippetDictionary.size());
		
		//System.out.println(snippetDictionary.toString());
		
		//Run basic polarity
		PolarityBasic polarityBasic=new PolarityBasic(trainingDir,snippetDictionary);
		polarityBasic.run();
		
		return -1;

	}

}
