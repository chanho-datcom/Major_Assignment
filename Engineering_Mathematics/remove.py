list1 = ['a', 'a', 'b', 'c', 'd', 'b']

def remove_elt(list1):
    result = []
    for item in list1:
        if item not in result:
            result.append(item)
    return result
print(remove_elt(list1))
