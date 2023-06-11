import pandas as pd
pd.read_csv('class-roles.csv', header=None).T.to_csv('roles-class.csv', header=False, index=False)