import random


def partition(array, low, high):
    r = int((low + high) / 2)
    array[low], array[r] = array[r], array[low]
    i = low
    x = array[low]
    for j in range(low + 1, high + 1):
        if array[j] < x:
            i += 1
            if i != j:
                array[i], array[j] = array[j], array[i]

    array[low], array[i] = array[i], array[low]
    return i


def select(array, low, high, k):
    i = partition(array, low, high)
    n = i - low
    if n > k:
        return select(array, low, i - 1, k)
    elif n == k:
        return array[i]
    else:
        return select(array, i + 1, high, k - n - 1)


if __name__ == "__main__":
    length = random.randint(20, 30)
    test = list()
    for i in range(0, length):
        test.append(random.randint(0, 100))
    flag = random.randint(0, length-1)
    print("原始数据： ", end="")
    print(test)
    print("")
    print("寻找第 %d 小元素" % (flag + 1))
    result = select(test, 0, len(test) - 1, flag)
    print("第 %d 小元素为： %d" % ((flag + 1), result))
