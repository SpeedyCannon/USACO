/*
PROB: snakes
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class snakes {
	static final String fileName="snakes";
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken()),k=Integer.parseInt(st.nextToken());
		int[][] dp=new int[n+1][k+1];
		int[] s=new int[n+1];
		
		st=new StringTokenizer(br.readLine());
		int maxS=0;//Takes max of snakes [0,i]
		int sum=0;
		for(int i=1;i<=n;i++) {
			sum+=s[i]=Integer.parseInt(st.nextToken());
			maxS=Integer.max(s[i],maxS);
			dp[i][0]=i*maxS;//Means no net size change possible, must cover everything
			for(int j=1;j<=k;j++) {
				dp[i][j]=Integer.MAX_VALUE;
				int maxB=s[i];
				for(int b=i-1;b>=0;b--) {
					dp[i][j]=Integer.min(dp[i][j],dp[b][j-1]+(i-b)*maxB);
					maxB=Integer.max(maxB,s[b]);
				}
			}
		}
		br.close();
		pw.println(dp[n][k]-sum);
		pw.close();
	}

}
