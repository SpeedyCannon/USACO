import java.io.*;
import java.util.*;

public class Format {

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader(".in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(".out")));
		
		br.close();
		pw.close();
	}

}
