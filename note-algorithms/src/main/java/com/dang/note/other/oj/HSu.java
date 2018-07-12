package com.dang.note.other.oj;

public class HSu {
    final static int n = 5;//һ���屾�������
    static boolean book[] = {false, false, false, false, false};//δ������
    static int[][] a = {{1, 1, 0, 0, 0}, {1, 0, 0, 0, 0}, {0, 0, 1, 1, 1}, {0, 0, 1, 0, 0}, {0, 0, 0, 1, 1}};
    static int b[] = new int[n];//n���鱻˭������

    public static void main(String[] args) {
        dfs(0);
    }

    //��k���˵����
    public static void dfs(int k) {
        if (k == 5) {
            print();
            return;
        }
        //��i��������
        for (int i = 0; i < n; i++) {
            if (book[i] == false && a[k][i] == 1) {//�ɽ��
                book[i] = true;
                b[k] = i;//��k���鱻����˵�i����
                dfs(k + 1);
                book[i] = false;
                b[k] = 0;
            }
        }
    }

    private static void print() {
        for (int i = 0; i < 5; i++) {
            System.out.print(b[i] + "-");
        }
        System.out.println();
    }
}


/*
 ��������
      
    ѧУ�����ʱ����Ϣѧ������ʦ��n����Ҫ�ָ��μ���ѵ��n��ѧ�����磺A��B��C��D��E��5����Ҫ�ָ��μ���ѵ���š������������5λѧ����
    ÿ��ֻ��ѡ1������ʦ������ÿ���˽��Լ�ϲ��������д�����µı��У�Ȼ�����������д�ı��������鱾��ϣ�����һ�����������ʦ������ܵķ��䷽����
    ʹÿ��ѧ�������⡣


	A	B	C	D	E
��	1	1	0	0	0
��	1	0	0	0	0
��	0	0	1	1	1
��	0	0	1	0	0
��	0	0	0	1	1
 * */
