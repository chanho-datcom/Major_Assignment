import tensorflow as tf
from tensorflow.keras.applications.resnet50 import preprocess_input, decode_predictions
from tensorflow.keras.preprocessing import image
import numpy as np
import matplotlib.pyplot as plt

IMG_PATH = 'data/sample-images/cat.jpg'

img = image.load_img(IMG_PATH, target_size=(224, 224))
plt.imshow(img)
plt.show()

model = tf.keras.applications.resnet50.ResNet50()

def classify(img_path):
    img = image.load_img(img_path, target_size=(224, 224))
    model = tf.keras.applications.resnet50.ResNet50()
    img_array = image.img_to_array(img)
    img_batch = np.expand_dims(img_array, axis=0)
    img_preprocessed = preprocess_input(img_batch)
    prediction = model.predict(img_preprocessed)
    print(decode_predictions(prediction, top=3)[0])

print("2016250027 ParkChanHo")
classify(IMG_PATH)