/*
PROB: TEMPLATE
ID: genel061
LANG: JAVA
 */

import java.io.*;
import java.util.*;

public class TEMPLATE {
	static final String fileName="TEMPLATE";

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File(fileName+".in"));
		//BufferedReader br=new BufferedReader(new FileReader(fileName+".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(fileName+".out")));
		
		sc.close();
		//br.close();
		pw.close();
	}

}
