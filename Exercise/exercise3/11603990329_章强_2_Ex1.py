def get_graph(filename):
    edges = list()
    nodes = set()
    # 读取文件
    with open(filename, "r", encoding="utf-8") as file:
        lines = file.read().split("\n")

    for line in lines:
        if len(line) <= 0:
            continue
        edges.append(line.split(" "))

    for i in edges:
        for j in range(0, 2):
            nodes.add(i[j])
    # nodes = list(edges.keys())

    return nodes, edges


def prim(V, E):
    newV = list()
    newE = list()
    newV.append(V[0])
    V.remove(V[0])
    while len(newV) != len(V) and len(E) != 0:
        for i in newV:
            temp = E[0]
            for j in E:
                if j[0] == i and j[1] in V:
                    if j[2] < temp[2]:
                        temp = j
            newE.append(temp)
            E.remove(temp)
            newV.append(temp[1])
            V.remove(temp[1])

    print(newV)


if __name__ == "__main__":
    result = get_graph("files/graph.txt")
    print(result[0])
    print(result[1])
    prim(list(result[0]), result[1])
