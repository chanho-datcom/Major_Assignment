# -*- coding: utf-8 -*-
"""
2016250027 박찬호
"""
print('2016250027 박찬호 주소록프로그램')
adrr = {'홍길동' : '010-8765-4321'}
print('*************************************************************')
print('주소록 명령프로그램')
print('성명과 전화번호로 이루어진 주소록을 관리하는 프로그램입니다.')
print('*************************************************************')
print('******')
print('주메뉴')
print('******')
print("    I (또는 i)를 입력하면, 새로운 주소를 입력합니다" )
print("    D (또는 d)를 입력하면, 새로운 주소를 입력합니다" )
print("    U (또는 u)를 입력하면, 새로운 주소를 입력합니다" )
print("    R (또는 r)를 입력하면, 새로운 주소를 입력합니다" )
print("    Q (또는 q)를 입력하면, 새로운 주소를 입력합니다" )


while True :
    cha = input("\n    키보드에 ['I', 'D', 'U', 'R', 'Q'] 중 한 글자를 입력하시오!")
    
    if cha.lower() == 'i':
        print("<<주소 삽입>>")
        print("기존 주소록에 있는 사람 명단 : ", list(adrr.keys()))
        print('삽입할 사람의 성명과 전화번호를 입력하시오')
        addname = input("성명 : ")
        phonenumber = input("전화번호(예:010-1234-4321) : ")
        adrr[addname] = phonenumber
        print("주소록 : ", list(adrr.items()))
        continue
    elif cha.lower()== 'd':
        print("<<주소 삭제>>")
        print("기존 주소록에 있는 사람 명단 : ", list(adrr.keys()))
        print('지우고자 하는 사람의 성명을 입력하시오!')
        removename = input("성명 : ")
        if removename not in adrr.keys():
            print("주소록에 없는 사람입니다. 확인해주세요.")
            continue
        del(adrr[removename])
        print("주소록 : ", list(adrr.items()))
        continue
    elif cha.lower()== 'u':
        print("<<주소 갱신>>")
        print("기존 주소록에 있는 사람 명단 : ", list(adrr.keys()))
        print('갱신할 사람의 성명과 전화번호를 입력하시오!')
        change = input("성명 : ")
        if change not in adrr.keys():
            print("주소록에 없는 사람입니다. 확인해주세요.")
            continue
        changenum = input("전화번호(예:010-1234-4321) : ")
        adrr[change] = changenum
        print("주소록 : ", list(adrr.items()))
        continue
    elif cha.lower() == 'r':
        print("주소록 : ", list(adrr.items()))
    elif cha.lower() == 'q':
        print("주소록 프로그램을 종료합니다.")
        break
    else:
        print("키보드에 ['I', 'D', 'U', 'R', 'Q'] 중 한 글자를 입력하시오!")
        print("잘못된 메뉴글자의 입력입니다.")
        
        
        
        
        
        
        
    
    