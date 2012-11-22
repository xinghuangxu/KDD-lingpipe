package xinghuangxu.lingpipe.snipeet.extractor;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SnippetFactory {

	
	private SnippetFactory() {

	}
	
	public static SnippetFactory newInstance(){
		return new SnippetFactory();
	}


	public SnippetDictionary newSnippetDictionary(String name, Review review, AspDictionary aspDictionary) {
		SnippetDictionary snippetDictionary = new SnippetDictionary(name);
		Set<String> tagNames=aspDictionary.keySet();
		for(Iterator<String> i=tagNames.iterator();i.hasNext();){
			String tagName=i.next();
			List<String> values= aspDictionary.get(tagName);
			for(Iterator<String> valuesIterator=values.iterator();valuesIterator.hasNext();){
				String value=valuesIterator.next();
				for(Iterator<String> reviewIterator=review.iterator();reviewIterator.hasNext();){
					String reviewContent=reviewIterator.next();
					List<Snippet> newSnippet=newSnippet(reviewContent,tagName,value);
					if(newSnippet!=null)
						snippetDictionary.add(tagName, newSnippet);
				}
			}
		}
		return snippetDictionary;
	}
	
	public List<Snippet> newSnippet(String reviewContent, String key,String value){
		List<Snippet> snippets=new ArrayList<Snippet>();
		String[] slices=reviewContent.split("[a-z]*"+value+"[a-z]*");
		for(int i=0;i<slices.length-1;i++){
			snippets.add(new Snippet(key,value,slices[i].trim(),slices[i+1].trim()));
		}
		if(snippets.size()==0)return null;
		return snippets;
	}
	
	

}
