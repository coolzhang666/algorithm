def gcd(m, n):
    while m != n:
        if m > n:
            m -= n
        else:
            n -= m
    return m


if __name__ == "__main__":
    print(gcd(45, 36))
