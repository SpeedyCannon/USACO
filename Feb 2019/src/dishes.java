/*
PROB: dishes
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class dishes {
	static final String fileName="dishes";

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		int n=Integer.parseInt(br.readLine());
		int ans=0;
		TreeMap<Integer,Integer>stacks=new TreeMap<Integer,Integer>();
		stacks.put(0,0);
		for(;ans<n;ans++) {
			int plate=Integer.parseInt(br.readLine());
			Integer potStack=stacks.ceilingKey(plate);
			if(potStack==null)
				stacks.put(plate,plate);
			else if(stacks.get(potStack)>plate)
				stacks.put(potStack,plate);
			else break;
		}
		pw.println(ans);
		br.close();
		pw.close();
	}

}
