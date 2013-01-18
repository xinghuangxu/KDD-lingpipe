package xinghuangxu.lingpipe.snippet.extractor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Save the aspects used to extract snippets
 * Work just like a dictionary
 * @author xinghuang
 *
 */
public class AspDictionary {

	/**
	 * HashTable
	 * Key:String is the name of the aspect
	 * Value:List<String> is the acronyms of the aspect
	 * Example: <Screen>Screen|LED</Screen>
	 */
	Map<String, List<String>> aspectDictionary;

	
	public AspDictionary() {
		aspectDictionary=new HashMap<String,List<String>>();
	}
	
	
	
	public void add(String key, String value){
		String[] values=value.trim().split("\\|");
		List<String> valueList=new ArrayList<String>();
		if(aspectDictionary.containsKey(key)){
			valueList=aspectDictionary.get(key);
		}
		for(int i=0;i<values.length;i++){
			if(!valueList.contains(values[i]))
				valueList.add(values[i].replaceAll("\\\n|\\\t", "").trim());
		}
		aspectDictionary.put(key, valueList);
	}
	
	public int size(){
		return aspectDictionary.size();
	}
	
	public Set<String> keySet(){
		return aspectDictionary.keySet();
	}
	
	
	public List<String> get(String key){
		return aspectDictionary.get(key);
	}

}
