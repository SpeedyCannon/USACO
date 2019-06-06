/*
PROB: balance
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class balance {
	static final String fileName="balance";
	
	//swap bit f (which contains 1) and bit t (which contains 0)
	//f and t must be on opposite sides
	public static void updateScore(int f,int to) {
		
	}
//when you move a 1 from left to right, when you move a 1 from right to left, and difference between original scores
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(/*new FileReader(fileName+".in")*/new InputStreamReader(System.in));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		int n=Integer.parseInt(br.readLine());
		boolean[] a=new boolean[2*n];
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<2*n;i++)
			a[i]=Integer.parseInt(st.nextToken())==1;
		
		long lscore,rscore;
		lscore=rscore=0;
		int lcnt=0,rcnt=0;
		int llast0,llast1,rfirst0,rfirst1;
		llast0=llast1=rfirst0=rfirst1=-1;
		int limit=a.length/2;
		for(int i=0;i<limit;i++) {
			if(a[i]) {
				lscore+=limit-i-1-(lcnt++);
				llast1=i;
			}
			else llast0=i;
			if(a[i+limit]) {
				rscore+=limit-i-1-(rcnt++);
				if(rfirst1==-1)rfirst1=i;
			}
			else if(rfirst0==-1)rfirst0=i;
		}
		
		int ans=(int)Math.abs(lscore-rscore);
		
		
		
		br.close();
		
		pw.close();
	}

}
