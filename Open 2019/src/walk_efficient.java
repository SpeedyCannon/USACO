/*
PROB: walk
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class walk_efficient {
	static final String fileName="walk";

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(fileName+".in")/*new InputStreamReader(System.in)*/);
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		StringTokenizer st=new StringTokenizer(br.readLine());
		long N=Integer.parseInt(st.nextToken()),K=Integer.parseInt(st.nextToken());
		br.close();
		pw.println(2019201997L-48*N-84*(K-1));
		pw.close();
	}

}
