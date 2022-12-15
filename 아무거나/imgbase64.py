import base64
import io
from PIL import Image
import os

def base64encode(path = "C:/Users/USER/Desktop/images/"):
    print(path)
    file_list = os.listdir(path)
    for file in file_list:
        with open(path+file,'rb') as img:
            #rb mode returns bytes type
            b64string = base64.b64encode(img.read())
            
            #test
            base64decode(b64string)

def base64decode(b64string):
    imgdata = base64.b64decode(b64string)   
    dataBytesIO = io.BytesIO(imgdata) 
    # differnce between open() & io.BytesIO()
    # is open() won't keep string in memory after write while io.BytesIO() use memory buffer(RAM) to write
    # so io.BytesIO() can do optimization when you need concatenate data or file writing system(just act like a buffer)
    image = Image.open(dataBytesIO)
    image.show()

#test
base64encode()



 



    

    