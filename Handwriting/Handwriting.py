import os
import tensorflow as tf
from tensorflow import keras
import numpy as np
import matplotlib.pyplot as plt

# Goal ==> Train a model that is able to distinguish numbers based on handwriting
# and have the model stop training once it has reached a 99% accuracy 

# Load the data
mnist = tf.keras.datasets.mnist
# # Discard test test
(x_train,y_train),(x_test,y_test) = mnist.load_data(path ="mnist.npz")

# # normalize pixel values
x_train = x_train / 255.0

# Look at shape of training data
data_shape = x_train.shape
print(f"There are {data_shape[0]} examples with shape ({data_shape[1]}, {data_shape[2]})")

# Visual example of the data
index = 5287
np.set_printoptions(linewidth=320)
print(f'LABEL: {y_train[index]}')
print(f'\nIMAGE PIXEL ARRAY:\n {x_train[index]}')
plt.imshow(x_train[index],cmap='Greys')
plt.show()

# Create our call back
class myCallback(tf.keras.callbacks.Callback):
   def on_epoch_end(self,epoch,logs={}):
      # if our accuracy is above 99%, then the training will stop
      if(logs.get('accuracy') > .99):
         print("\nReaching 99% accuracy so cancelling training")
         self.model.stop_training = True
   

# Creation and training of our model
def train_mnist(x_train, y_train):
   # instance of our callback
   callbacks = myCallback()
   
   # create model
   model = tf.keras.models.Sequential([
      tf.keras.layers.Flatten(input_shape=(28,28)),
      tf.keras.layers.Dense(128, activation=tf.nn.relu),
      tf.keras.layers.Dense(10, activation=tf.nn.softmax)
   ])
   
   # compile model
   model.compile(optimizer = tf.optimizers.Adam(),
                 loss = 'sparse_categorical_crossentropy',
                 metrics=['accuracy'])
   
   # # saving training history
   history = model.fit(x_train,y_train,epochs=10,callbacks = [callbacks])
   
   results = model.evaluate(x_test,y_test)
   print("test loss, test acc:", results)
   
   # return history
   return history


hist = train_mnist(x_train,y_train)

