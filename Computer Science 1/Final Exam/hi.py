def myFun(s,i):
    if i < 0:
        return ''
    else:
        return s[i:] + myFun(s,i-1)
print(myFun('foobar',3))