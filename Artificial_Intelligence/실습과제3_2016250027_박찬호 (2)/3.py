# Python ≥3.5 is required
import sys #파이썬 인터프리터가 제공하는 변수와 함수를 직접 제어할수 있게 해주는 모듈
assert sys.version_info >= (3, 5) #디버깅 보조

# Scikit-Learn ≥0.20 is required
import sklearn #머신러닝 모듈
assert sklearn.__version__ >= "0.20" # 사이킷런 버전 확인

# Common imports
import numpy as np # 선형대수 라이브러리
import os # 운영체제 제어 모듈

# To plot pretty figures
import matplotlib as mpl #데이터 시각화 모듈
import matplotlib.pyplot as plt #그래프 그리기 모듈
mpl.rc('axes', labelsize=14) # .rc 폰트설정방법
mpl.rc('xtick', labelsize=12)
mpl.rc('ytick', labelsize=12)

# Where to save the figures
PROJECT_ROOT_DIR = "." # 프로젝트 루트 폴더
CHAPTER_ID = "end_to_end_project" # 만들 디렉토리명
IMAGES_PATH = os.path.join(PROJECT_ROOT_DIR, "images", CHAPTER_ID) # 저장경로
os.makedirs(IMAGES_PATH, exist_ok=True) # 디렉토리 설치

def save_fig(fig_id, tight_layout=True, fig_extension="png", resolution=300): # 결과 저장 함수
    path = os.path.join(IMAGES_PATH, fig_id + "." + fig_extension) # 저장할 경로
    print("Saving figure", fig_id)
    if tight_layout:
        plt.tight_layout() # 그래프 레이아웃 자동 설정
    plt.savefig(path, format=fig_extension, dpi=resolution) # 그래프 저장

# Ignore useless warnings (see SciPy issue #5998) / 쓸모없는 경고 무시
import warnings
warnings.filterwarnings(action="ignore", message="^internal gelsd")

import os
import tarfile # tar 아카이브 읽고 쓰기
import urllib # url 처리모듈

DOWNLOAD_ROOT = "https://raw.githubusercontent.com/ageron/handson-ml2/master/" # 데이터 받아올 경로
HOUSING_PATH = os.path.join("datasets", "housing")  # 하우징 경로 설정
HOUSING_URL = DOWNLOAD_ROOT + "datasets/housing/housing.tgz" #하우징 url 경로 설정

# guthun에서 받아온 파일 압축풀기
def fetch_housing_data(housing_url=HOUSING_URL, housing_path=HOUSING_PATH):
    if not os.path.isdir(housing_path): # 하우징패스에 폴더가 없으면
        os.makedirs(housing_path) # 디렉토리 만들기
    tgz_path = os.path.join(housing_path, "housing.tgz") # tgz 경로 설정
    urllib.request.urlretrieve(housing_url, tgz_path) # url.request 모듈은 url을 여는데 도움이 되는 모듈
    housing_tgz = tarfile.open(tgz_path) # 경로에 있는 tgz파일 열기
    housing_tgz.extractall(path=housing_path) # extractall : 압축파일을 해당 경로에 모두 압축해제
    housing_tgz.close() # 압축파일 닫기

fetch_housing_data()

import pandas as pd # 데이터 조작 및 분석을 위한 라이브러리

def load_housing_data(housing_path=HOUSING_PATH):
    csv_path = os.path.join(housing_path, "housing.csv") #하우징 경로에 .csv파일 위치
    return pd.read_csv(csv_path) # .csv 파일 읽기

housing = load_housing_data() # 하우징csv파일 읽기

print("2016250027 박찬호")

# Scikit-Learn 함수 사용
from sklearn.model_selection import train_test_split # 배열또는 행렬을 임의 학습 및 테스트 하위 집합으로 분할
train_set, test_set = train_test_split(housing, test_size=0.2, random_state=42) # test_size 데이터 세트 비율
# random_state 데이터에 적용되는 셔플 링 제어 *train_test_split() : 배열을 테스트에 0.2 트레인에 0.8 셔플링 42
print("\n\ntest_set.head():\n", test_set.head())

