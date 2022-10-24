# -*- coding: utf-8 -*-
"""
2016250027 박찬호
"""
print('2016250027 박찬호 사칙연산프로그램')
print('*********************************************************************')
print('사칙연산 프로그램')
print('3개(정수, 사칙연산자, 정수)를 입력하여 연산결과를 출력하는 프로그램')
print('*********************************************************************')


while True :
    print('**************')
    print('입력')
    print('**************')
    
    fnum = input("첫번째 정수 : ")
    op = input("사칙연산자(+, -, *, /) : ")
    snum = input("두번째 정수 : ")
    
    if '.' in fnum or fnum.isdigit()==0:
        print("첫번째 숫자가 정수가 아닙니다")
    if op not in '+-*/':
        print("사칙연산자가 아닌 글자가 입력되었습니다.")
    if '.' in snum or snum.isdigit()==0:
        print("두번째 숫자가 정수가 아닙니다")
    if snum == '0' and op == '/':
        print("0으로 나눗셈은 안됩니다")
        continue
    cent = str(fnum)+ op +str(snum)
    if '.' in fnum or fnum.isdigit()==0 or op not in '+-*/' or '.' in snum or \
    snum.isdigit()==0 or '//' in cent or '**' in cent:
        continue
    
    else:
        print('*****\n 출력\n******')
        result = float(eval(cent))
        print("연산 결과 : ", result)
        break
