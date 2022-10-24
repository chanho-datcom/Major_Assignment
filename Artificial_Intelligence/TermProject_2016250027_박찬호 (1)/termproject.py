import os
import urllib
import pandas as pd
import tarfile
import matplotlib.pyplot as plt
import numpy as np
from pandas.plotting import scatter_matrix


DOWNLOAD_ROOT = "http://cksgh9206.dothome.co.kr/"
FIRES_PATH = os.path.join("datasets", "sanbul")
FIRES_URL = DOWNLOAD_ROOT + "datasets/sanbul/sanbul-5.tgz"
def fetch_fires_data(fires_url=FIRES_URL, fires_path=FIRES_PATH):
    if not os.path.isdir(fires_path):
        os.makedirs(fires_path)
    tgz_path = os.path.join(fires_path, "sanbul-5.tgz")
    urllib.request.urlretrieve(fires_url, tgz_path)
    fires_tgz = tarfile.open(tgz_path)
    fires_tgz.extractall(path=fires_path)
    fires_tgz.close()

fetch_fires_data()

def load_fires_data(fires_path=FIRES_PATH):
    csv_path = os.path.join(fires_path, "sanbul-5.csv")
    return pd.read_csv(csv_path)

fires = load_fires_data()

"""
print("################ fires.head\n")
print(fires.head())
print("################ fires.info\n")
print(fires.info())
print("################ fires.describe")
print(fires.describe())

month = fires.month.value_counts()
print("################ month")
print(month)
print("################ day")
day = fires.day.value_counts()
print(day)
"""
plt.subplot(331)
plt.hist(fires.avg_temp, bins=80)
plt.title('avg_temp')
plt.subplot(332)
plt.hist(fires.avg_wind, bins=80)
plt.title('avg_wind')
plt.subplot(333)
plt.hist(fires.burned_area, bins=80)
plt.title('burned_area')
plt.subplot(334)
plt.hist(fires.latitude, bins=80)
plt.title('latitude')
plt.subplot(335)
plt.hist(fires.longitude, bins=80)
plt.title('longitude')
plt.subplot(336)
plt.hist(fires.max_temp, bins=80)
plt.title('max_Temp')
plt.subplot(337)
plt.hist(fires.max_wind_speed, bins=80)
plt.title('max_wind_speed')
plt.tight_layout()
plt.show()


plt.subplot(121)
plt.hist(fires.burned_area, bins=50)
plt.title('burned_area')
plt.subplot(122)
plt.hist(np.log(fires.burned_area+1), bins=50)
plt.title('ln(burned_area+1)')
plt.tight_layout()
plt.show()

from sklearn.model_selection import train_test_split
train_set, test_set = train_test_split(fires, test_size=0.2, shuffle=True, random_state=42)

test_set.head()

fires["month"].hist()
plt.tight_layout()
plt.show()

from sklearn.model_selection import StratifiedShuffleSplit

split = StratifiedShuffleSplit(n_splits=1, test_size=0.2, random_state=42)
for train_index, test_index in split.split(fires, fires["month"]):
    strat_train_set = fires.loc[train_index]
    strat_test_set = fires.loc[test_index]

print("\nMonth category proportion: \n",
strat_test_set["month"].value_counts()/len(strat_test_set))

print("\nOverall month category proportion: \n",
fires["month"].value_counts()/len(fires))


fires_df = pd.DataFrame(data=fires, columns=['burned_area', 'max_temp', 'avg_temp', 'max_wind_speed'])
scatter_matrix(fires_df, alpha=0.5, figsize=(8, 8), diagonal='hist')
plt.show()

fires.plot(kind="scatter", x="longitude", y="latitude", alpha=0.4,s=fires["max_temp"], label="max_temp",c="burned_area", cmap=plt.get_cmap("jet"), colorbar=True)
plt.show()

corr_matrix = fires.corr()
print("Looking for correlations\nCORR_MATRIX :")
print(corr_matrix["burned_area"].sort_values(ascending=False))

from sklearn.preprocessing import OneHotEncoder
cat_encoder = OneHotEncoder()

fires = strat_train_set.drop(["burned_area"], axis = 1)
fires_labels = strat_train_set["burned_area"].copy()

cat_day_encoder = cat_encoder.fit_transform(fires[["day"]])
print("cat_day_encoder :\n", cat_day_encoder)
print("cat_day_encoder.categories : ", cat_encoder.categories_)

cat_month_encoder = cat_encoder.fit_transform(fires[["month"]])
print("cat_month_encoder :\n", cat_month_encoder)
print("cat_month_encoder.categories : ", cat_encoder.categories_)


print("\n\n########################################################################")
print("Now let's build a pipeline for preprocessing the numerical attributes:")
fires_num = fires.drop(["month", "day"], axis=1)

from sklearn.pipeline import Pipeline
from sklearn.preprocessing import StandardScaler

num_pipeline = Pipeline([
('std_scaler', StandardScaler()),
])
from sklearn.compose import ColumnTransformer
num_attribs = list(fires_num)
cat_attribs = ["month", "day"]

