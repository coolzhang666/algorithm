import random


def mergesort(demo):
    # 将一个列表分割成若干个长度为1的列表
    if len(demo) <= 1:
        return demo
    flag = int(len(demo) / 2)
    left = mergesort(demo[:flag])
    right = mergesort(demo[flag:])
    return merge(left, right)


def merge(left, right):
    # 将连个长度为1的两个列表按从小到大的顺序排序后合并为一个列表
    i, j = 0, 0
    result = []
    while i < len(left) and j < len(right):
        if left[i] > right[j]:
            result.append(right[j])
            j += 1
        else:
            result.append(left[i])
            i += 1
    result += left[i:]
    result += right[j:]
    return result


if __name__ == "__main__":
    len_num = random.randint(2, 50)
    test_list = list()
    for i in range(0, len_num):
        test_list.append(random.randint(-1000, 1000))

    print("原始数据：")
    print(test_list)

    result = mergesort(test_list)
    print("排序后：")
    print(result)
