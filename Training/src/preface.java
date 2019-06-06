/*
PROB: preface
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class preface {
	static final String fileName="preface";
	static int[][][]letters= {
			{//ones place
			//   I V X L C D M
				{0,0,0,0,0,0,0},
				{1,0,0,0,0,0,0},
				{2,0,0,0,0,0,0},
				{3,0,0,0,0,0,0},
				{1,1,0,0,0,0,0},
				{0,1,0,0,0,0,0},
				{1,1,0,0,0,0,0},
				{2,1,0,0,0,0,0},
				{3,1,0,0,0,0,0},
				{1,0,1,0,0,0,0}
			},
			{
				{0,0,0,0,0,0,0},
				{0,0,1,0,0,0,0},
				{0,0,2,0,0,0,0},
				{0,0,3,0,0,0,0},
				{0,0,1,1,0,0,0},
				{0,0,0,1,0,0,0},
				{0,0,1,1,0,0,0},
				{0,0,2,1,0,0,0},
				{0,0,3,1,0,0,0},
				{0,0,1,0,1,0,0}
			},
			{//100s place
			//   I V X L C D M
				{0,0,0,0,0,0,0},
				{0,0,0,0,1,0,0},
				{0,0,0,0,2,0,0},
				{0,0,0,0,3,0,0},
				{0,0,0,0,1,1,0},
				{0,0,0,0,0,1,0},
				{0,0,0,0,1,1,0},
				{0,0,0,0,2,1,0},
				{0,0,0,0,3,1,0},
				{0,0,0,0,1,0,1}
			},
			{
				{0,0,0,0,0,0,0},
				{0,0,0,0,0,0,1},
				{0,0,0,0,0,0,2},
				{0,0,0,0,0,0,3},
				{0,0,0,0,0,0,4},
				{0,0,0,0,0,0,5},
				{0,0,0,0,0,0,6},
				{0,0,0,0,0,0,7},
				{0,0,0,0,0,0,8},
				{0,0,0,0,0,0,9}
			}
	};

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		int n=sc.nextInt();
		sc.close();
		int[] cnt=new int[7];
		for(int i=1;i<=n;i++) {
			char[] c=new StringBuilder(i+"").reverse().toString().toCharArray();
			for(int digit=0;digit<c.length;digit++)
				for(int letter=0;letter<7;letter++)
					cnt[letter]+=letters[digit][c[digit]-'0'][letter];
		}
		int i=6;
		for(;i>0;i--)
			if(cnt[i]!=0)break;
		char[]roman= {'I','V','X','L','C','D','M'};
		for(int j=0;j<=i;j++)
			pw.println(roman[j]+" "+cnt[j]);
		pw.close();
	}

}
