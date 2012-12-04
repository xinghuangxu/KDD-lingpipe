package xinghuangxu.lingpipe.tutorial.sentiment;

import com.aliasi.util.Files;

import com.aliasi.classify.Classification;
import com.aliasi.classify.Classified;
import com.aliasi.classify.DynamicLMClassifier;

import com.aliasi.lm.NGramProcessLM;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import xinghuangxu.lingpipe.sentiment.logging.Log;
import xinghuangxu.lingpipe.snippet.extractor.Snippet;
import xinghuangxu.lingpipe.snippet.extractor.SnippetDictionary;

/**
 * Polarity Basix Demo class
 * @author xinghuang xu
 *
 */
public class PolarityBasic {

	File mPolarityDir;
	String[] mCategories;
	DynamicLMClassifier<NGramProcessLM> mClassifier;
	SnippetDictionary snippetDictionary;
	
	private static PolarityBasic instance;

	private PolarityBasic(String trainingDir) {

		//this.snippetDictionary = snippetDictionary;

		mPolarityDir = new File(trainingDir);
		mCategories = mPolarityDir.list();
		int nGram = 8;
		mClassifier = DynamicLMClassifier
				.createNGramProcess(mCategories, nGram);
	}
	
	public static void create(String trainingDir) throws IOException{
		instance=new PolarityBasic(trainingDir);
		instance.train();
	}
	
	public static PolarityBasic getInstance(){
		return instance;
	}
	
	private void setData(SnippetDictionary sd){
		snippetDictionary=sd;
	}
	

	/**
	 * Code for running Lingpipe for Sentiment Analysis
	 * 
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
//	public void run() throws ClassNotFoundException, IOException {
//		train();
//		evaluate();
//	}



	/**
	 * Training
	 * 
	 * @throws IOException
	 */
	void train() throws IOException {
		int numTrainingCases = 0;
		int numTrainingChars = 0;
		Log.info("Training.");
		for (int i = 0; i < mCategories.length; ++i) {
			String category = mCategories[i];
			Classification classification = new Classification(category);
			File file = new File(mPolarityDir, mCategories[i]);
			File[] trainFiles = file.listFiles();
			for (int j = 0; j < trainFiles.length; ++j) {
				File trainFile = trainFiles[j];
				++numTrainingCases;
				String review = Files.readFromFile(trainFile, "ISO-8859-1");
				numTrainingChars += review.length();
				Classified<CharSequence> classified = new Classified<CharSequence>(
						review, classification);
				mClassifier.handle(classified);
			}
		}
		Log.info("  # Training Cases=" + numTrainingCases);
		Log.info("  # Training Chars=" + numTrainingChars);
	}

	
	/**
	 * Evaluate
	 * 
	 * @throws IOException
	 */
	void evaluate() throws IOException {

		Log.info("\nEvaluating.");
		Set<String> keys = snippetDictionary.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();) {
			String key = i.next();
			List<Snippet> snippets = snippetDictionary.get(key);
			for (Iterator<Snippet> si = snippets.iterator(); si.hasNext();) {
				Snippet snippet = si.next();
				Classification classification = mClassifier.classify(snippet
						.toString());
				if (classification.bestCategory().equals("pos"))
					snippet.setPolarity(1);
				else
					snippet.setPolarity(-1);
			}
		}
		snippetDictionary.analysePolarity();
		Log.info(snippetDictionary.getPolarities());

	}

	public static void setSnippetDictionary(SnippetDictionary snippetDictionary) {
		instance.setData(snippetDictionary);
		
	}
	
	public static void run() throws IOException{
		instance.evaluate();
	}



}
