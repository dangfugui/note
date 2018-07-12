package rqnoj.cn;


public class db_采药PID15 {
	public static void main(String[] args) {
		int t,n;
		java.util.Scanner scan=new java.util.Scanner(System.in);
		t=scan.nextInt();
		n=scan.nextInt();
		int db[]=new int[t+1];
		for(int i=0;i<n;i++){
			int time,value;
			time=scan.nextInt();
			value=scan.nextInt();
			for(int b=t;b>0;b--){
				if(b>=time){
					db[b]=Math.max(db[b-time]+value, db[b]);
				}
			}
		}
		System.out.println(db[t]);
	}
}