full_pipeline = ColumnTransformer([
("num", num_pipeline, num_attribs),
("cat", OneHotEncoder(), cat_attribs),
])
fires_prepared = full_pipeline.fit_transform(fires)

print(fires_prepared)

print("#########################################################2")
from sklearn.model_selection import GridSearchCV
from sklearn.svm import SVR


#sgd
X = fires_prepared
y = fires_labels
from sklearn.linear_model import SGDRegressor
sgd_reg = SGDRegressor(max_iter=10000, tol=1e-3, penalty=None, eta0=0.1)
sgd_reg.fit(fires_prepared, fires_labels)

params = {
    'alpha': 10.0 ** -np.arange(1, 7),
    'loss': ['squared_loss', 'huber', 'epsilon_insensitive'],
    'penalty': ['l2', 'l1', 'elasticnet'],
    'learning_rate': ['constant', 'optimal', 'invscaling'],
}

grid_search_cv = GridSearchCV(sgd_reg, params, verbose=1, cv=10)

grid_search_cv.fit(fires_prepared, fires_labels)

sgd_best_model = grid_search_cv.best_estimator_

print("best_estimator : ", sgd_best_model)

def plot_learning_curves(model, X, y):
    X_train, X_val, y_train, y_val = train_test_split(X, y, test_size=0.2, random_state=10)
    train_errors, val_errors = [], []
    for m in range(1, len(X_train)):
        model.fit(X_train[:m], y_train[:m])
        y_train_predict = model.predict(X_train[:m])
        y_val_predict = model.predict(X_val)
        train_errors.append(mean_squared_error(y_train_predict, y_train[:m]))
        val_errors.append(mean_squared_error(y_val_predict, y_val))
    plt.plot(np.sqrt(train_errors), "r-+", linewidth=2, label="train")
    plt.plot(np.sqrt(val_errors), "b-", linewidth=3, label="val")
    plt.legend(loc="upper right", fontsize=14)  # not shown in the book
    plt.xlabel("Training set size", fontsize=14)  # not shown
    plt.ylabel("RMSE", fontsize=14)

plot_learning_curves(sgd_reg, X, y)
plt.axis([0, 80, 0, 3])
plt.show()

from sklearn.metrics import mean_squared_error
fires_predictions = sgd_reg.predict(fires_prepared)
sgd_mse = mean_squared_error(fires_labels, fires_predictions)
sgd_rmse = np.sqrt(sgd_mse)
# revert into the original value: y=ln(burned_area+1) => burned_area = exp(y)-1
sgd_rmse_reverted = np.exp(sgd_rmse)-1
print("\nLR - RMSE(train set):\n", sgd_rmse_reverted)

from sklearn.model_selection import cross_val_score

sgd_scores = cross_val_score(sgd_reg, fires_prepared, fires_labels,
scoring="neg_mean_squared_error", cv=10)

fires_train_prepared, fires_test_prepared, fires_train_labels, fires_test_labels = train_test_split(fires_prepared, fires_labels, test_size=0.2,
random_state=42)

def display_scores(scores):
    print("Scores:", scores)
    print("Mean:", scores.mean())
    print("Standard deviation:", scores.std())

sgd_rmse_scores = np.sqrt(-sgd_scores)
print("\nLinear Regression scores (train set): \n")
display_scores(sgd_rmse_scores)


sgd_scores = cross_val_score(sgd_reg, fires_test_prepared, fires_test_labels,
scoring="neg_mean_squared_error", cv=10)
lin_rmse_scores = np.sqrt(-sgd_scores)
print("\nLinear Regression scores (test set): \n")
display_scores(lin_rmse_scores)














import tensorflow as tf
from tensorflow import keras
from sklearn.datasets import make_moons

X_train, X_valid, y_train, y_valid = train_test_split(fires_prepared, fires_labels, test_size=0.2,
random_state=42)
fires_train_prepared, fires_test_prepared, fires_train_labels, fires_test_labels = train_test_split(fires_prepared, fires_labels, test_size=0.2,
random_state=42)
X_test = fires_test_prepared
y_test = fires_test_labels

np.random.seed(42)
tf.random.set_seed(42)

mlp_model = keras.models.Sequential([
    keras.layers.Dense(30, activation="relu", input_shape=X_train.shape[1:]),
    keras.layers.Dense(1, activation="relu"),
])

mlp_model.compile(loss="mean_squared_error", optimizer=keras.optimizers.SGD(lr=1e-3))

history = mlp_model.fit(X_train, y_train, epochs=200, validation_data=(X_valid, y_valid))
mse_test = mlp_model.evaluate(X_test, y_test)
X_new = X_test[:10]
y_pred = mlp_model.predict(X_new)

plt.plot(pd.DataFrame(history.history))
plt.grid(True)
plt.gca().set_ylim(0, 1)
plt.show()

model =mlp_model
model_version = "0001"
model_name = "my_fires_model"
model_path = os.path.join( model_name, model_version)
tf.saved_model.save(model, model_path)











