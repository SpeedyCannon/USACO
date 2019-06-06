/*
 ID: genel061
 LANG: JAVA
 TASK: nocows
 */

import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class nocows {
	
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(System.in);
		//Scanner sc=new Scanner(new File("nocows.in"));
		//PrintWriter pw=new PrintWriter(new File("nocows.out"));
		int N=sc.nextInt();
		int K=sc.nextInt();
		sc.close();
		
		long[][]dp=new long[201][101];
		for(int k=1;k<=K;k++) {//all heights
			dp[1][k]=1;
			for(int n=2;n<=N;n++)//all nodes
				for(int p=1;p<=n-2;p++) {//previous nodes
					dp[n][k]+=dp[p][k-1]*dp[n-p-1][k-1];
					dp[n][k]%=9901;
				}
		}
		for(long[] a:dp)
			System.out.println(Arrays.toString(a));
		
		//pw.println((dp[N][K]-dp[N][K-1]+9901)%9901);
		//pw.close();
		
	}

}