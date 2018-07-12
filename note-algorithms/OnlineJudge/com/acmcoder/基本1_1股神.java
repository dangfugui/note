package com.acmcoder;

import java.util.Scanner;

public class 基本1_1股神 {
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		while(scan.hasNext()){
			int n=scan.nextInt();
			System.out.println(getAns(n));
		}
		
	}

	private static int getAns(int n) {
		int up=1;
		int ans=1;
		int d;
		for(d=2;d<=n;){
			if((d+up)<=(n+1)){//n+1明天  可以升完 正好是明天
				ans+=up;
				d+=up;
				up++;
			}else{
				ans+=(n-d+1);
				break;
			}
			if(d<=n){
				ans--;
				d++;
			}
		}
		return ans;
	}
}
///http://exercise.acmcoder.com/online/online_judge_ques?ques_id=1664&konwledgeId=134
/*
 题目描述
有股神吗？
有，小赛就是！
经过严密的计算，小赛买了一支股票，他知道从他买股票的那天开始，股票会有以下变化：第一天不变，以后涨一天，跌一天，涨两天，跌一天，涨三天，跌一天...依此类推。
为方便计算，假设每次涨和跌皆为1，股票初始单价也为1，请计算买股票的第n天每股股票值多少钱？
输入包括多组数据；
每行输入一个n，1<=n<=10^9 。
输出
请输出他每股股票多少钱，对于每组数据，输出一行。
 */