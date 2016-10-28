import java.lang.*;

public class Viper{
    public Viper(){

    }

    //find the largest palendrome in a string O(n^2)
    public static String largestPalendrome(String s){
        String ret = "";

        for(int i = 0; i<s.length(); i++){
            String sub = s.substring(i);
            for(int j = 0;  j < sub.length(); j++){
                String test = sub.substring(0, sub.length()-j);
                if(squeezeTest(test) && test.length() > ret.length() ){
                //if(isPalendrome(test) && test.length() > ret.length() ){
                    ret = test;
                    break;
                }
            }
        }
        return ret;
    }

    private static boolean squeezeTest(String s){
        if(s.length() <= 1)
            return true;

        if( s.charAt(0) == s.charAt(s.length()-1) )
            return squeezeTest( s.substring(1).substring(0, s.length()-2) );
        else
            return false;
    }

    //function to test for a palendrome
    public static boolean isPalendrome(String s){
        if(s.length() == 0)
            return false;

        return s.equals(reverse(s));
    }

    //function to reverse a string
    public static String reverse(String s){
        StringBuilder sb = new StringBuilder();

        for(int i = s.length()-1; i>=0; i--){
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}