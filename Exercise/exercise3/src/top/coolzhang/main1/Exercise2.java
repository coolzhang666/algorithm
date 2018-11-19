package top.coolzhang.main1;

public class Exercise2 {

    private class ENode {
        int ivex;
        int weight;
        ENode nextEdge;
    }

    private class VNode {
        char data;
        ENode firstEdge;
    };

    private int mEdgNum;
    private VNode[] mVexs;

    public Exercise2(char[] vexs, EData[] edges) {

        int vlen = vexs.length;
        int elen = edges.length;

        mVexs = new VNode[vlen];
        for (int i = 0; i < mVexs.length; i++) {
            mVexs[i] = new VNode();
            mVexs[i].data = vexs[i];
            mVexs[i].firstEdge = null;
        }

        mEdgNum = elen;
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

    public void kruskal() {
        int index = 0;
        int[] vends = new int[mEdgNum];
        EData[] rets = new EData[mEdgNum];
        EData[] edges;

        edges = getEdges();
        sortEdges(edges, mEdgNum);

        for (int i=0; i<mEdgNum; i++) {
            int p1 = getPosition(edges[i].start);
            int p2 = getPosition(edges[i].end);

            int m = getEnd(vends, p1);
            int n = getEnd(vends, p2);
            if (m != n) {
                vends[m] = n;
                rets[index++] = edges[i];
            }
        }

        int length = 0;
        for (int i = 0; i < index; i++)
            length += rets[i].weight;
        System.out.printf("Kruskal=%d: ", length);
        for (int i = 0; i < index; i++)
            System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
        System.out.printf("\n");
    }

    private EData[] getEdges() {
        int index=0;
        EData[] edges;

        edges = new EData[mEdgNum];
        for (int i=0; i < mVexs.length; i++) {

            ENode node = mVexs[i].firstEdge;
            while (node != null) {
                if (node.ivex > i) {
                    edges[index++] = new EData(mVexs[i].data, mVexs[node.ivex].data, node.weight);
                }
                node = node.nextEdge;
            }
        }

        return edges;
    }

    private void sortEdges(EData[] edges, int elen) {

        for (int i=0; i<elen; i++) {
            for (int j=i+1; j<elen; j++) {

                if (edges[i].weight > edges[j].weight) {
                    // 交换"边i"和"边j"
                    EData tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

    private int getEnd(int[] vends, int i) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
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
//                EData[] edges = {
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
//                new EData('A', 'D', 6),
//                new EData('B', 'C', 1),
//                new EData('B', 'D',  3),
//                new EData('C', 'D',  4),
//                new EData('C', 'E',  6),
//                new EData('D', 'E',  2),
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
        Exercise2 pG;
        pG = new Exercise2(vexs, edges);
        pG.kruskal();
    }
}