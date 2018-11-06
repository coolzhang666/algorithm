import random
from time import time
from copy import deepcopy


def quick_sort(list, low, high):
    left = low
    right = high
    k = list[low]

    while left < right:
        while list[left] <= k:
            left += 1
            if left == high:
                break
        while list[right] > k:
            right -= 1
            if right == low:
                break
        if left < right:
            list[left], list[right] = list[right], list[left]

    list[low] = list[right]
    list[right] = k
    return right


def quickSort(list, low, high):
    if low < high:
        s = quick_sort(list, low, high)
        quickSort(list, low, s - 1)
        quickSort(list, s + 1, high)


def insertSort(array):
    length = len(array)
    for i in range(1, length):
        x = array[i]
        for j in range(i, -1, -1):
            if x < array[j - 1]:
                array[j] = array[j - 1]
            else:
                break
        array[j] = x


if __name__ == "__main__":
    len_num = 10000
    test_list = list()
    for i in range(0, len_num):
        test_list.append(random.randint(-9999999, 9999999))
    test_list1 = deepcopy(test_list)

    print("数据量： %d" % len_num)
    print("原始数据：", end="")
    print(test_list)
    print("")
    start = time()
    quickSort(test_list, 0, len(test_list) - 1)
    end = time()
    time1 = end - start
    print("快速排序结果：")
    print(test_list)
    print("快速排序用时： %.5fs" % time1)

    print("")
    start = time()
    insertSort(test_list1)
    end = time()
    time2 = end - start
    print("插入排序结果：")
    print(test_list1)
    print("插入排序用时： %.5fs" % time2)
    print("")
    print("插入排序运行时间是快速排序的 %.5f 倍" % (time2 / time1))
