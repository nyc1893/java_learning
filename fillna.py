
import os

import pandas as pd
import numpy as np

tr = pd.read_csv("a2.csv",encoding = 'gbk', engine='python')   

tr = tr.fillna("null")
tr.head()
tr.to_csv("a3.csv",index =None,encoding="utf_8_sig",)