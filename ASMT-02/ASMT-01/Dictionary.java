/****************************************************************
*
* File: Dictionary.java
* By: Francis Santiago
* Date: 9-18-2018
*
* definition: This program uses enum to store dictionary entires,
*              then the user can search predetermined entries and the
*              program displays word, wordType, definition. 
*
****************************************************************/
import java.util.*;
public enum Dictionary{
  
  //The words, definitions, and word type classifications created a constant enum data types.
  CSC340("CSC340", "= C++ version of CSC210 + CSC220 + more ? A CS upper division course ? Many hours outside of class. ? Programming Methodology", 
         "adjective ? noun ? noun ? noun"),
  CSC220("CSC220", "Data Structures ? Ready to create complex data structures ? To create data structures", 
         "noun ? adjective ? verb"),
  CSC210("CSC210", "Intro to Java ? To learn Java ? Comfortable with Objects and Classes ? Ready for CSC220 ", 
         "noun ? verb ? adjective ? adjective"),
  Book("Book", "A set of pages ? To arrange something on a particular date ? To arrange for someone to have a seat on a plane ? A written work published in printed or electronic form", 
       "noun ? verb ? verb ? noun"),
  Bookable("Bookable", "Can be ordered in advance.", "adjective"),
  Bookbinder("Bookbinder", "A person who fastens the pages of books.", "noun"),
  Bookcase("Bookcase","A piece of furniture with shelves", "noun"),
  Placeholder("Placeholder",
		      "To be updated... ? "
		    + "To be updated... ? "
		    + "To be updated... ? "
		    + "To be updated... ? "
		    + "To be updated... ? "
		    + "To be updated... ? "
		    + "To be updated... ? "
		    + "To be updated... ? "
		    + "To be updated... ? "
		    + "To be updated... ? "
		    + "To be updated... ?",
		    "adjective ? adverb ? verb ? pronoun ? preposition ? noun ? noun ? noun ? interjection ? adjective ? conjunction"),
  Verb("Verb","Verb is a word or group of words that expresses an action (such as eat), an event\n" + 
			"(such as happen) or a state (such as exist)","noun"),
	Conjunction("Conjunction","Conjunction is a word that joins words, phrases or sentences, for example\n" + 
			"'and', 'but', 'or'","noun"),
	Interjection("Interjection","Interjection is a short sound, word or phrase spoken suddenly to express\n" + 
			"an emotion. Oh!, Look out! and Ow! are interjections","noun"),
	Adjective("Adjective","Adjective is a word that describes a person or thing, for example big, red\n" + 
			"and clever in a big house, red wine and a clever idea","noun");
	
  private String word;
  private String definition;
  private String wordType;
  public String getword(){//string for words
    return this.word;
  }
  public String getdefinition(){//string for definitons
    return this.definition;
  }
  public String getwordType(){//string for word types
    return this.wordType;
  }
  public static boolean check_wordType(String gram){
    switch(gram.toLowerCase()){
      case "noun": case "verb": case "conjunction": case "interjection":
      case "preposition": case "pronoun": case "adjective":
        return true;
      default:
        return false;
    }
  }
  Dictionary(String w, String d, String t){
    this.word = w;
    this.definition = d;
    this.wordType = t;
  }
  //Structure of ArrayList
  //Prints dictionary entries, word type, and definition
  public static int check_wordType(List<wordDriver> index, String wordType, String word, boolean distinct){
    //fill the string
    List<wordDriver> added = new ArrayList<wordDriver>();
    //List<String> added = new ArrayList<String>;
    word = word.charAt(0) + word.substring(1).toLowerCase();//char index starts at 0 then adds a sting at 1
    for(wordDriver stored : index){
      if((stored.getwordType()).equals(wordType)){
    	       if((distinct && !added.contains(stored)) || !distinct) {//checks distincts
    	    	  added.add(stored);
    	    }
       }
    }    
    Collections.sort(added);//to organize the elements
    //Collections.reverse(added);
    if(!added.isEmpty()){
    	 for(int i = 0; i < added.size(); i++){
    		  wordDriver elem = added.get(i);
     	    String definition =  elem.getdefinition();
     	    if(!definition.endsWith(".")){
     	    		definition = definition+".";
     	    }
     		 System.out.println("     " + word + " [" + wordType + "] : " + definition);//output
     }
    }
    return added.size();
  }
  
