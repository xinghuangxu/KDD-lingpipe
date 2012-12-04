package xinghuangxu.lingpipe.snippet.extractor;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;



public class SnippetDictionary {
	
	private String name;

	private Map<String, List<Snippet>> snippetDictionary;
	private Map<String,Integer> aspectsPolarity;
	

	public SnippetDictionary(String name) {
		this.name=name;
		snippetDictionary = new HashMap<String, List<Snippet>>();
		aspectsPolarity=new HashMap<String,Integer>();
	}

	public void add(String key, List<Snippet> value) {

		List<Snippet> valueList = new ArrayList<Snippet>();
		
		if (snippetDictionary.containsKey(key)) {
			valueList = snippetDictionary.get(key);
		}
		valueList.addAll(value);
		snippetDictionary.put(key, valueList);
	}
	
	public int size(){
		return snippetDictionary.size();
	}
	
	public int snippetSize(){
		Set<String> keys=snippetDictionary.keySet();
		int count=0;
		for(Iterator<String> i=keys.iterator();i.hasNext();){
			String key=i.next();
			List<Snippet> snippets=snippetDictionary.get(key);
			count+=snippets.size();
		}
		return count;
	}
	
	public void trimAll(int left,int right){
		
		Set<String> keys=snippetDictionary.keySet();
		for(Iterator<String> i=keys.iterator();i.hasNext();){
			String key=i.next();
			List<Snippet> snippets=snippetDictionary.get(key);
			for(Iterator<Snippet> snippetsIterator=snippets.iterator();snippetsIterator.hasNext();){
				snippetsIterator.next().trim(left,right);
			}
		}
	}
	
	public Set<String> keySet(){
		return snippetDictionary.keySet();
	}
	
	public List<Snippet> get(String key){
		return snippetDictionary.get(key);
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		Set<String> keys=snippetDictionary.keySet();
		for(Iterator<String> i=keys.iterator();i.hasNext();){
			String key=i.next();
			List<Snippet> snippets=snippetDictionary.get(key);
			for(Iterator<Snippet> snippetsIterator=snippets.iterator();snippetsIterator.hasNext();){
				sb.append(snippetsIterator.next().toString()+"\n");
			}
		}
		return sb.toString();
	}

	public void analysePolarity() {
		Set<String> keys=snippetDictionary.keySet();
		for(Iterator<String> i=keys.iterator();i.hasNext();){
			Integer polarity=0;
			String key=i.next();
			List<Snippet> snippets=snippetDictionary.get(key);
			for(Iterator<Snippet> snippetsIterator=snippets.iterator();snippetsIterator.hasNext();){
				int sp=snippetsIterator.next().getPolarity();
				polarity=polarity+(sp>0?sp:0);
			}
			aspectsPolarity.put(key, polarity);
		}
	}
	
	public String getPolarities(){
		StringBuilder sb=new StringBuilder();
		sb.append(name+":\n");
		Set<String> keys=aspectsPolarity.keySet();
		for(Iterator<String> i=keys.iterator();i.hasNext();){
			String key=i.next();
			List<Snippet> sl=snippetDictionary.get(key);
			Integer polarity=aspectsPolarity.get(key);
			Double ratio=(polarity.doubleValue()/sl.size())*100;
			DecimalFormat df=new DecimalFormat("#.##");
			sb.append(" # "+key+": "+polarity+"/"+sl.size()+" = "+df.format(ratio)+"% pos\n");
		}
		sb.append("///////////////////////////////////////////////////////////////////////////\n\n");
		return sb.toString();
	}

}
