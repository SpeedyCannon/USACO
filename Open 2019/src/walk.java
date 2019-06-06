/*
PROB: walk
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class walk {
	
	static class data implements Comparable<data> {
		long comp;
		int x,y;
		
		data(long c,int x,int y){
			comp=c;
			this.x=x;
			this.y=y;
		}
		
		@Override
		public int compareTo(data c) {
			if(comp<c.comp)return -1;
			if(comp>c.comp)return 1;
			if(x<c.x)return -1;
			if(x>c.x)return 1;
			if(y<c.y)return -1;
			if(y>c.y)return 1;
			return 1;
		}
		
	}
	
	static final String fileName="walk";
	static final long mx=2019201913,my=2019201949,MOD=2019201997;
	static int N,K;
	static long[][] val;
	static TreeSet<data>comp=new TreeSet<data>();
	static HashMap<Integer,HashSet<Integer>>banComp=new HashMap<Integer,HashSet<Integer>>();
	static HashMap<Integer,Integer>node=new HashMap<Integer,Integer>();
	static HashMap<Integer,HashSet<Integer>>groups=new HashMap<Integer,HashSet<Integer>>();

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
//		K=Integer.parseInt(st.nextToken());
		br.close();
		val=new long[N+1][2];//acting as x, acting as y
		
		for(int n=1;n<=N;n++) {
			val[n][0]=(mx*((long)n))%MOD;
			val[n][1]=(my*((long)n))%MOD;
			banComp.put(n,new HashSet<Integer>());
		}
		
		long ans=MOD;
		for(int x=1;x<=N;x++)
			for(int y=x+1;y<=N;y++)
				comp.add(new data((val[x][0]+val[y][1])%MOD,x,y));
		
		for(data d:comp) {
			if(banComp.get(d.x).contains(d.y))continue;
			if(banComp.get(d.x).isEmpty()) {//FIRST TIME BANNING, then cnt++ bc removing d.x for first time
				
			}
			//check d.y as well to see if there is a remove
			//if cursize falls below K-usedGroups, then undo change
			else {
				
			}
		}
		
		pw.println(ans);
		pw.close();
	}

}
