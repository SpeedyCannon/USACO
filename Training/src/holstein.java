/*
PROB: holstein
ID: genel061
LANG: JAVA
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class holstein {
	
	static int[] req;
	static int[][] scoop;

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File("holstein.in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));
		int v=sc.nextInt();
		req=new int[v];
		for(int i=0;i<v;i++)
			req[i]=sc.nextInt();
		int g=sc.nextInt();
		scoop=new int[g][v];
		for(int i=0;i<g;i++)
			for(int j=0;j<v;j++)
				scoop[i][j]=sc.nextInt();
		int minScoops=g;
		int useIdx=(1<<g)-1;
		int allCases=1<<g;//System.out.println(allCases);
		for(int i=0;i<allCases;i++) {
			int temp=countOnes(i);
			if((temp<minScoops||temp==minScoops&&i==minIdx(i,useIdx))&&meetsReq(i)) {
				minScoops=temp;
				useIdx=i;
			}//CHECK MINIDX
		}
		pw.println(toString(minScoops,useIdx));
		sc.close();
		pw.close();
	}
	
	public static String toString(int minScoops, int n) {
		StringBuilder sb=new StringBuilder(minScoops+"");
		while(n!=0) {
			sb.append(' ').append(Integer.numberOfTrailingZeros(n)+1);
			n=n&(n-1);
		}
		return sb.toString();
	}
	
	public static int minIdx(int a,int b) {
		return Integer.numberOfTrailingZeros((a^b)&a)<Integer.numberOfTrailingZeros((a^b)&b)?a:b;
	}
	
	public static int countOnes(int n) {
		int ret=0;
		while(n!=0) {
			n=n&(n-1);
			ret++;
		}
		return ret;
	}
	
	public static boolean meetsReq(int n) {
		int[] total=new int[req.length];
		while(n!=0) {
			int index=Integer.numberOfTrailingZeros(n);
			n=n&(n-1);
			for(int i=0;i<total.length;i++)
				total[i]+=scoop[index][i];
		}
		for(int i=0;i<total.length;i++)
			if(total[i]<req[i])return false;
		return true;
	}

}
