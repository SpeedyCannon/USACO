import java.io.*;
import java.util.*;

public class shortcut {//WATCH OUT FOR INTEGER LIMIT
	
	static class path implements Comparable<path>{
		LinkedList<Integer>p;
		int e;
		long w;
		
		path(int end,long weight,LinkedList<Integer>path){
			this.e=end;
			this.w=weight;
			this.p=path;
		}
		
		@Override
		public int compareTo(path p) {
			if(this.w<p.w)return -1;
			if(this.w>p.w)return 1;
			//DOUBLE CHECK
			return prefPath(this.p,p.p);
		}
		
		public int prefPath(LinkedList<Integer>l1,LinkedList<Integer>l2){
			Iterator<Integer>i1=l1.descendingIterator();
			Iterator<Integer>i2=l2.descendingIterator();
			while(true) {
				if(!i1.hasNext()&&!i2.hasNext())return 0;
				if(!i1.hasNext())return -1;
				if(!i2.hasNext())return 1;
				int n1=i1.next();
				int n2=i2.next();
				if(n1<n2)return -1;
				if(n1>n2)return 1;
			}
		}
		
		@Override
		public String toString() {
			return w+" "+p;
		}
	}

	static PriorityQueue<path> pq;
	static HashMap<Integer,HashSet<path>>p;//can be BigInteger, short, long, etc.
	static path[]minDist;
	static int[] totalCows;
	static int[] c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("shortcut.in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));
		p=new HashMap<Integer,HashSet<path>>();
		pq=new PriorityQueue<path>();
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int t=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		c=new int[n+1];
		totalCows=new int[n+1];
		for(int i=1;i<=n;i++)
			c[i]=Integer.parseInt(st.nextToken());
		for(int i=0;i<m;i++) {
			st=new StringTokenizer(br.readLine());
			int start=Integer.parseInt(st.nextToken());
			int end=Integer.parseInt(st.nextToken());
			int weight=Integer.parseInt(st.nextToken());
			LinkedList<Integer>path1=new LinkedList<Integer>();
			path1.add(start);
			path1.add(end);
			LinkedList<Integer>path2=new LinkedList<Integer>();
			path2.add(end);
			path2.add(start);
			p.putIfAbsent(start,new HashSet<path>());
			p.putIfAbsent(end, new HashSet<path>());
			p.get(start).add(new path(end,weight,path1));
			p.get(end).add(new path(start,weight,path2));
		}
		
		int start=1;
		LinkedList<Integer>temp=new LinkedList<Integer>();
		temp.add(start);
		pq.add(new path(start,0,temp));
		minDist=new path[p.size()+1];
		for(int i=0;i<minDist.length;i++)
			minDist[i]=new path(i,1000000000000000000L,new LinkedList<Integer>());
		update(start);
		
		for(int i=2;i<=n;i++)
			if(!minDist[i].p.isEmpty())
				for(int index:minDist[i].p)
					totalCows[index]+=c[i];
		int max=0;
		for(int i=2;i<=n;i++)
			if(minDist[i].w>t) {
				totalCows[i]*=minDist[i].w-t;
				max=(int)Math.max(totalCows[i],max);
			}
		
		pw.println(max);
		br.close();
		pw.close();
	}
	
	public static void update(int node) {
		minDist[node]=new path(node,0,new LinkedList<Integer>());
		while(!pq.isEmpty()) {
			path cur=pq.poll();
			if(cur.w>minDist[cur.e].w)continue;
			if(p.containsKey(cur.e))
				for(path P:p.get(cur.e)) {
					LinkedList<Integer>temp=(LinkedList<Integer>) cur.p.clone();
					temp.add(P.e);
					path potential=new path(P.e,cur.w+P.w,temp);
					if(minDist[P.e].compareTo(potential)==1) {
						minDist[P.e]=potential;
						pq.add(potential);
					}
				}
		}
	}

}
