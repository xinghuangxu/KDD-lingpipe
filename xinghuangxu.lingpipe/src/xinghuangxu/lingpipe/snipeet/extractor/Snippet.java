package xinghuangxu.lingpipe.snipeet.extractor;

/**
 * 
 * Left <tagName> name </tagName> Right
 * @author xinghuang xu
 *
 */
public class Snippet {

	public Snippet(String tagName, String name, String left, String right) {
		super();
		this.tagName = tagName;
		this.name = name;
		this.leftString = left;
		this.rightString = right;
	}
	
	private int polarity;
	private String tagName;
	private String name;
	private String leftString;
	private String rightString;
	
	public void trim(int left,int right){
		String cleanLeftString=leftString.trim().replaceAll("[^0-9a-z\\s]", "").replaceAll(" +"," ");
		String cleanRightString=rightString.trim().replaceAll("[^0-9a-z\\s]", "").replaceAll(" +"," ");
		leftString=getLast(left,cleanLeftString);
		rightString=getFirst(right,cleanRightString);
	}

	private static String getFirst(int num,String leftString) {
		String[] splits=leftString.split(" ");
		if(num>splits.length)num=splits.length;
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<num;i++){
			sb.append(splits[i].trim()+" ");
		}
		return sb.toString();
	}

	private static String getLast(int num,String rightString) {
		String[] splits=rightString.split(" ");
		if(num>splits.length)num=splits.length;
		StringBuilder sb=new StringBuilder();
		for(int i=splits.length-1;num>0;num--){
			StringBuilder head=new StringBuilder(splits[i]);
			sb=head.append(" "+sb);
			i--;
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return leftString+name+" "+rightString;
	}

	public void setPolarity(int i) {
		polarity=i;
	}
	
	public int getPolarity(){
		return polarity;
	}
	

}
