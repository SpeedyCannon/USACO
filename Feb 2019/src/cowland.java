/*
PROB: cowland
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class cowland {
	static final String fileName="cowland";
	static int[] value;
	static int[] line;
	static int[] ind;
	static int[] root;
	static int[] e;//WHEN YOU CHOOSE ROOT, REMOVE EDGE child->root before going to child
	static HashMap<Integer,HashSet<Integer>>edge=new HashMap<Integer,HashSet<Integer>>();
	static int n,q,center,XOR;
	
	private static void update(int node) {
		value[node]^=XOR;
		if(!edge.get(node).isEmpty())
			for(int i:edge.get(node))
				update(i);
	}
	
	public static void update(int i,int v) {
		XOR=e[i]^v;
		update(i);
		e[i]=v;
	}
	
	public static int dist(int node1,int node2) {
		if(node1==node2)return 0;
		if(line[node1]==line[node2]) {
			if(ind[node1]<ind[node2])return value[node1]^value[node2]^e[node1];
			return value[node1]^value[node2]^e[node2];
		}
		HashSet<Integer>roots=new HashSet<Integer>();
		roots.add(node1);
		roots.add(node2);
		int cur1=node1,cur2=node2,commonRoot=-1;
		while(true) {
			if(cur1!=center&&roots.contains(root[cur1])) {
				commonRoot=root[cur1];
				break;
			}
			if(cur2!=center&&roots.contains(root[cur2])||cur1==cur2) {
				commonRoot=root[cur2];
				break;
			}
			roots.add(cur1=root[cur1]);
			roots.add(cur2=root[cur2]);
		}
		int ret=0;
		for(cur1=node1;cur1!=commonRoot;cur1=root[cur1])
			ret^=value[cur1];
		for(cur2=node2;cur2!=commonRoot;cur2=root[cur2])
			ret^=value[cur2];
		
		if(line[node1]==line[commonRoot])
			return ret^e[node1];
		if(line[node2]==line[commonRoot])
			return ret^e[node2];
		return ret^e[commonRoot];
	}
	
	static int curLine=0;
	public static void construct(int cur,int r,int index) {
		root[cur]=r;
		line[cur]=curLine;
		ind[cur]=index;
		if(edge.get(cur).isEmpty())return;
		if(edge.get(cur).size()==1) {
			int i=edge.get(cur).iterator().next();
			edge.get(i).remove(cur);
			value[i]=value[cur]^e[i];
			construct(i,r,index+1);
		}
		else
			for(int i:edge.get(cur)) {
				edge.get(i).remove(cur);
				value[i]=value[cur]^e[i];
				curLine++;
				construct(i,cur,0);
			}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		//find the node with the MOST edges, set the root of that node to 0
		StringTokenizer st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		q=Integer.parseInt(st.nextToken());
		value=new int[n+1];
		line=new int[n+1];
		root=new int[n+1];
		ind=new int[n+1];
		e=new int[n+1];
		st=new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++)
			e[i]=Integer.parseInt(st.nextToken());
		for(int i=1;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			edge.putIfAbsent(a, new HashSet<Integer>());
			edge.putIfAbsent(b, new HashSet<Integer>());
			edge.get(a).add(b);
			edge.get(b).add(a);
		}
		int maxChild=0;
		for(int i:edge.keySet())
			if(edge.get(i).size()>maxChild) {
				center=i;
				maxChild=edge.get(i).size();
			}
		construct(center,center,0);System.out.println(Arrays.toString(value));
		while(q-->0) {
			st=new StringTokenizer(br.readLine());
			if(Integer.parseInt(st.nextToken())==1)
				update(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			else
				pw.println(dist(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		br.close();
		pw.close();
	}

}