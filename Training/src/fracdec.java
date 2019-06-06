/*
ID: genel061
LANG: JAVA
TASK: fracdec
 */
import java.io.*;
import java.util.*;

public class fracdec {

	public String solve(int num, int denom) {
		int gcd = gcd(num, denom);
		num /= gcd;
		denom /= gcd;

		int intPart = num / denom;
		num -= (denom * intPart);
		if (num == 0) return String.format("%d.0", intPart);
		
		HashMap<Integer, Integer> remainders = new HashMap<Integer, Integer>();
		StringBuilder sbFrac = new StringBuilder();

		String sFrac = "";
		int idx = 0;
		while (true) {
			remainders.put(num, idx++);
			num *= 10;
			int m = num / denom;
			sbFrac.append(m);
			num = num % denom;
			
			if (remainders.containsKey(num)) {
				sFrac = sbFrac.toString();
				int d = remainders.get(num);
				sFrac = sFrac.substring(0, d) + (num > 0 ? "(" + sFrac.substring(d) + ")" : "");
				break;
			}
		}

		StringBuilder res = new StringBuilder();
		res.append(intPart);
		res.append(".");
		res.append(sFrac);

		return res.toString();
	}

	static int gcd(int x, int y) {
		if (y == 0) return x;
		if (x < y) return gcd(y, x);
		return gcd(y, x % y);
	}

	public static void main(String[] args) throws IOException {
		String problemName = "fracdec";
		BufferedReader f = new BufferedReader(new FileReader(problemName + ".in"));
		StringTokenizer st = new StringTokenizer(f.readLine());
		int num = Integer.parseInt(st.nextToken());
		int denom = Integer.parseInt(st.nextToken());

		String res = (new fracdec()).solve(num, denom);

		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(problemName + ".out")));
		
		int L = 76;
		while (res.length() > L) {
			out.println(res.substring(0, L));
			res = res.substring(L);
		}
		out.println(res);
		out.close();
		System.exit(0);
	}

}