# 소득 범주 수를 제한하려면 1.5로 나눕니다.
housing["income_cat"] = np.ceil(housing["median_income"] / 1.5) # 요소별로 입력의 한도를 반환합니다.
# 5 이상은 5로 표시
housing["income_cat"].where(housing["income_cat"] < 5, 5.0, inplace=True)

from sklearn.model_selection import StratifiedShuffleSplit # 학습/테스트 세트에서 데이터를 분할하기위한 학습/테스트 인덱스 제공

split = StratifiedShuffleSplit(n_splits=1, test_size=0.2, random_state=42)
# *셔플링 및 분할 반복 횟수 :1 / 테스트분할에 데이터세트 비율 0.2 / 훈련 및 테스트 인덱스 무작위성 42
for train_index, test_index in split.split(housing, housing["income_cat"]):
    strat_train_set = housing.loc[train_index] # 배열.loc 첫번째 인자 행 / 두번째 인자 열
    strat_test_set = housing.loc[test_index]

housing = strat_train_set.copy()

print("\n\n\n##################################################\n")
print("제 03 강 실습과제\n")
print("Prepare the data for Machine Learning algorithms\n")
print("A LIBRARY of transformation functions on ANY DATASET\n")

housing = strat_train_set.drop("median_house_value", axis=1) # 훈련 세트에서 median house value 제거
housing_labels = strat_train_set["median_house_value"].copy() # 레이블에 median house value 복사
# axis = 1 이면 열 0이면 행
sample_incomplete_rows = housing[housing.isnull().any(axis=1)].head() #null인 곳을  true로 바꿔 대입
print("sample_incomplete_rows: ", sample_incomplete_rows) # 출력

print("\n\ntotal_bedrooms attribute has some missing values.\n")

median = housing["total_bedrooms"].median() # total_bedrooms 중앙값 구하기
#option 3
sample_incomplete_rows["total_bedrooms"].fillna(median, inplace=True) # fillna메소드를 사용해 null값 채우기
print("\n\nsample_incomplete_rows (option 3): \n", sample_incomplete_rows)
print("\n\nsample_incomplete_rows (option 3): \n", sample_incomplete_rows["total_bedrooms"])

# SimpleImputer: missing values 다루기 위한 Scikit-Learn class
from sklearn.impute import SimpleImputer
imputer = SimpleImputer(strategy="median") # 데이터셋에 누락된 값 표시 (전략은 중간 값)

print("\n\nRemove the text attribute because median can only be calculated on numerical attributes:\n")

housing_num = housing.drop("ocean_proximity", axis=1) #ocean 열 삭제

print("\n\nimputer.fit(housing_num): \n", imputer.fit(housing_num)) # 빈데이터에 중간값 채우기
print("\n\nimputer.statistics_ : \n", imputer.statistics_) # 각 특성의 중간값 계산 그 결과 객체에 statistics_속성에 저장
print("\n\nhousing_num.median().values: \n", housing_num.median().values) # 각 특성의 중간값 계산 저장

# Transform the training set:
X = imputer.transform(housing_num) # 누락된 값을 계산된 중간값으로 변환

housing_tr = pd.DataFrame(X, columns=housing_num.columns, # 위의 값은 변형된 특성들이므로
                          index = list(housing.index.values)) # 판다스 데이터프레임으로 다시 만듬
print("\n\nhousing_tr.loc[sample_incomplete_rows.index.values]: \n",
      housing_tr.loc[sample_incomplete_rows.index.values]) # 샘플 인컴 행에 인덱스 값을 데이터프레임 열에 대입
print("\nimputer.strategy: ", imputer.strategy)
housing_tr = pd.DataFrame(X, columns=housing_num.columns) # imputer객체에 저장된 중간값을 열로해서 데이터프레임으로 변환
print("housing_tr.head(): \n", housing_tr.head())

