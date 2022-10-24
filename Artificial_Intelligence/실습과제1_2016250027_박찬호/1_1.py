# To support both python 2 and python3
from __future__ import division, print_function, unicode_literals

#일반적인 imports
import numpy as np #데이터구조(배열 등)을 쉽게 처리하는 라이브러리
from sklearn import linear_model #sklearn = 머신러닝 분석 라이브러리
import matplotlib.pyplot as plt #정적, 애니메이션 및 대화형 시각화를 위한 라이브러리
import pandas as pd #데이터구조및데이터분석도구를 제공하는 라이브러리

oecd_bli = pd.read_csv("oecd_bli_2015.csv", thousands=',')#.csv파일을 읽어오고 천단위 구분기호 콤마를 없앤다.
oecd_bli = oecd_bli[oecd_bli["INEQUALITY"]=="TOT"]#INEQUALITY 속성안에 value tot인 것을 가져다 쓰겠다.
oecd_bli = oecd_bli.pivot(index="Country", columns="Indicator", values="Value")
#pivot 데이터재구조화 행과열을 재구성한다.
print("2016250027 박찬호\n")
print("oecd_bli.head(2):\n")
print(oecd_bli.head(2))
print("\n\noecd_bli['Life satisfaction'].head():\n")
print(oecd_bli["Life satisfaction"].head())

gdp_per_capita = pd.read_csv("gdp_per_capita.csv", thousands=',', delimiter='\t',
                             encoding='latin1', na_values="n/a")
gdp_per_capita.rename(columns={"2015": "GDP per capita"}, inplace=True)
gdp_per_capita.set_index("Country", inplace=True)
print("\n\ngdp_per_capita.head(2):\n")
print(gdp_per_capita.head(2))

full_country_stats = pd.merge(left=oecd_bli, right=gdp_per_capita, left_index=True, right_index=True)

keep_indices = list(set(range(36)))
sample_data = full_country_stats[["GDP per capita", 'Life satisfaction']].iloc[keep_indices]

def prepare_country_stats(oecd_bli, gdp_per_capita):
    return sample_data

# prepare the data
country_stats = prepare_country_stats(oecd_bli, gdp_per_capita)
X = np.c_[country_stats["GDP per capita"]]
y = np.c_[country_stats['Life satisfaction']]

#visualize the data
country_stats.plot(kind='scatter', x="GDP per capita", y='Life satisfaction')
plt.title("2016250027 ParkChanHo")
plt.show()

#select a linear model
model = linear_model.LinearRegression()
#train the model
model.fit(X, y)

#make a prediction for cyprus
print("\n\n# Make a prediction for Cyprus\n")
X_new = np.array([[22587.0]]) #cyprus' gdp per capita
print(model.predict(X_new))