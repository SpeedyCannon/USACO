import java.io.*;
import java.util.*;

public class shortcutEfficient {
	
	static class pair implements Comparable<pair>{
		int f;
		long s;
		
		pair(int first,long second){
			this.f=first;
			this.s=second;
		}
		
		@Override
		public int compareTo(pair p) {
			if(this.s<p.s)return -1;
			if(this.s>p.s)return 1;
			if(this.f<p.f)return -1;
			if(this.f>p.f)return 1;
			return 0;
		}
		
	}
	
	static PriorityQueue<pair> pq;
	static HashMap<Integer,HashSet<pair>>edge;//can be BigInteger, short, long, etc.
	static long[]minDist,c;
	static int[]next;
	static HashMap<Integer,HashSet<Integer>>from;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("shortcut.in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int t=Integer.parseInt(st.nextToken());
		
		st=new StringTokenizer(br.readLine());
		c=new long[n+1];
		visited=new boolean[n+1];
		for(int i=1;i<=n;i++)
			c[i]=Integer.parseInt(st.nextToken());
		
		edge=new HashMap<Integer,HashSet<pair>>();
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int ai=Integer.parseInt(st.nextToken());
			int bi=Integer.parseInt(st.nextToken());
			int ti=Integer.parseInt(st.nextToken());
			
			edge.putIfAbsent(ai,new HashSet<pair>());
			edge.putIfAbsent(bi,new HashSet<pair>());
			edge.get(ai).add(new pair(bi,ti));
			edge.get(bi).add(new pair(ai,ti));
		}
		br.close();
		
		pq=new PriorityQueue<pair>();
		pq.add(new pair(1,0));
		minDist=new long[n+1];
		next=new int[n+1];
		
		for(int i=0;i<minDist.length;i++) {
			minDist[i]=Integer.MAX_VALUE;
			next[i]=i;
		}
		
		update(1);
		
		from=new HashMap<Integer,HashSet<Integer>>();
		for(int i=2;i<=n;i++) {
			from.putIfAbsent(next[i],new HashSet<Integer>());
			from.get(next[i]).add(i);
		}
		cows(1);
		
		long max=0;
		for(int i=2;i<=n;i++)
			max=Math.max(max,(minDist[i]-t)*c[i]);
		System.out.println(Arrays.toString(c));
		pw.println(max);
		pw.close();
	}
	
	public static long cows(int node) {
		if(visited[node])return c[node];
		visited[node]=true;
		long sum=c[node];
		if(from.containsKey(node))
			for(int to:from.get(node))
				sum+=cows(to);
		return c[node]=sum;
	}
	
	public static void update(int node) {
		minDist[node]=0;
		while(!pq.isEmpty()) {
			pair cur=pq.poll();
			if(cur.s<=minDist[cur.f]&&edge.containsKey(cur.f))
				for(pair p:edge.get(cur.f))
					if(minDist[p.f]>cur.s+p.s||minDist[p.f]==cur.s+p.s&&next[p.f]>cur.f) {
						minDist[p.f]=cur.s+p.s;
						pq.add(new pair(p.f,minDist[p.f]));
						next[p.f]=cur.f;
					}
		}
		
	}

}