print("\n\n\n##################################################\n")
print("Now let's preprocess the categorical input feature, ocean_proximity: ")

housing_cat = housing[["ocean_proximity"]]
housing_cat.head(10)

from sklearn.preprocessing import OrdinalEncoder # preprocessing : 전처리
# sklearn.preprocessing.OrdinalEncoder 범주형 기능을 정수 배열로 인코딩
ordinal_encoder = OrdinalEncoder()
housing_cat_encoded = ordinal_encoder.fit_transform(housing_cat) # 하우징캣 값을 정수 배열로 변환하여 대입
print("\nhousing_cat_encoded[:10]: \n",
      housing_cat_encoded[:10])

print("\nordinal_encoder.categories_: \n",
      ordinal_encoder.categories_) # categories_ : 배열 목록

from sklearn.preprocessing import OneHotEncoder # onehotencoder : 원-핫 숫자 형 배열 인코딩

cat_encoder = OneHotEncoder()
housing_cat_1hot = cat_encoder.fit_transform(housing_cat)
print("\nhousing_cat_1hot: \n",
      housing_cat_1hot)

print("\nNumPy matrix:\n",
      housing_cat_1hot.toarray())

print("\n\nAlternatively, you can set sparse=False when creating the OneHotEncoder:")

cat_encoder = OneHotEncoder(sparse=False) #sprse True/False : 트루는 밀집행렬 , 펄스는 희소행렬
housing_cat_1hot = cat_encoder.fit_transform(housing_cat)
print("\nhousing_cat_1hot: \n",
      housing_cat_1hot)

print("\ncat_encoder.categories_: \n",
      cat_encoder.categories_)

print("\n\nLet's create a custom transformer to add extra attributes: \n")

from sklearn.base import BaseEstimator, TransformerMixin
# baseestimator : 추정기 기본 클래스 / transformermixin : 모든 변환기에 대한 mixin 클래스
# column index
rooms_ix, bedrooms_ix, population_ix, household_ix = 3, 4, 5, 6 #.csv파일에 열의 인덱스 값 시작은 0

#추가 속성 변환기 커스터마이징
class CombinedAttributesAdder(BaseEstimator, TransformerMixin):
    def __init__(self, add_bedrooms_per_room = True): # no *args or **kargs
        self.add_bedrooms_per_room = add_bedrooms_per_room
    def fit(self, X, y=None):
        return self # nothing else to do
    def transform(self, X, y=None):
        rooms_per_household = X[:, rooms_ix] / X[:, household_ix]
        population_per_household = X[:, population_ix] / X[:, household_ix]
        if self.add_bedrooms_per_room:
            bedrooms_per_room = X[:, bedrooms_ix] / X[:, rooms_ix]
            return np.c_[X, rooms_per_household, population_per_household, bedrooms_per_room]
        else:
            return np.c_[X, rooms_per_household, population_per_household]

attr_adder = CombinedAttributesAdder(add_bedrooms_per_room=False)

housing_extra_attribs = attr_adder.transform(housing.values)

housing_extra_attribs = pd.DataFrame(housing_extra_attribs, columns=list(housing.columns)+
                                                                    ["rooms_per_household", "population_per_household"])
print("\n\nhousing_extra_attribs.head(): \n", housing_extra_attribs.head())


print("\n\n\n##################################################")
print("Now let's build a pipeline for preprocessing the numerical attributes:")

from sklearn.pipeline import Pipeline # 서로 다른 매개 변수를 설정하면서 교차 검증 여러 단계 조합
from sklearn.preprocessing import StandardScaler # 평균 제거 단위 분산으로 스케일링하여 기능 표준화 / 데이터세트의 표준화

num_pipeline = Pipeline([ # 계산 후 변환하고 표준화하는 파이프라인 구성
        ('imputer', SimpleImputer(strategy="median")),
        ('attribs_adder', CombinedAttributesAdder()),
        ('std_scaler', StandardScaler()),
    ])

