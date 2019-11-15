package com.appThis;

import com.appThis.FunWithDigits;
import org.junit.Test;


import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

public class Tests {
    private FunWithDigits fwd = new FunWithDigits();

    @Test
    public void firstExample(){
        fwd.setM(2);
        assertEquals("1912", fwd.FunWithDigits(new int[]{9,4,2,7,9,0}) );
    }

    @Test
    public void secondExample(){
        fwd.setM(2);
        assertEquals("10661", fwd.FunWithDigits(new int[]{9,4,2,7,9,0,1}) );
    }

    @Test
    public void thirdExample(){
        fwd.setM(2);
        assertEquals("36432", fwd.FunWithDigits(new int[]{3,2,1,3,2,1,3,2,1}));
    }

    @Test
    public void noArgs(){
        fwd.setM(2);
        assertNull(fwd.FunWithDigits(new int[]{}));
    }

    @Test
    public void charArgs(){
        fwd.setM(2);
        assertEquals("1912", fwd.FunWithDigits(new char[]{'9','4','2','7','9','0'}) );
    }

    @Test
    public void stringArg(){
        fwd.setM(2);
        assertEquals("1912", fwd.FunWithDigits("942790") );
    }

    @Test
    public void oneArg(){
        fwd.setM(2);
        assertEquals("9", fwd.FunWithDigits(new int[]{9}) );
        assertEquals("1", fwd.FunWithDigits(new int[]{1}) );
    }

    @Test
    public void negativeArg(){
        try {
            fwd.FunWithDigits(new int[]{-1, 2, 3});
            fail(); //should except and not get here
        }catch(Exception e){
            //good
        }
    }

    @Test
    public void longOverflowTest(){
        /*
          largest long 9,223,372,036,854,775,807
          should make sure this can handle large numbers
         */
        fwd.setM(2);
        assertEquals("222222222222222222222", fwd.FunWithDigits(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}));
        assertEquals("1999999999999999999998", fwd.FunWithDigits(new int[]{9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9}));
    }

    @Test
    public void changeNumberBucketSize(){
        fwd.setM(2);
        assertEquals("2", fwd.FunWithDigits(new int[]{1,1}));

        fwd.setM(3);
        assertEquals("3", fwd.FunWithDigits(new int[]{1,1,1}));

        fwd.setM(4);
        assertEquals("158", fwd.FunWithDigits(new int[]{3,6,4,9,0,1}));

        fwd.setM(3);
        assertEquals("484", fwd.FunWithDigits(new int[]{1,2,3,1,2,3,4}));
    }

    @Test
    public void sillyBig(){
        int size = Integer.MAX_VALUE/10000; //need something big to stress test

        //want evens for this kind of test
        if(size % 2 == 1)size++;

        StringBuilder sbIn = new StringBuilder();
        StringBuilder sbOut = new StringBuilder();
        for(int i = 0; i < size; i++){
            sbIn.append("1");
            if(i < size/2){
                sbOut.append("2");
            }
        }
        long startTime = System.nanoTime();
        assertEquals(sbOut.toString(), fwd.FunWithDigits(sbIn.toString()));
        long stopTime = System.nanoTime();

        System.out.println("Elapsed Time in seconds: " + ((stopTime - startTime)/1000000000.0));
    }

}
