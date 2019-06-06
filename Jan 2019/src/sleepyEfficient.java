import java.io.*;
import java.util.StringTokenizer;

public class sleepyEfficient {
	
	static int[] cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("sleepy.in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		
		int n=Integer.parseInt(br.readLine());
		int p[]=new int[n+1];
		cnt=new int[n+1];
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++)
			p[i]=Integer.parseInt(st.nextToken());
		
		int last=n;
		while(last>1&&p[last]>p[last-1]) {
			add(p[last]);
			last--;
		}
		add(p[last]);
		
		pw.println(last-1);
		StringBuilder sb=new StringBuilder();
		for(int i=1;i<last;i++) {
			if(i!=1)sb=sb.append(' ');
			sb=sb.append(last-i-1+value(p[i]));
			add(p[i]);
		}
		
		pw.println(sb.toString());
		br.close();
		pw.close();
	}
	
	public static void add(int i) {
		while(i<cnt.length) {
			cnt[i]++;
			i+=(i&-i);
		}
	}
	
	public static int value(int i) {
		int sum=0;
		while(i>0) {
			sum+=cnt[i];
			i-=(i&-i);
		}
		return sum;
	}

}
