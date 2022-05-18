import pandas as pd

def get_data():
    filePath = ""
    df = pd.read_csv(filePath)
    df_transformed = df.copy()
    x_train, x_test, y_train, y_test = train_test_split( df_transformed[["BMI","PhysicalHealth","MentalHealth","Smoking","AlcoholDrinking","Sex","Race", "PhysicalActivity"]],
                                                         df_transformed["HeartDisease"],test_size=0.20,
                                                         random_state=21)
    print('Shape of Training Xs:{}'.format(x_train.shape))
    print('Shape of Test Xs:{}'.format(x_test.shape))
    print('Shape of Training y:{}'.format(y_train.shape))
    print('Shape of Test y:{}'.format(y_test.shape))