import java.io.*;
import java.util.*;
import java.lang.Math;
//import Node;
/*
Sloppy code to sort a file by a dictionary
much could be improved upon wiht native types

*/

public class SortByAlpha{
	
	public SortByAlpha(){}
	
	public static long power(int x,int y){
		 long p=1;
		 long b=((long)y)&0x00000000ffffffffL;
		 // bits in b correspond to values of powerN
		 // so start with p=1, and for each set bit in b, multiply corresponding table entry
		 long powerN=x;
		 while(b!=0){
			 if((b&1)!=0) p*=powerN;
			 b>>>=1;
			 powerN=powerN*powerN;
		 }
		 return p;
	} // power
	
	public static void main(String[] args){
		HashMap<Character, Integer> alphaValue = new HashMap<Character, Integer>();
		HashMap<String, Double> wordsValue      = new HashMap<String, Double>();
		
		try{
			System.out.println("Opening Alphabet");
			FileInputStream fstream = new FileInputStream("alphabet.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int charValue = 1; //later used to store the largest value of a character
			
			while ((strLine = br.readLine()) != null)   {
				alphaValue.put(strLine.charAt(0), charValue);
				System.out.println (strLine + " : " + alphaValue.get(strLine.charAt(0)));
				charValue += 1;
			}
			in.close();
			fstream.close();
			
			System.out.println("Opening Dictionary");
			RandomAccessFile raf = new RandomAccessFile(new File("input.txt"), "r");
			
			int maxLength = 0;
			int wordCount = 0;
			while ((strLine = raf.readLine()) != null)   {
				if(strLine.length() > maxLength)maxLength = strLine.length();
				wordCount++;
			}
			System.out.println("Max word length is: " + maxLength +" in " + wordCount + " words");
			raf.seek(0); //reset the file
			
			String[] dataIn = new String[wordCount];
			int i = 0;
			while ((strLine = raf.readLine()) != null)   {
				System.out.println(strLine);
				dataIn[i++] = strLine;
			}
			raf.close();
			
			System.out.println("\nCalculating Word Values...\n\n");
			
			for(i = 0; i<dataIn.length; i++){
				int j = 0;
				double wordValue = 0.0;
				
				for(j = 0; j<charValue; j++){
					if(j<dataIn[i].length()){
						wordValue += (double)(power(maxLength - j, charValue) * dataIn[i].charAt(j))/(double)power(charValue, maxLength);
					}
					else{
						wordValue += (double)(power(maxLength - j, charValue) * charValue)/(double)power(charValue, maxLength);
					}
				}
				wordsValue.put(dataIn[i], wordValue);
			}
			
			//insertion sort 
			Set keys = new HashSet(wordsValue.keySet());
			Iterator it = keys.iterator();
			
			LinkedList sortme = new LinkedList();
			int iteration = 0;
			while(it.hasNext()){
				String thisName = it.next().toString();
				double thisValue = wordsValue.get(thisName);
				
				if(iteration == 0){
					sortme.addFirst(new Node(thisName, thisValue));
					iteration++;
					continue;
				}
				
				int j;
				boolean inserted = false;
				for(j=0; j<iteration; j++){
					Node t = (Node)sortme.get(j);
					if(t.value > thisValue){
						if(j == 0)
							sortme.addFirst(new Node(thisName, thisValue));
						else
							sortme.add(j, new Node(thisName, thisValue));
						inserted = true;
						break;
					}
				}
				if(!inserted){
					sortme.addLast( new Node(thisName, thisValue));
				}
				
				iteration++;
			}//while
			
			for(int j=0; j<sortme.size(); j++){
				Node t = (Node)sortme.get(j);
				System.out.println(t.name + "   " + t.value);
			}
			
    	}catch (Exception e){//Catch exception if any
    		System.err.println("Error: " + e.getMessage());
    	}
	}
	
	
}
