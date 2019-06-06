/*
PROB: paintbarn
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class paintbarn {
	static final String fileName="paintbarn";

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File(fileName+".in"));
		//BufferedReader br=new BufferedReader(new FileReader(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		
		sc.close();
		//br.close();
		pw.close();
	}

}