housing_num_tr = num_pipeline.fit_transform(housing_num)
print("\nhousing_num_tr: \n", housing_num_tr)

from sklearn.compose import ColumnTransformer # 변환기를 배열 또는 판다스 데이터프레임 열에 적용

num_attribs = list(housing_num)
cat_attribs = ["ocean_proximity"]
# columntransformer(변환기, *, 나머지='drop', sparse_threshold= 0.3
full_pipeline = ColumnTransformer([
        ("num", num_pipeline, num_attribs),
        ("cat", OneHotEncoder(), cat_attribs),
    ])

housing_prepared = full_pipeline.fit_transform(housing)

print("\nHousing prepared:", housing_prepared)

print("\nHousing prepared shape:", housing_prepared.shape)


print("\n\n##########################################################")
print("### Select and Train a Model")
print("Training and Evaluating on the Training Set")

from sklearn.linear_model import LinearRegression #선형 회귀
lin_reg = LinearRegression()
lin_reg.fit(housing_prepared, housing_labels)

# 몇가지 train 인스턴스에서 전체 파이프라인을 시도해보겠습니다.
some_data = housing.iloc[:5]
some_labels = housing_labels.iloc[:5]
some_data_prepared = full_pipeline.transform(some_data)

print("\nPredictions:\n", lin_reg.predict(some_data_prepared))
print("\nLabels:\n", list(some_labels))
print("\nsome_data_prepared: \n", some_data_prepared)

from sklearn.metrics import mean_squared_error #평균 제곱 오차 회귀 손실
housing_predictions = lin_reg.predict(housing_prepared)
lin_mse = mean_squared_error(housing_labels, housing_predictions)
lin_rmse = np.sqrt(lin_mse)
print("\nRMSE:\n", lin_rmse)

from sklearn.metrics import mean_absolute_error #절대 오차 회귀 손실
lin_mae = mean_absolute_error(housing_labels, housing_predictions)
print("\nMAE:\n", lin_mae)


print("\n\n### DecisionTreeRegressor: \n") #의사결정트리회귀자

from sklearn.tree import DecisionTreeRegressor
tree_reg = DecisionTreeRegressor(random_state=42)
tree_reg.fit(housing_prepared, housing_labels)

housing_predictions = tree_reg.predict(housing_prepared)
tree_mse = mean_squared_error(housing_labels, housing_predictions)
tree_rmse = np.sqrt(tree_mse)
print("\nTree RMSE:\n", tree_rmse)

print("Wait, what!? No error at all?")

print("\n\n##########################################################")
print("Fine-tune your model")

from sklearn.model_selection import cross_val_score #교차 검증으로 점수평가

scores = cross_val_score(tree_reg, housing_prepared, housing_labels,
                         scoring="neg_mean_squared_error", cv=10)
tree_rmse_scores = np.sqrt(-scores)


def display_scores(scores):
    print("\nScores:\n", scores)
    print("\nMean:\n", scores.mean())
    print("\nStandard deviation:\n", scores.std())

print("\n\ndisplay_scores(tree_rmse_scores): \n", display_scores(tree_rmse_scores))

print("\n\n Compute the same scores for the Linear Regression\n")

lin_scores = cross_val_score(lin_reg, housing_prepared, housing_labels,
                             scoring="neg_mean_squared_error", cv=10)
lin_rmse_scores = np.sqrt(-lin_scores)
print("\n\ndisplay_scores(lin_rmse_scores): \n", display_scores(lin_rmse_scores))


print("\n\nRandomForestRegressor: \n")

from sklearn.ensemble import RandomForestRegressor
#RandomForestRegressor : 데이터 세트의 하위 샘플에 대한 여러 분류의사결정트리를 맞추고 평균을 사용하여 정확도를 높이고 과적 합을 제어하는 메타 추정기
forest_reg = RandomForestRegressor(n_estimators=100, random_state=42)
forest_reg.fit(housing_prepared, housing_labels)

