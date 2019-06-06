/*
PROB: concom
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class concom {
	static final String fileName="concom";
	static boolean[] visited=new boolean[101];
	static int[][] percent=new int[101][101];
	static HashMap<Integer,HashSet<Integer>>owns=new HashMap<Integer,HashSet<Integer>>();
	
	public static void find(int node) {
		if(visited[node])return;
		visited[node]=true;
		if(owns.get(node).isEmpty())return;
		for(int i=1;i<101;i++)
			if(i==node)continue;
			else if(percent[node][i]>50) {
				owns.get(node).add(i);
				find(i);
				owns.get(node).addAll(owns.get(i));
			}
		
		sum=new int[101];
		sumVisit=new boolean[101];
		add(node);
		for(int i=1;i<101;i++)
			if(sum[i]>50) {
				owns.get(node).add(i);
				find(i);
				owns.get(node).addAll(owns.get(i));
			}
	}
	
	static int[] sum;
	static boolean[] sumVisit;
	public static void add(int node) {
		if(sumVisit[node])return;
		sumVisit[node]=true;
		for(int i=1;i<101;i++)
			sum[i]+=percent[node][i];
		for(int i:owns.get(node))
			add(i);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		int n=Integer.parseInt(br.readLine().trim());
		for(int i=0;i<101;i++)
			owns.put(i,new HashSet<Integer>());
		for(int i=0;i<n;i++) {
			String[] temp=br.readLine().trim().split(" ");
			int x=Integer.parseInt(temp[0]),y=Integer.parseInt(temp[1]),p=Integer.parseInt(temp[2]);
			percent[x][y]=p;
			if(p>50)owns.get(x).add(y);
		}
		br.close();
		for(int i=1;i<101;i++) {
			owns.get(i).remove(i);
			find(i);
			//System.out.println(owns.get(i));
		}
		if(!owns.isEmpty())
			for(Map.Entry<Integer,HashSet<Integer>> i:owns.entrySet()) {
				//System.out.println(i.getKey()+" "+i.getValue());
				int[] temp=new int[i.getValue().size()];
				int ind=0;
				if(temp.length!=0)
					for(int j:i.getValue())
						temp[ind++]=j;
				Arrays.sort(temp);
				//System.out.println(Arrays.toString(temp));
				for(int j:temp)
					pw.println(i.getKey()+" "+j);
			}
		pw.close();
	}

}
