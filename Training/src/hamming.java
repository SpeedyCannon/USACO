/*
PROB: hamming
ID: genel061
LANG: JAVA
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class hamming {
	
	static int[] ans;
	static int n,d;

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File("hamming.in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
		n=sc.nextInt();int b=1<<sc.nextInt();d=sc.nextInt();
		sc.close();
		ans=new int[n];
		for(int i=0;i<b&&n!=0;i++)
			if(works(i)) {
				n--;
				ans[n]=i;
			}
		for(int i=0;i<ans.length/2;i++) {
			int temp=ans[i];
			ans[i]=ans[ans.length-i-1];
			ans[ans.length-i-1]=temp;
		}
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<ans.length/10;i++) {
			for(int j=0;j<10;j++)
				sb=sb.append(' ').append(ans[i*10+j]);
			pw.println(sb.deleteCharAt(0));
			sb=new StringBuilder();
		}
		if(ans.length%10!=0) {
			for(int i=ans.length-(ans.length%10);i<ans.length;i++)
				sb=sb.append(' ').append(ans[i]);
			pw.println(sb.deleteCharAt(0));
		}
		pw.close();
	}
	
	public static boolean works(int i) {
		for(int idx=n;idx<ans.length;idx++)
			if(!countOnes(i^ans[idx]))return false;
		return true;
	}
	
	public static boolean countOnes(int n) {//DOUBLE CHECK IF WORKS
		int ret=0;
		while(n!=0&&ret!=d) {
			n=n&(n-1);
			ret++;
		}
		return ret==d;
	}

}
