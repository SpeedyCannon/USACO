/*
TASK: zerosum
ID: genel061
LANG: JAVA
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class zerosum {
	
	static int n;
	static int[] op;//' '=0,'+'=1,'-'=2
	static PrintWriter pw;
	static char[] opChar= {' ','+','-'};
	
	public static void run(int len) {
		if(len==n) {
			if(calculate()==0)pw.println(tostring());
			return;
		}
		op[len]=0;
		run(len+1);
		op[len]=1;
		run(len+1);
		op[len]=2;
		run(len+1);
	}
	
	public static String tostring() {
		StringBuilder sb=new StringBuilder();
		sb=sb.append(1);
		for(int i=0;i<n;i++)
			sb=sb.append(opChar[op[i]]).append(i+2);
		return sb.toString();
	}
	
	public static int calculate() {
		int[]num=new int[n+1];
		for(int i=0;i<n+1;i++)
			num[i]=i+1;
		for(int i=0;i<n;i++)
			if(op[i]==0) {
				if(num[i]<0)num[i+1]=num[i]*10-num[i+1];
				else num[i+1]=num[i]*10+num[i+1];
				num[i]=0;
			}
			else if(op[i]==2)
				num[i+1]=-num[i+1];
		int ret=0;
		for(int i:num)
			ret+=i;
		return ret;
	}

	public static void main(String[] args) throws IOException {
		Scanner sc=new Scanner(new File("zerosum.in"));
		pw=new PrintWriter(new BufferedWriter(new FileWriter((new File("zerosum.out")))));
		n=sc.nextInt()-1;
		sc.close();
		op=new int[n];
		run(0);
		pw.close();
	}

}
