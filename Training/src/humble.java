/*
ID: genel061
LANG: JAVA
TASK: humble
*/
import java.io.*;
import java.util.*;
import static java.lang.Math.*;

public class humble 
{
	public static void main(String[] args) throws IOException 
	{
		BufferedReader f = new BufferedReader(new FileReader("humble.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
		StringTokenizer st = new StringTokenizer(f.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] nums = new int[K];
		st = new StringTokenizer(f.readLine());
		for(int i = 0; i < K;i++)
			nums[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(nums);			
		
		int[] hum = new int[N+1];
		int[] next = new int[K];
		
		hum[0] = 1;
		for(int i=1;i<=N;i++){
			int best=Integer.MAX_VALUE;
			for(int j=0;j<K;j++){
				while(next[j] < i && nums[j]*hum[next[j]] <= hum[i-1])next[j]++;
				best = min(best,nums[j]*hum[next[j]]);
			}
			hum[i] = best;
		}
		
		out.println(hum[N]);
		out.close();
		System.exit(0);
	}
	
}
