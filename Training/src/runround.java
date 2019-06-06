/*
 ID: genel061
 LANG: JAVA
 TASK: runround
 */

import java.util.HashSet;
import java.util.Scanner;
import java.io.*;

public class runround {
	
	public static void main(String[] args) throws IOException {
		// Scanner in = new Scanner("99");
		Scanner in = new Scanner(new File("runround.in"));
		PrintWriter pw = new PrintWriter(new File("runround.out"));
		int N = in.nextInt();
		in.close();
		int i;
		for(i = N + 1; ! isRunRound(Integer.toString(i)); i++);//Integer.toString(i) is MUCH FASTER THAN i+""
		pw.println(i);//STRING AND .charAt IS FASTER THAN .toCharArray and [index]
		pw.close();
	}
	
	// for each digit, check whether the digit at (digit % num.length + digitPos) % num.length does not equal digit
	public static boolean isRunRound(String num) {
		int covered=0;//BIT OPERATORS MUCH FASTER THAN HASHING
		int cnt = 0;
		int digitPos = 0;
		while(cnt < num.length()) {
			int digit = num.charAt(digitPos)-'0';
			int newPos = (digit % num.length() + digitPos) % num.length();
			if((covered&(1<<(num.charAt(newPos)-'0')))!=0) return false;
			digitPos = newPos;
			cnt++;
			covered=covered|(1<<(num.charAt(newPos)-'0'));
		}
		return true;
	}

}