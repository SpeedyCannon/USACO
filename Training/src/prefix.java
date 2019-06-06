/*
ID: genel061
LANG: JAVA
TASK: prefix
 */
import java.io.*;
import java.util.*;

public class prefix {

	public int solve(String s, String[] primitives) {
		int len = s.length();
		boolean[] dp = new boolean[len + 1];

		for (String p : primitives) {
			int l = p.length();
			if (l >= len) continue;
			if (s.substring(0, l).equals(p)) {
				dp[l] = true;
			}
		}

		for (int k = 0; k <= len; k++) {
			if (dp[k]) {
				for (String p : primitives) {
					int l = p.length();
					if (k + l  > len) continue;
					if (s.substring(k , k + l ).equals(p)) {
						dp[k + l] = true;
					}
				}
			}
		}
		int res = 0;
		for (int k = 0; k <= len; k++) {
			if (dp[k]) {
				res = k;
			}
		}
		return res;
	}

	public static void main(String[] args) throws IOException {
		String problemName = "prefix";
		BufferedReader f = new BufferedReader(new FileReader(problemName + ".in"));

		ArrayList<String> primitives = new ArrayList<String>();
		String line = f.readLine();
		while (!line.equals(".")) {
			StringTokenizer st = new StringTokenizer(line);
			while (st.hasMoreTokens()) {
				primitives.add(st.nextToken());
			}
			line = f.readLine();
		}
		StringBuilder sb = new StringBuilder();
		line = f.readLine();
		while (line != null) {
			sb.append(line);
			line = f.readLine();
		}

		int res = (new prefix()).solve(sb.toString(), primitives.toArray(new String[0]));

		// output Span
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
		out.println(res);
		out.close(); // close the output file
		System.exit(0); // don't omit this!
	}

}