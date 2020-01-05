package adventofcode2019;

public class Day4 {
    
    public static void main(String[] args) {
        //int i = 128392;
        //int j = 643281;
        int count = 0;
        //System.out.println(hasDouble(221));
        //System.out.println(hasDouble(643281));
        //System.out.println(neverDecreases(111123));
        //System.out.println(neverDecreases(100));
        //System.out.println((hasDouble(1234) && neverDecreases(11111)));
        //System.out.println(neverDecreases(221));
        //System.out.println(neverDecreases(234));
        //System.out.println(neverDecreases(11111));
        System.out.println(hasDouble2(22333));
        System.out.println(hasDouble2(33322));
        System.out.println(hasDouble2(3322));
        System.out.println(hasDouble2(333222));
        System.out.println(hasDouble2(333333));
        System.out.println(hasDouble2(33333));
        System.out.println(hasDouble2(22));
        System.out.println(hasDouble2(1));
        for (int i = 128392; i <= 643281; i++) {
            if (hasDouble2(i) && neverDecreases(i)) {
                count++;
            }
        }
        System.out.println(count);
    }
    
    public static boolean hasDouble2(int num) {
        boolean curDouble = false;
        int digit = num%10;
        int prevDigit = 0;
        num = num/10;
        while (num > 0) {
            int nextDigit = num%10;
            if (digit == nextDigit && curDouble) {
                curDouble = false;
            }
            else if (digit != nextDigit && curDouble) {
                return true;
            }
            else if (digit == prevDigit && digit == nextDigit) {
                curDouble = false;
            }
            else if (digit == nextDigit) {
                curDouble = true;
            }
            prevDigit = digit;
            digit = nextDigit;
            num = num/10;
        }
        return curDouble;
    }
    
    public static boolean hasDouble(int num) {
        int digit = num%10;
        num = num/10;
        while (num > 0) {
            int nextDigit = num%10;
            if (digit == nextDigit) {
                return true;
            }
            digit = nextDigit;
            num = num/10;
        }
        return false;
    }
    
    public static boolean neverDecreases(int num) {
        int digit = num%10;
        //System.out.println("First digit: " + digit);
        num = num/10;
        //System.out.println("Next num: " + num);
        while (num > 0) {
            int nextDigit = num%10;
            //System.out.println("Next digit: " + nextDigit);
            if (digit < nextDigit) {
                //System.out.println("Return FALSE");
                return false;
            }
            digit = nextDigit;
            //System.out.println("Third digit: " + digit);
            num = num/10;
            //System.out.println("Final num: " + num);
        }
        //System.out.println("Return TRUE");
        return true;
    }
}
