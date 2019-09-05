public class wordDriver implements Comparable<wordDriver> {

	private String definition;
	private String wordType;

	wordDriver(String d, String t){
		this.definition = d;
		this.wordType = t;
	}
	public String getwordType() 
	{
		return this.wordType;
	}
	
	public String getdefinition() 
	{
		return this.definition;
	}
	@Override
	public int compareTo(wordDriver other){
		return wordType.compareTo(other.wordType);
	}
	
	public boolean equals(Object obj){
		if(!(obj instanceof wordDriver)) return false;
		wordDriver other = (wordDriver)obj;
		return this.definition.equals(other.definition) && this.wordType.equals(other.wordType);
	}
}