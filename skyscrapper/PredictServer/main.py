# package import
from fastapi import FastAPI
import uvicorn
from pydantic import BaseModel
from starlette.responses import JSONResponse

import pickle
import numpy as np
import pandas as pd

# Inquiry Model 생성


class Inquiry(BaseModel):
    message: str

# Prodyct Model 생성


class Product(BaseModel):
    product_info: str


app = FastAPI()

with open('INQ_CV.pickle', 'rb') as f:
    inq_loaded_vect = pickle.load(f)

with open('INQ_NB.pickle', 'rb') as f:
    inq_loaded_dict = pickle.load(f)

with open('PRODUCT_CV.pickle', 'rb') as f:
    pro_loaded_vect = pickle.load(f)

with open('PRODUCT_NB.pickle', 'rb') as f:
    pro_loaded_dict = pickle.load(f)

print("CLEAR:PICKLE IMPORT")

@app.post(path="/predinquiry", status_code=201)
def predict_inquiry(inquiry: Inquiry):

    print("inq", inquiry.message)

    tf = inq_loaded_vect.transform([inquiry.message])
    target = ['0', '1']
    pred = inq_loaded_dict.predict(tf)
    result = {"pending_status": target[pred[0]]}

    print("INQUIRY= ", result)

    return JSONResponse(result)


@app.post(path="/predproduct", status_code=201)
def predict_product(product: Product):

    print("pro", product.product_info)

    tf = pro_loaded_vect.transform([product.product_info])
    target = ['0', '1']
    pred = pro_loaded_dict.predict(tf)
    result = {"pending_status": target[pred[0]]}

    print("PRODUCT= ", result)

    return JSONResponse(result)


if __name__ == '__main__':
    uvicorn.run(app, host="127.0.0.1", port=8000)


# uvicorn main:app --reload