  //Structure of HashMap
  public static void main(String[] args){
    //Create the HashMap
    Map<String, List<wordDriver>> stack = new HashMap<String, List<wordDriver>>();
    Scanner input = new Scanner(System.in);
    
    System.out.println("! Loading data...");

    for(Dictionary entry : Dictionary.values()){//iterate through the data and stores
      
      String[] sortwordType = (entry.getwordType()).split("[?]"); //gets wordType
      String[] sortDefinition = (entry.getdefinition()).split("[?]"); //gets definition
      
      List<wordDriver> EntryList = new ArrayList<wordDriver>();//from public class wordDriver
      for(int i = 0; i < sortDefinition.length; i++){
        EntryList.add(new wordDriver(sortDefinition[i].trim(),sortwordType[i].trim()));
      }
      //store entries
      stack.put((entry.getword()).toUpperCase(), EntryList);
    }
    //loads data ""
    System.out.println("! Loading completed...\n");
    System.out.println("-----DICTIONARY 340 JAVA-----");
    
    //Interface 
    do{
      System.out.print("Search: ");
      //Search the Dictionary
      //User input, input word type, check word type, check distinct
      String[] entriesArray = (input.nextLine()).split("[ ]");//next line
      String entriesUser = "";//user entry
      String entries_wordType = "";//entered word type
      boolean check_wordType = false;//check word type
      boolean distinct = false; // check distinct
     
      for(int i = 0; i < entriesArray.length; i++) //upper and lower case check
      {
        if(i == 0){
          entriesUser = entriesArray[i].toUpperCase();
        }
          else if((i == 1) && !entriesArray[i].toLowerCase().equals("distinct")){
        	   	
              entries_wordType = entriesArray[i].toLowerCase();
              check_wordType = Dictionary.check_wordType(entries_wordType.trim());         
        }
          else if(entriesArray[i].toLowerCase().equals("distinct")){
        		  
              distinct = true;
        }
      }
     //user entry for exiting/breaking the program
     //format for the program
      if(entriesUser.equals("!Q")){
          System.out.println("-----Thank You-----");
          break;
      }
      else if(stack.get(entriesUser) == null){ //checks for an invalid entry
           System.out.println("    |\n     <Not found>\n    |");
      }
      else if(entries_wordType != ""){
        if(check_wordType){ //check entry input for word type
        List<wordDriver> temp = stack.get(entriesUser);//values from wordDriver
        System.out.println("    |");
        int count = check_wordType(temp, entries_wordType.toLowerCase(), entriesUser, distinct);
        if(count == 0){
      	System.out.println("    <Not found>");//formatting
        }
        System.out.println("    |");//formatting
        }
        else{
        System.out.println("    |\n     <2nd argument must be a part of speech or \"distinct\">\n    |");
        }
        }
        else{
        //prints to wordType relative to the user
        List<wordDriver> temp = stack.get(entriesUser);
        String[] myArray = {"adjective","adverb", "conjunction", "interjection", "noun", "preposition", "pronoun", "verb"};//valid wordType
        System.out.println("    |");//formatting
        int count = 0;
        for(int i = 0; i < myArray.length; i++)//go thorugh array
        {
            count += check_wordType(temp, myArray[i], entriesUser, distinct);
        }
        if(count == 0){
    		System.out.println("    <Not found>");//formatting
        }
        System.out.println("    |");//formatting
      }
    }
    while(true);
  }
}