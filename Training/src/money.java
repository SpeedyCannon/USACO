/*
ID: genel061
PROB: money
LANG: JAVA
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class money {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc=new Scanner(new File("money.in"));
		PrintWriter pw=new PrintWriter(new File("money.out"));
		int v=sc.nextInt(),n=sc.nextInt(),coins[]=new int[v];
		for(int i=0;i<v;i++)
			coins[i]=sc.nextInt();
		sc.close();
		Arrays.sort(coins);
		long[] d=new long[n+1];
		d[0]=1;
		for(int c:coins)
			for(int i=c;i<d.length;i++)
				d[i]+=d[i-c];
		pw.println(d[n]);
		pw.close();
	}

}
