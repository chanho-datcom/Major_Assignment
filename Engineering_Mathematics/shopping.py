# 2.쇼핑리스트(첨부파일)을 입력받아, 총 가격을 리턴하는 함수 shopping_total_sum(shopping_file)를 구현하라.#

def shopping_total_sum(shopping_file):
    
    f = open(shopping_file, 'r')
    sum =0
    buy_list= f.read()
    buy_list = buy_list.split('\n')
    f.close()
    print("EX) tomato : 1, cola : 2")
    item = input("Bread 3000원, Tomato 2000원, Cola 1500원 중 구매하실 물품을 입력하세요: ")
    try:
        ip = item.split(",")
        i= 0
        if 'bread' in item.lower():
            sum += (int(buy_list[0].split()[2])) * (int((ip[i].split()[2])))
            i += 1
        if 'tomato' in item.lower():
            sum += (int(buy_list[1].split()[2])) * (int((ip[i].split()[2])))
            i += 1
        if 'cola' in item.lower():
            sum += int(buy_list[2].split()[2]) * (int((ip[i].split()[2])))
        return sum
    except ValueError:
        print("값을 잘못 입력하였습니다. 예시를 참고해 주세요.")
print(shopping_total_sum("shopping_list.txt"))
