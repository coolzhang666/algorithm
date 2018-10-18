def gcd(num1, num2, flag=0):
    if num2 == 0:
        return 0
    else:
        return gcd(num2, num1 % num2) + 1


if __name__ == "__main__":
    result = set()
    for m in range(1, 20):
        for n in range(0, 11):
            result.add(gcd(m, n))

    print("最少次数为：%d" % min(result))
    print("最大次数为：%d" % max(result))
