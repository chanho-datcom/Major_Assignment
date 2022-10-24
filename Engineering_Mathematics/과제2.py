# -*- coding: utf-8 -*-
"""
Created on Thu Nov 28 16:37:38 2019

@author: com
"""
import pandas as pd
users = pd.read_table('user.txt', sep = '|', index_col = 'user_id')

df = pd.DataFrame(users)

#1. 데이터의 개수를 확인하세요.
print(df.info)

#2. 데이터프레임의 앞의 5개와 뒤의 5개의 행을 확인하세요.
print(df.head())
print(df.tail())

#3. users라는 데이터프레임의 occupation과 age 열만을 포함하는 데이터프레임을 만들고,
#   이를 users_age에 할당하세요.
users = df[['occupation', 'age']]
users_age = pd.DataFrame(users)

#4. users_age의 앞의 5개의 행을 확인하세요.
print(users_age.head())

#5. 직업(occupation) 별 나이에 대한 사분위수를 이용한 이상치처리를 하세요. 
#   이때, users_age에는 Lower, Upper, Outlier 열이 추가되어야 합니다.
oc = users_age.groupby(['occupation'])

users_age['Lower'] = oc.transform(lambda x : x.quantile(.25) - 1.5 * (x.quantile(.75) - x.quantile(.25)))

users_age['Upper'] = oc.transform(lambda x : x.quantile(.75) + 1.5 * (x.quantile(.75) - x.quantile(.25)))

users_age['Outlier'] = (users_age.age < users_age.Lower) | (users_age.age > users_age.Upper)
"""
users_age['Lower'] = users_age.groupby(['occupation']).transform(lambda x : x.quantile(.25) - 1.5 * (x.quantile(.75) - x.quantile(.25)))

users_age['Upper'] = users_age.groupby(['occupation', 'Lower']).transform(lambda x : x.quantile(.75) + 1.5 * (x.quantile(.75) - x.quantile(.25)))

users_age['Outlier'] = (users_age.age < users_age.Lower) | (users_age.age > users_age.Upper)
"""

print(users_age)

#6. 이상치가 몇 개 있는지 확인하세요.
users_age_Outlier = users_age[users_age['Outlier'] == 1]
print(users_age_Outlier)

#7. 이상치의 값들을 각 직업별 중앙값(median)으로 변경하세요.
users_age_median = users_age.groupby('occupation').median()

users_age_Outlier = users_age_Outlier.reset_index()
users_age_median = users_age_median.reset_index()

for i in range(len(users_age_Outlier)) :
    for j in range(len(users_age_median)):
        if users_age_Outlier['occupation'][i] == users_age_median['occupation'][j] :
            users_age_Outlier['age'][i] = users_age_median['age'][j]

users_age = users_age.reset_index()

for i in range(len(users_age_Outlier)):
    for j in range(len(users_age)):
        if users_age_Outlier['user_id'][i] == users_age['user_id'][j]:
            users_age['age'][j] = users_age_Outlier['age'][i]

users_age = users_age.set_index(users_age['user_id'])
del users_age['user_id']
users_age['Outlier'] = False            
print(users_age)