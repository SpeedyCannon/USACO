/*
PROB: inflate
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class inflate {
	static final String fileName="inflate";
	static int m;
	
	static class problem implements Comparable<problem> {
		int p,t;
		double ratio;
		
		problem(int points,int time){
			this.p=points;
			this.t=time;
			this.ratio=((double)points)/((double)time);
		}
		
		@Override
		public int compareTo(problem P) {
			if(ratio<P.ratio)return 1;
			if(ratio>P.ratio)return -1;
			if(p*P.t<t*P.p)return 1;
			if(p*P.t>t*P.p)return -1;
			if(t<P.t)return -1;
			if(t>P.t)return 1;
			return -1;
		}
		
	}
	

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		
		m=sc.nextInt();
		int n=sc.nextInt();
		problem[] read=new problem[n];
		
		for(int i=0;i<n;i++)
			read[i]=new problem(sc.nextInt(),sc.nextInt());
		//Arrays.sort(read);
		//TRY GREEDY BEFORE DP BEFORE BRUTE FORCING
		int[] max=new int[m+1];
		int ans=0;
		for(int i=0;i<n;i++)
			for(int j=read[i].t;j<=m;j++)
				max[j]=Math.max(max[j],max[j-read[i].t]+read[i].p);
		for(int i=0;i<=m;i++)
			if(ans<max[i])
				ans=max[i];
		pw.println(ans);
		sc.close();
		pw.close();
	}

}
