import java.io.*;
import java.util.*;
import java.math.*;

public class poetry {
	
	static int[] scount;//count of words by syllable count
	static int[] possibilities;//separate log of possible word permutations for a [number of syllables (=index)]
	static HashMap<Integer,HashMap<Integer,Integer>>rcount;//saves word count of a syllable count by rhyme type

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("poetry.in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
		
		StringTokenizer st=new StringTokenizer(br.readLine());
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		int k=Integer.parseInt(st.nextToken());
		int[] lcount=new int[26];//line count by rhyme pattern
		scount=new int[k+1];
		possibilities=new int[k+1];
		rcount=new HashMap<Integer,HashMap<Integer,Integer>>();
		HashSet<Integer>pcount=new HashSet<Integer>();//has all of the possibilities by rhyme type
		for(int i=0;i<n;i++) {
			st=new StringTokenizer(br.readLine());
			int s=Integer.parseInt(st.nextToken());//syllable count
			int c=Integer.parseInt(st.nextToken());//rhyme type
			scount[s]++;
			rcount.putIfAbsent(c,new HashMap<Integer,Integer>());
			rcount.get(c).putIfAbsent(s,0);//DOUBLE CHECK IF THIS WORKS, if it doesn't, we need to clone the hashmap and then add the new int and then put it back to replace the old hashmap
			rcount.get(c).put(s,rcount.get(c).get(s)+1);//ALSO AS ABOVE COMMENT
		}
		for(int i=0;i<m;i++)
			lcount[br.readLine().charAt(0)-'A']++;//CAN BE VULNERABLE TO READING ERRORS LIKE '\n'
		for(int i=1;i<=k;i++) 
			syl(i);
	//System.out.println(Arrays.toString(possibilities));
		int total=0,ans=1;//switch to BigInteger if some are wrong
		for(HashMap<Integer,Integer>read:rcount.values()) {
			int temp=0;
			if(!read.isEmpty())
				for(Map.Entry<Integer,Integer>word:read.entrySet())
					temp=modAdd(temp,modMult(possibilities[k-word.getKey()],word.getValue()));
	//System.out.println(temp+" "+read);
			total=modAdd(total,temp);
			pcount.add(temp);
		}
		for(int i=0;i<26;i++)
			if(lcount[i]==1)ans=modMult(ans,total);
			else if(lcount[i]!=0) {
				int temp=0;
				if(!pcount.isEmpty())
					for(int j:pcount)
						temp=modAdd(temp,modPow(j,lcount[i]));
				ans=modMult(ans,temp);
			}
		
		pw.println(ans);
		br.close();
		pw.close();
	}
	
	public static int syl(int n) {
		int ret=0;
		for(int i=1;i<=(n-1)/2;i++)
			ret=modAdd(ret,modMult(possibilities[i],possibilities[n-i]));
		ret=modMult(ret,2);
		if(n%2==0)ret=modAdd(ret,modMult(possibilities[n/2],possibilities[n/2]));
		ret=modAdd(ret,scount[n]);
		return possibilities[n]=ret;
	}
	
	static long MOD=1000000007L;
	public static int modAdd(int n1,int n2) {
		return ((n1%(int)MOD)+(n2%(int)MOD))%(int)MOD;
	}
	
	public static int modMult(int n1,int n2) {
		long i1=n1;
		long i2=n2;
		return (int)(((i1%MOD)*(i2%MOD))%MOD);
	}
	
	public static int modPow(int n1,int n2) {
		return new BigInteger(n1+"").modPow(new BigInteger(n2+""),new BigInteger(MOD+"")).intValue();
	}

}