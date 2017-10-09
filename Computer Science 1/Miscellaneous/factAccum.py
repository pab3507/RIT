def factA(n, a):
    if n == 0:
        return a
    else:
        return factA(n-1, n*a)

def fact(n):
    return factA(n,1)