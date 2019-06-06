/*
PROB: agrinet
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class agrinet {
	static final String fileName="agrinet";
	static class edges implements Comparable<edges>{
		int v, w;
		
		edges(int b, int c){
			v=b;
			w=c;
		}
		
		@Override
		public int compareTo(edges o) {
			return this.w-o.w;
		}	

		public String toString(){
			return v+" "+w;
		}

	}
	
	static LinkedList<edges>[] a;
	static int[] d;
	static boolean[] v;
	static int n, m;
	static int INF = 10000000;
	
	public static int prim(int s) {	
		PriorityQueue<edges>q=new PriorityQueue<edges>();
		v[s]=true;
		d[s]=0;
		int cnt=1;
		int sum = 0;
		
		for(edges temp:a[s])
			if(!v[temp.v])q.add(temp);
	
		while(!q.isEmpty()){
			edges next=q.poll();
			int next_v = next.v;
			int next_w = next.w;
			
			if(!v[next_v]){
				//System.out.println(next);
				v[next_v]=true;
				sum+=next_w;
				cnt++;
				if(cnt==n) break;
				for(edges temp:a[next_v])
					if(!v[temp.v])q.add(temp);
			}
		}
		return sum;
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		n=sc.nextInt();
		
		a = new LinkedList[n+1];
		v = new boolean[n+1];
		d = new int[n+1];
		for(int i=1; i<=n; i++){
			v[i] = false;
			d[i] = INF;
			a[i] = new LinkedList<edges>();
		}
		int read=0;
		for(int i=1;i<=n;i++)
			for(int j=1;j<=n;j++)
				if((read=sc.nextInt())!=0) {
					a[i].add(new edges(j,read));
					a[j].add(new edges(i,read));
				}
		pw.println(prim(1));
		sc.close();
		pw.close();
	}

}
