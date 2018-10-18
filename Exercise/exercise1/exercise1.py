def gcd1(num1, num2):
    if num1 > 0 or num2 > 0:
        el = min(num1, num2)
        for i in range(el, 0, -1):
            if num1 % i == 0 and num2 % i == 0:
                num1 = num1 / i
                num2 = num2 / i
                return i
    else:
        print("输入数据有误")


if __name__ == "__main__":
    result = gcd1(24, 60)
    print(result)
