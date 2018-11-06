def gcd2(num1, num2):
    if num2 == 0:
        return num1
    else:
        return gcd2(num2, num1 % num2)


if __name__ == "__main__":
    result = gcd2(24, 60)
    print(result)
