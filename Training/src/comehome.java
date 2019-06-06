/*
PROB: comehome
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class comehome {
	static final String fileName="comehome";
	static class pair implements Comparable<pair>{
		char f;
		int s;
		
		pair(char first,int second){
			this.f=first;
			this.s=second;
		}
		
		public int compareTo(pair p) {
			if(this.s<p.s)return -1;
			if(this.s>p.s)return 1;
			if(this.f<p.f)return -1;
			if(this.f>p.f)return 1;
			return 0;
		}
		
	}
	
	static PriorityQueue<pair> pq;
	static HashMap<Character,HashSet<pair>>edge;//can be BigInteger, short, long, etc.
	static int[]minDist;
	
	static char toIndex(char c) {
		if(Character.isUpperCase(c))return (char)(c-'A');
		return (char)(c-'a'+26);
	}
	
	public static void main(String[] args) throws IOException {
		edge=new HashMap<Character,HashSet<pair>>();
		pq=new PriorityQueue<pair>();
		Scanner sc=new Scanner(new File(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		int p=sc.nextInt();sc.nextLine();
		for(int i=0;i<p;i++) {
			char start=toIndex(sc.next().charAt(0)),end=toIndex(sc.next().charAt(0));
			int weight=sc.nextInt();sc.nextLine();
			edge.putIfAbsent(start,new HashSet<pair>());
			edge.putIfAbsent(end, new HashSet<pair>());
			edge.get(start).add(new pair(end,weight));
			edge.get(end).add(new pair(start,weight));
		}
		pq.add(new pair(toIndex('Z'),0));
		minDist=new int[52];
		for(int i=0;i<minDist.length;i++)
			minDist[i]=Integer.MAX_VALUE;
		update(toIndex('Z'));
		int ans=Integer.MAX_VALUE;
		int idx=0;
		for(int i=0;i<25;i++)
			if(ans>minDist[i]) {
				ans=minDist[i];
				idx=i;
			}
		pw.println((char)(idx+'A')+" "+ans);
		sc.close();
		pw.close();
	}
	
	public static void update(int node) {
		minDist[node]=0;
		while(!pq.isEmpty()) {
			pair cur=pq.poll();
			if(cur.s>minDist[cur.f])continue;
			if(edge.containsKey(cur.f))
				for(pair p:edge.get(cur.f))
					if(minDist[p.f]>cur.s+p.s) {
						minDist[p.f]=cur.s+p.s;
						pq.add(new pair(p.f,minDist[p.f]));
					}
		}
	}
}
