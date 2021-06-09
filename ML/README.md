## IDDR Dataset
Dataset: https://github.com/sekilab/RoadDamageDetector</br>
The dataset used was obtained from the Global Road Damage Detection Challenge 2020 which contains 21041 road images collected from India, Japan, and the Czech Republic.</br>
The images divided into 5 classes, namely:</br>
1. D00 : 6592 images
2. D10 : 4446 images
3. D20 : 8381 images
4. D40 : 5627 images
5. D44 : 5057 images

![Road Damamage Type](https://github.com/B21-CAP0475/IDDR/blob/main/ML/assets/RoadDamageTypeDef.png)

## Object Detection API Documentation
You can see the full documentation of object detection API in this link : https://tensorflow-object-detection-api-tutorial.readthedocs.io/en/latest/

## This Project Was Built With
[Tensorflow](https://www.tensorflow.org/)</br>
[Tensorflow Object Detection API](https://tensorflow-object-detection-api-tutorial.readthedocs.io/en/latest/)</br>
[Tensorflow Lite](https://www.tensorflow.org/lite)</br>
[SSD MobileNet V2](https://github.com/tensorflow/models/blob/master/research/object_detection/g3doc/tf2_detection_zoo.md)

## Prerequisites
To use this project locally, you requires several resources to be prepared and installed on the local computer:
- [Python Virtual Environtment](https://www.python.org/downloads/) (recommendation)
- [Python ver 3.6.8](https://www.python.org/downloads/) (we use python version 3.6.8)
- [Object Detection API](https://tensorflow-object-detection-api-tutorial.readthedocs.io/en/latest/)


### Installation

1. Clone the repository

   ```sh
   $ git clone https://github.com/B21-CAP0475/IDDR.git

   # go to ML directory
   $ cd ML
   ```

2. Create virtual environment **(recommendation)**. Run these commands:

   - Linux/macOs
     ```sh
     $ virtualenv <your env name>
     $ source <your env name>/bin/activate
     ```
   - Windows
     ```sh
     python -m venv <your env name>
     <your env name>\scripts\activate
     ```

3. Install [Object Detection API](https://tensorflow-object-detection-api-tutorial.readthedocs.io/en/latest/) and follow all the instructions
4. If you want to train using different model, open [Modeling.ipynb](https://github.com/B21-CAP0475/IDDR/blob/main/ML/Modeling.ipynb) and replace the model with yours
