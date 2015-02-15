/**
 *  This is a Java template for CS3230 - Programming Assignment 1 - Part 1
 *  (January-2015)
 *
 *  You are not required to follow the template. Feel free to modify any part.
 *
 *  Comment your code!
 */


import java.io.*;
import java.util.*;


class Template { // in Mooshak online judge, make sure that Java file name = class name that contains Main method
	
	static final int MAX_SIZE = 10;
	
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out))); 
        // use this (a much faster output routine) instead of Java System.out.println (slow)
        
        int t, b;
		String v, m, p; 
        t = sc.nextInt();
		
        for (int i = 1; i <= t; ++i) {
            b = sc.nextInt();
			sc.nextLine();
			
			v = sc.nextLine(); 
			m = sc.nextLine();
			p = ""; // p is the momentum
			
			// Insert solution here.
			int vDecimals = getDecimals(v);
			int mDecimals = getDecimals(m);
			int pDecimals = vDecimals + mDecimals;
			
			// string concats are scary monsters.
			
			v = removeDot(v);
			m = removeDot(m);
			
			int[] vArr = toIntArray(v, Math.max(v.length(), m.length()));
			int[] mArr = toIntArray(m, Math.max(v.length(), m.length()));
			
			int[] pArr = multiply(vArr, mArr, b);
			
			// make back into string
			p = makeString(pArr);
			// add decimal point
			p = installDot(p, pDecimals);
			// trim zeros
			// remove trailing dot
			p = trimZeros(p);
			
			pw.write(p);
			pw.write("\n");
        }
        pw.close(); // do not forget to use this
        sc.close();
    }
    
    private static String installDot(String s, int pos) {
    	String ans = "";
    	
    	if (pos == 0) {
    		StringBuilder sb = new StringBuilder(".");
    		sb.append(s);
    		ans = sb.toString();
    	} else {
    		StringBuilder sb = new StringBuilder(s.substring(0, pos));
    		sb.append(".");
    		sb.append(s.substring(pos, s.length()));
    		ans = sb.toString();
    	}
    	
    	ans = new StringBuilder(ans).reverse().toString();
    	
		return ans;
	}
    
    private static String makeString(int[] a) {
    	StringBuilder s = new StringBuilder();
    	
    	for (int i = 0; i < a.length; i++) {
    		s.append(toDigit(a[i]));
    	}
    	
    	return s.toString();
    }

	public static int[] multiply(int[] x, int[] y, int b) {
    	// ensure the lower digit number below
    	if (x.length < y.length) {
    		return multiply(y, x, b);
    	}
    	
    	int[] ans = new int[x.length + y.length];
    	
    	// do long mult by placing each subsequent mult on top of the below one
    	for (int i = 0; i < y.length; i++) {
    		for (int j = 0; j < x.length; j++) {
    			ans[j + i] = x[j] * y[i] + ans[j + i];
    		}
    	}
    	
    	// carry over 
    	int car = 0;
    	for (int i = 0; i < ans.length; i++) {
    		int sum = ans[i] + car;
    		int rem = sum % b;
    		ans[i] = rem;
    		car = sum / b;
    	}
    	
    	return ans;
    }
    
    
    private static int[] toIntArray(String s, int size) {
    	s = new StringBuilder(s).reverse().toString();
    	
    	int[] array = new int[size];
    	boolean passedDot = false;
    	for (int i = 0; i < s.length(); i++) {
    		if (s.charAt(i) == '.') {
    			passedDot = true;
    			continue;
    		} else {
    			int j = i;
    			if (passedDot) {
    				j--;
    			}
    			array[j] = parseDigit(s.charAt(i));
    		}
    	}
    	
    	return array;
    }
    
    private static String removeDot(String s) {
    	String ans = s;
    	if (s.contains(".")) {
    		String[] sArr = s.split("\\.");
    		StringBuilder ansB = new StringBuilder(sArr[0]);
    		ansB.append(sArr[1]);
    		ans = ansB.toString();
    	}
    	
    	return ans;
    }
    
    private static int getDecimals(String s) {
    	int decimals = 0;
    	
    	if (s.contains(".")) {
    		String[] sArr = s.split("\\.");
    		decimals = sArr[1].length();
    	}
    	
    	return decimals;
    }
	
	
	/**
	 * Use to trim leading and trailing zeros on a result string.
	 */
	private static String trimZeros(String input) {
		int left = 0;
		int right = input.length()-1;
		int fp = input.indexOf('.');
		if (fp == -1) {
			fp = input.length();
		}
		
		while(left < fp-1) {
			if (input.charAt(left) != '0')
				break;
			left++;
		}
		
		while (right >= fp) {
			if (input.charAt(right) != '0') {
				if (input.charAt(right) == '.')
					right--;
				break;
			}
			right--;
		}
		
		if (left >= fp)
			return "0" + input.substring(left,right+1);
		return input.substring(left,right+1);
	}
    
	/**
	 * Convert digit to int (for reading)
	 */
	private static int parseDigit(char c) {
		if (c <= '9') {
			return c - '0';
		} 
		return c - 'A' + 10;
	}
	
	/**
	 * Convert int to digit. (for printing)
	 */
	private static char toDigit(int digit) {
		if (digit <= 9) {
			return (char)(digit + '0');
		} 
		return (char)(digit - 10 + 'A');
	}
	
	private static void print(Object o) {
		//System.err.println(o);
	}
}
