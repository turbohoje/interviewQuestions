package com.appThis;

import java.math.BigInteger;
import java.util.Arrays;
import asg.cliche.*;
import java.io.IOException;

public class FunWithDigits {
    private int m = 2;   // max M, this is not 0-th'd
    private int myM = 0; // next M value, this is 0-th'd

    public static void main(String[] args) throws IOException {
        ShellFactory.createConsoleShell("FunWithDigits", "appThisTestCLI", new FunWithDigits()).commandLoop();
    }

    @Command(description="Digits: enter digits (no spaces)", abbrev="d")
    public String getDigitsFromUser(String s){
        return FunWithDigits(s);
    }

    @Command(description="Quit", abbrev="q")
    public void quit(){
        System.exit(0);
    }

    /*
       M is the number of "numbers" we break the string into, this is not 0-th'd
       however myM is 0th'd
     */
    @Command(description="Get M value", abbrev="gm")
    public int getM(){
        return this.m;
    }
    @Command(description="Set M value", abbrev="sm")
    public void setM(int m){
        if(m <= 0) return;
        this.m = m;
    }

    public String FunWithDigits(int[] input) {
        if(input.length == 0){
            return null; //or maybe throw InvlaidInputEx
        }else if(input.length == 1){
            return Integer.toString(input[0]);
        }

        StringBuilder stringBank[] = new StringBuilder[m];
        for(int i = 0; i < m; i++){
            stringBank[i] = new StringBuilder();
        }

        int descending[] = new int[input.length];
        Arrays.sort(input);
        //reverse array (not a collection) and bounds chk
        for(int i = input.length-1; i >= 0; i--){
            descending[i] = input[(input.length-1)-i];
            if(descending[i] < 0 || descending[i] > 9){
                throw new IllegalStateException();
            }
        }

        for(int i : descending) {
            stringBank[myM].append(i);
            advanceM();
        }

        BigInteger[] numberStash = new BigInteger[m];
        for(int i = 0; i < m; i++){
            numberStash[i] = new BigInteger(stringBank[i].toString());
        }

        BigInteger result = new BigInteger("0");
        for(int i = 0; i < m; i++){
            result = result.add(numberStash[i]);
        }
        return result.toString();
    }

    private void advanceM(){
        myM ++;
        if(myM == m){ // == much faster than >=, used for performance here
            myM = 0;
        }
    }

    public String FunWithDigits(char[] input) {
        int intput[] = new int[input.length];
        for(int i = 0; i < input.length; i++){
            intput[i] = Integer.parseInt(Character.toString(input[i]));
        }
        return FunWithDigits(intput);
    }

    public String FunWithDigits(String str){
        int input[] = new int[str.length()];
        for(int i = 0; i < input.length; i++){
            input[i] = Integer.parseInt(Character.toString(str.charAt(i)));
        }
        return FunWithDigits(input);
    }
}
