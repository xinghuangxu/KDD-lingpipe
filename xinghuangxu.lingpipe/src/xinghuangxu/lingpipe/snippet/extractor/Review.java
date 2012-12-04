package xinghuangxu.lingpipe.snippet.extractor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Review implements Iterable<String> {

	private List<String> reviewList;
	{
		reviewList = new ArrayList<String>();
	}

	public boolean add(String review) {
		return reviewList.add(review);
	}

	public boolean isEmpty() {
		return reviewList.isEmpty();
	}

	public int size() {
		return reviewList.size();
	}

	@Override
	public Iterator<String> iterator() {
		return reviewList.iterator();
	}

}