housing_predictions = forest_reg.predict(housing_prepared)
forest_mse = mean_squared_error(housing_labels, housing_predictions)
forest_rmse = np.sqrt(forest_mse)
print("\nRandomForestRegressor RMSE:\n", forest_rmse)


print("\n\n##########################################################\n")
print("GridSearchCV: \n")

from sklearn.model_selection import GridSearchCV
# gridsearchCV : 추정기에 대한 지정된 매개 변수 값에 대한 철저한 검색
# 최적의 파라미터를 찾고 교차검증도 해준다.
param_grid = [
    # try 12 (3×4) combinations of hyperparameters
    {'n_estimators': [3, 10, 30], 'max_features': [2, 4, 6, 8]},
    # then try 6 (2×3) combinations with bootstrap set as False
    {'bootstrap': [False], 'n_estimators': [3, 10], 'max_features': [2, 3, 4]},
]

forest_reg = RandomForestRegressor(random_state=42)
# train across 5 folds, that's a total of (12+6)*5=90 rounds of training
grid_search = GridSearchCV(forest_reg, param_grid, cv=5,
                           scoring='neg_mean_squared_error')
print("\ngrid_search.fit(housing_prepared, housing_labels) : \n",
      grid_search.fit(housing_prepared, housing_labels))

print("\ngrid_search.best_params_ : \n",
      grid_search.best_params_)

print("\ngrid_search.best_estimator_ : \n",
      grid_search.best_estimator_)

print("\n\n##########################################################\n")
print("RandomizedSearchCV: \n")

from sklearn.model_selection import RandomizedSearchCV #하이퍼매개 변수 무작위 검색
# 추정기의 매개 변수는 매개 변수 설정에 대한 교차 검증검색을 통해 최적화화from scipy.stats import randint

param_distribs = {
        'n_estimators': randint(low=1, high=200),
        'max_features': randint(low=1, high=8),
    }

forest_reg = RandomForestRegressor(random_state=42)
rnd_search = RandomizedSearchCV(forest_reg, param_distributions=param_distribs,
                                n_iter=10, cv=5, scoring='neg_mean_squared_error',
                                random_state=42)
print("\nrnd_search.fit(housing_prepared, housing_labels) : \n",
      rnd_search.fit(housing_prepared, housing_labels))

cvres = rnd_search.cv_results_
for mean_score, params in zip(cvres["mean_test_score"], cvres["params"]):
    print(np.sqrt(-mean_score), params)

feature_importances = grid_search.best_estimator_.feature_importances_
#각 속성의 상대적 중요성을 나타낼수 있습니다.
print("\nfeature_importances: \n", feature_importances)

extra_attribs = ["rooms_per_hhold", "pop_per_hhold", "bedrooms_per_room"]
#cat_encoder = cat_pipeline.named_steps["cat_encoder"] # old solution
# cat_one_hot_attribs = list(encoder.classes_)
cat_encoder = full_pipeline.named_transformers_["cat"]
cat_one_hot_attribs = list(cat_encoder.categories_[0])
attributes = num_attribs + extra_attribs + cat_one_hot_attribs

print("\nsorted(zip(feature_importances, attributes), reverse=True) : \n",
      sorted(zip(feature_importances, attributes), reverse=True))


print("\n\n#######################################################\n")
print("### Evaluate your system on the Test Set\n")

final_model = grid_search.best_estimator_
# 최종 모델을 테스트 셋에서 평가해 본다.
X_test = strat_test_set.drop("median_house_value", axis=1)
y_test = strat_test_set["median_house_value"].copy()

X_test_prepared = full_pipeline.transform(X_test)

final_predictions = final_model.predict(X_test_prepared)

final_mse = mean_squared_error(y_test, final_predictions)

final_rmse = np.sqrt(final_mse)

print("y_test: \n", y_test[:10])
print("final_predictions: \n", final_predictions[:10])
print("\nFinal RMSE:\n", final_rmse)