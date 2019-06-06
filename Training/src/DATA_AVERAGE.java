import java.util.Scanner;
import java.util.StringTokenizer;

public class DATA_AVERAGE {
	
	static final int readLines=7;

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		double time=0;//secs
		int memory=0;//KB
		for(int i=0;i<readLines;i++) {
			String read=sc.nextLine();
			read=read.substring(read.indexOf('[')+1,read.lastIndexOf(']'));
			StringTokenizer st=new StringTokenizer(read);
			time+=Double.parseDouble(st.nextToken());
			st.nextToken();
			memory+=Integer.parseInt(st.nextToken());
		}
		System.out.printf("Average time: %.3f secs\n",time/readLines);
		System.out.printf("Average memory: %d KB\n",memory/readLines);
	}

}
