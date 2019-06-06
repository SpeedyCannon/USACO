import java.math.BigInteger;
import java.util.*;

public class Testing {
	
	public static void main(String[] args) {
		System.out.println(Integer.lowestOneBit(4));
	}
	
	public static boolean equals(boolean[] b1,boolean[] b2) {
		for(int i=0;i<Math.min(b1.length,b2.length);i++)
			if(b1[i]!=b2[i]) {
				System.out.println(i+" "+b1[i]+" "+b2[i]);
				return false;
			}
		return true;
	}
	
	public static boolean[] O(int n) {
		boolean[] comp=new boolean[n+1];
		comp[1]=true;
		for(int i=2;i<=Math.sqrt(n);i++)
			if(!comp[i])
				for(int j=i*2;j<=n;j+=i) {
					comp[j]=true;
				}
		return comp;
	}
	
	public static boolean prime(int n) {
		int lim=(int)Math.sqrt(n);
		for(int i=2;i<=lim;i++)
			if(n%i==0)return false;
		return true;
	}

}
