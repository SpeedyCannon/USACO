import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class sleepy {//WATCH OUT FOR INTEGER LIMIT
	
	static int[] tree;
	static int[] sorted;

	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("sleepy.in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		
		int n=Integer.parseInt(br.readLine());//size
		int[] p=new int[n];//list of numbers
		StringTokenizer st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++)
			p[i]=Integer.parseInt(st.nextToken());
		//CONSIDER IF IT IS ALREADY IN ORDER, MAKE IT FLEXIBLE FOR 0
		
		int lastmax=n-1;//index of the last maximum
		while(lastmax>=0&&p[lastmax]>p[lastmax-1])lastmax--;
	//System.out.println(lastmax);
		
		tree=new int[n+1];
		int[] unsorted=new int[lastmax];
		sorted=new int[n-lastmax];
		for(int i=0;i<lastmax;i++)
			unsorted[i]=p[i];
		for(int i=lastmax;i<n;i++)
			sorted[i-lastmax]=p[i];
	//System.out.println(Arrays.toString(unsorted));
	//System.out.println(Arrays.toString(sorted));
		
		Queue<Integer> ans=new LinkedList<Integer>();
		for(int i=0;i<lastmax;i++) {
			ans.add(lastmax-i+value(unsorted[i])+binarySearch(unsorted[i])-1);
	//System.out.println(binarySearch(unsorted[i]));
			add(unsorted[i]);
		}
		
		StringBuilder sb=new StringBuilder();
		if(!ans.isEmpty()) {
			while(!ans.isEmpty())
				sb=sb.append(' ').append(ans.poll());
			sb=sb.deleteCharAt(0);
		}
		pw.println(lastmax);
		pw.println(sb.toString());
		
		br.close();
		pw.close();
	}
	
	public static int value(int i) {
		int sum=0;
		while(i!=0) {
			sum+=tree[i];
			i-=i&-i;
		}
		return sum;
	}
	
	public static void add(int i) {
		while(i<tree.length) {
			tree[i]++;
			i+=(i&-i);
		}
	}
	
	public static int binarySearch(int x) {
		if(x>sorted[sorted.length-1]){
	        return sorted.length;
	    }
	    int l=0;
	    int r=sorted.length-1;
	    while(l<r){
	        int m = l+(r-l)/2;
	        if(x>sorted[m])
	            l=m+1;
	        else r=m;
	    }
	    return l;
	}

}
