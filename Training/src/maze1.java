/*
PROB: maze1
ID: genel061
LANG: JAVA
 */
import java.io.*;

public class maze1 {
	static boolean[][][] board;
	static int time[][],
	change[][]= {{-1,0},{0,1},{1,0},{0,-1}},w,h;

	static boolean block(int r,int c,int d) {
		return r+change[d][0]<0||r+change[d][0]>=h||c+change[d][1]<0||c+change[d][1]>=w||board[r][c][d];
	}
	
	static void flood_fill(int r,int c,int len){
		if(time[r][c]<=len&&time[r][c]!=0)return;
		time[r][c]=len;
		for(int d=0;d<4;d++)
			if(!block(r,c,d))
				flood_fill(r+change[d][0],c+change[d][1],len+1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new FileReader("maze1.in"));
		PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
		String[] firstLine=br.readLine().split(" ");
		w=Integer.parseInt(firstLine[0]);
		h=Integer.parseInt(firstLine[1]);
		board=new boolean[h][w][4];
		time=new int[h][w];
		for(int i=0;i<=h*2;i++){
			char[]read=br.readLine().toCharArray();
	    	if(i%2==1){//odd
	    		board[i/2][0][3]=(read[0]=='|');
	    		board[i/2][w-1][1]=(read[w*2]=='|');
	    		for(int j=1;j<w;j++)
	    			board[i/2][j][3]=board[i/2][j-1][1]=(read[j*2]=='|');
			}
			else {
				for(int j=0;j<w;j++){//j*2+1
					if(i!=0)board[i/2-1][j][2]=(read[j*2+1]=='-');
					if(i!=h*2)board[i/2][j][0]=(read[j*2+1]=='-');
				}
			}
		}
		br.close();
		int coord[][]=new int[2][2];
		int coordCnt=0;
	    for(int i=0;i<w;i++){
	    	if(coordCnt>=2)break;
	    	if(!board[0][i][0]){//up
	    		coord[coordCnt][0]=0;
	    		coord[coordCnt++][1]=i;
			}
	    	if(coordCnt>=2)break;
			if(!board[h-1][i][2]){//down
				coord[coordCnt][0]=h-1;
	    		coord[coordCnt++][1]=i;
			}
		}
		for(int i=0;i<h;i++){
	    	if(coordCnt>=2)break;
			if(!board[i][w-1][1]){//right
	    		coord[coordCnt][0]=i;
	    		coord[coordCnt++][1]=w-1;
			}
	    	if(coordCnt>=2)break;
			if(!board[i][0][3]){//left
				coord[coordCnt][0]=i;
	    		coord[coordCnt++][1]=0;
			}
		}
		flood_fill(coord[0][0],coord[0][1],1);
	    flood_fill(coord[1][0],coord[1][1],1);
	    int max=0;
	    for(int i=0;i<h;i++)
	    	for(int j=0;j<w;j++)
	    		if(max<time[i][j])max=time[i][j];
	    pw.println(max);
	    pw.close();
	}

}
