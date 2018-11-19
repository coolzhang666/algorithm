package top.coolzhang.main;

public class Exercise1 {
    private static int INF = Integer.MAX_VALUE;

    private class ENode {
        int ivex;       // 该边所指向的顶点的位置
        int weight;     // 该边的权
        ENode nextEdge; // 指向下一条弧的指针
    }

    private class VNode {
        char data;          // 顶点信息
        ENode firstEdge;    // 指向第一条依附该顶点的弧
    };

    private VNode[] mVexs;  // 顶点数组

    public Exercise1(char[] vexs, EData[] edges) {

        int vlen = vexs.length;
        int elen = edges.length;

        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode();
            mVexs[i].data = vexs[i];
            mVexs[i].firstEdge = null;
        }

        for (int i = 0; i < elen; i++) {
            char c1 = edges[i].start;
            char c2 = edges[i].end;
            int weight = edges[i].weight;

            int p1 = getPosition(c1);
            int p2 = getPosition(c2);
            ENode node1 = new ENode();
            node1.ivex = p2;
            node1.weight = weight;
            if(mVexs[p1].firstEdge == null)
                mVexs[p1].firstEdge = node1;
            else
                linkLast(mVexs[p1].firstEdge, node1);
            ENode node2 = new ENode();
            node2.ivex = p1;
            node2.weight = weight;
            if(mVexs[p2].firstEdge == null)
                mVexs[p2].firstEdge = node2;
            else
                linkLast(mVexs[p2].firstEdge, node2);
        }
    }

    private void linkLast(ENode list, ENode node) {
        ENode p = list;

        while(p.nextEdge!=null)
            p = p.nextEdge;
        p.nextEdge = node;
    }

    private int getPosition(char ch) {
        for(int i=0; i<mVexs.length; i++)
            if(mVexs[i].data==ch)
                return i;
        return -1;
    }

    private int getWeight(int start, int end) {

        if (start==end)
            return 0;

        ENode node = mVexs[start].firstEdge;
        while (node!=null) {
            if (end==node.ivex)
                return node.weight;
            node = node.nextEdge;
        }

        return INF;
    }

    public void prim(int start) {
        int min,i,j,k,m,n,tmp,sum;
        int num = mVexs.length;
        int index=0;                   // prim最小树的索引，即prims数组的索引
        char[] prims = new char[num];  // prim最小树的结果数组
        int[] weights = new int[num];  // 顶点间边的权值

        prims[index++] = mVexs[start].data;

        for (i = 0; i < num; i++ )
            weights[i] = getWeight(start, i);

        for (i = 0; i < num; i++) {
            if(start == i)
                continue;

            j = 0;
            k = 0;
            min = INF;
            while (j < num) {
                if (weights[j] != 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }
                j++;
            }

            prims[index++] = mVexs[k].data;
            weights[k] = 0;
            for (j = 0 ; j < num; j++) {
                tmp = getWeight(k, j);
                if (weights[j] != 0 && tmp < weights[j])
                    weights[j] = tmp;
            }
        }

        // 计算最小生成树的权值
        sum = 0;
        for (i = 1; i < index; i++) {
            min = INF;
            // 获取prims[i]在矩阵表中的位置
            n = getPosition(prims[i]);
            // 在vexs[0...i]中，找出到j的权值最小的顶点。
            for (j = 0; j < i; j++) {
                m = getPosition(prims[j]);
                tmp = getWeight(m, n);
                if (tmp < min)
                    min = tmp;
            }
            sum += min;
        }
        // 打印最小生成树
        System.out.printf("PRIM(%c)=%d: ", mVexs[start].data, sum);
        for (i = 0; i < index; i++)
            System.out.printf("%c ", prims[i]);
        System.out.printf("\n");
    }


    private static class EData {
        char start; // 边的起点
        char end;   // 边的终点
        int weight; // 边的权重

        public EData(char start, char end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    };

    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L'};
//        EData[] edges = {
//                // 起点 终点 权
//                new EData('A', 'B', 3),
//                new EData('A', 'F', 5),
//                new EData('A', 'E', 6),
//                new EData('B', 'C', 1),
//                new EData('B', 'F',  4),
//                new EData('C', 'D',  6),
//                new EData('C', 'F',  4),
//                new EData('D', 'F',  5),
//                new EData('D', 'E',  8),
//                new EData('E', 'F',  2),
//        };
//        EData[] edges = {
//                // 起点 终点 权
//                new EData('A', 'B', 5),
//                new EData('A', 'C', 7),
//                new EData('A', 'E', 2),
//                new EData('B', 'E', 3),
//                new EData('B', 'D',  6),
//                new EData('C', 'D',  4),
//                new EData('C', 'E',  4),
//                new EData('D', 'E',  5),
//        };
        EData[] edges = {
                // 起点 终点 权
                new EData('A', 'B', 3),
                new EData('A', 'C', 5),
                new EData('A', 'D', 4),
                new EData('B', 'E', 3),
                new EData('B', 'F',  6),
                new EData('C', 'D',  2),
                new EData('C', 'G',  4),
                new EData('D', 'E',  1),
                new EData('D', 'H',  5),
                new EData('E', 'F',  2),
                new EData('E', 'I',  4),
                new EData('F', 'J',  5),
                new EData('G', 'H',  3),
                new EData('G', 'K',  6),
                new EData('H', 'I',  6),
                new EData('H', 'K',  7),
                new EData('I', 'J',  3),
                new EData('I', 'L',  5),
                new EData('J', 'L',  9),
                new EData('K', 'L',  8),
        };
        Exercise1 pG;

        pG = new Exercise1(vexs, edges);

        pG.prim(0);   // prim算法生成最小生成树
    }
}