package top.coolzhang.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Exercise4 {
    public static void main(String[] args) throws Exception{
        String filename = "src/top/coolzhang/files/graph.txt";
        Graph graph = new Graph(filename);
        graph.dijkstra("A");
    }
}

class Graph {
    List<Node> nodes = new ArrayList<>(); // 节点列表
    List<Edge> edges = new ArrayList<>(); // 边列表
    List<Node> S = new ArrayList<>(); // 已计算出最短路径的节点集合
    List<Node> U = new ArrayList<>(); // 未计算出最短路径的节点集合


    public Graph(String fileName) throws Exception{
        File file = new File(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String line = "";
        String[] text = null;

        // 读取顶点信息
        line = reader.readLine();
        text = line.split(" ");
        for (int i = 0; i < text.length; i++) {
            nodes.add(new Node(text[i]));
        }

        // 读取边的信息
        while ((line = reader.readLine()) != null) {
            text = line.split(" ");
            String nodeName1 = text[0];
            String nodeName2 = text[1];
            int weight = Integer.parseInt(text[2]);
            Node node1 = getNodeByName(nodeName1, nodes);
            Node node2 = getNodeByName(nodeName2, nodes);
            Edge edge = new Edge(node1, node2, weight);
            edges.add(edge);
            if (node1.getFirstEdge() == null) {
                node1.setFirstEdge(edge);
            }else {
                Edge p = node1.getFirstEdge();
                while (p.getNextEdge() != null) {
                    p = p.getNextEdge();
                }
                p.setNextEdge(edge);
            }
        }
    }


    public void dijkstra(String nodeName) {

        for (Node node: nodes) {
            U.add((Node) node.clone());
        }
        Node startNode = getNodeByName(nodeName, U);
        if (startNode != null) {
            startNode.setDistance(0);
            do {
                // 将距离最小的点加入S集合
                Node temp = getMinDistanceNode(U);
                S.add(temp);
                U.remove(temp);

                // 更新为计算出最短路径的节点集合中每个节点的距离
                for (Node node1 : S) {
                    for (Node node2 : U) {
                        int dis = getDistance(node1, node2);
                        if (dis != Integer.MAX_VALUE) {
                            node2.setDistance(node1.getDistance() + dis);
                        }else {
                            node2.setDistance(dis);
                        }
                    }
                }

            }while (U.size() != 0);

            for (Node node : S) {
                System.out.println("节点" + node.getName() + "最短路径：" + node.getDistance());
            }

        }else {
            System.out.println("输入数据有误");
        }

    }


    /**
     *
     * 根据节点名字得到节点对象
     * @param name 节点名字
     * @return 返回节点对象（该名字对应的对象存在）或null（节点不存在）
     */
    public Node getNodeByName(String name, List<Node> list) {
        for (Node node : list) {
            if (name.equals(node.getName())) {
                return node;
            }
        }
        return null;
    }

    /**
     *
     * 得到两个节点之间的距离
     * @param node1 节点一
     * @param node2 节点二
     * @return 返回节点一和节点二的距离
     */
    public int getDistance(Node node1, Node node2) {
        for (Edge edge : edges) {
            Node frontNode = edge.getFrontNode();
            Node afterNode = edge.getAfterNode();
            if (frontNode.equals(node1) && afterNode.equals(node2)) {
                edges.remove(edge);
                return edge.getWeight();
            }
//            else if (frontNode.equals(node2) && afterNode.equals(node1)) {
//                return edge.getWeight();
//            }
        }
        return Integer.MAX_VALUE;
    }

    /**
     *
     * 获取集合中距离最小的节点
     * @param list 一个节点集合
     * @return 距离最小的节点
     */
    public Node getMinDistanceNode(List<Node> list) {
        if (list.size() > 0) {
            Node flag = list.get(0);
            for (Node node : list) {
                if (node.getDistance() < flag.getDistance()) {
                    flag = node;
                }
            }
            return flag;

        }else {
            return null;
        }
    }
}


/**
 * 节点类，保存图中的节点
 */
class Node implements Cloneable{
    private String name;
    private Edge firstEdge;
    private int distance;

    public Node(String name) {
        this.name = name;
        this.firstEdge = null;
        this.distance = Integer.MAX_VALUE;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Edge getFirstEdge() {
        return firstEdge;
    }

    public void setFirstEdge(Edge firstEdge) {
        this.firstEdge = firstEdge;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        if (distance < this.distance) {
            this.distance = distance;
        }
    }

    @Override
    public boolean equals(Object obj) {
        obj = (Node)obj;
        if (this.name.equals(((Node) obj).getName())) {
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Object clone() {
        Node o = null;
        try {
            o = (Node)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }
}


/**
 * 边类，保存图中的边的信息
 */
class Edge {
    private Node frontNode;
    private Node afterNode;
    private int weight;
    private Edge nextEdge;

    public Edge(Node frontNode, Node afterNode, int weight) {
        this.frontNode = frontNode;
        this.afterNode = afterNode;
        this.weight = weight;
        this.nextEdge = null;
    }

    public Node getFrontNode() {
        return frontNode;
    }

    public void setFrontNode(Node frontNode) {
        this.frontNode = frontNode;
    }

    public Node getAfterNode() {
        return afterNode;
    }

    public void setAfterNode(Node afterNode) {
        this.afterNode = afterNode;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Edge getNextEdge() {
        return nextEdge;
    }

    public void setNextEdge(Edge nextEdge) {
        this.nextEdge = nextEdge;
    }
}
