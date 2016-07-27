import java.util.*;
/*
get the first character that occurs in a string only once
 */
class FirstUnique{
    String test;

    HashMap<Character, Integer> counts             = new HashMap<Character, Integer>();
    HashMap<Character, Integer> firstOccurences    = new HashMap<Character, Integer>();

    public FirstUnique(String t){
        test = t;

        for(int i = 0; i<test.length(); i++){
            Character c = new Character(test.charAt(i));
            counts.put(c, counts.getOrDefault(c, 0) + 1);

            if(counts.get(c) == 1) { //are we first?
                firstOccurences.put(c, i);
            }
        }

        Character winner = null;
        int lowest = test.length();
        for(Character co: counts.keySet()){
            if(counts.get(co) == 1){
                if(firstOccurences.get(co) < lowest){
                    lowest = firstOccurences.get(co);
                    winner = co;
                }
            }
        }
        if(winner != null) {
            System.out.println("winner is " + winner);
        }else{
            System.out.println("no match");
        }
    }

    public static void main(String args[]){
        new FirstUnique("laababbasdfasdfasdfasddfasdfasdf");
    }
}