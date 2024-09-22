import pandas as pd
import matplotlib.pyplot as plt
from pmdarima import auto_arima
from statsmodels.tsa.statespace.sarimax import SARIMAX
from statsmodels.api.tsa import seasonal_decompose
# from statsmodels.tsa.seasonal import seasonal_decompose

def fit_sarima_model(time_series, forecast_days):
    """
    参数:
    time_series (pd.Series): 输入的时间序列数据。
    forecast_days (int): 需要预测的天数。
    返回:
    tuple: (预测结果, 拟合值, 最佳参数)
    """
    # 分解周期信息，得到周期
    plt.rcParams['font.sans-serif'] = ['SimHei']  # 用来正常显示中文标签
    plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号
    decomposition = seasonal_decompose(time_series, model='additive', extrapolate_trend='freq')
    fig = decomposition.plot()
    plt.show()

    # 使用auto_arima自动选择最佳SARIMA模型参数
    model = auto_arima(time_series, seasonal=True, m=12, trace=True,
                       error_action='ignore', suppress_warnings=True)

    # 获取SARIMA模型的最佳参数
    order = model.order
    seasonal_order = model.seasonal_order

    # 拟合SARIMA模型
    sarima_model = SARIMAX(time_series, order=order, seasonal_order=seasonal_order)
    sarima_result = sarima_model.fit(disp=False)

    # 进行预测
    forecast = sarima_result.get_forecast(steps=forecast_days)
    forecast_index = pd.date_range(start=time_series.index[-1] + pd.Timedelta(days=1),
                                   periods=forecast_days, freq='D')
    forecast_series = pd.Series(forecast.predicted_mean, index=forecast_index)

    # 获取拟合值
    fitted_values = sarima_result.fittedvalues

    # 返回预测结果，拟合值，最佳参数
    return forecast_series, fitted_values, order, seasonal_order


def plot_results(time_series, fitted_values, forecast_series, category, col_name):
    """
    time_series (pd.Series): 输入的时间序列数据。
    fitted_values (pd.Series): 拟合值。
    forecast_series (pd.Series): 预测值。
    category (str): 分类名称。
    col_name (str): 列名称。
    """
    plt.rcParams['font.sans-serif'] = ['SimHei']  # 用来正常显示中文标签
    plt.rcParams['axes.unicode_minus'] = False  # 用来正常显示负号

    plt.figure(figsize=(12, 6))

    # 绘制真实值
    plt.plot(time_series, label='真实值', color='blue')
    # 绘制拟合值
    plt.plot(time_series.index, fitted_values, label='拟合值', color='green')
    # 绘制预测值
    plt.plot(forecast_series, label='预测值', color='red')

    # 设置图例、标题和标签
    plt.legend()
    plt.title(f'SARIMA模型预测结果 - 分类: {category}, 列: {col_name}')
    plt.xlabel('日期')
    plt.ylabel('值')
    plt.grid(True)

    # 显示图形
    plt.show()


if __name__ == "__main__":
    # 读取数据
    df = pd.read_csv('./cost_plus_pricing.csv', parse_dates=['销售日期'], index_col='销售日期')

    # 需要预测的列
    forecast_cols = ['平均销量(千克)', '平均批发价格(元/千克)', '平均销售单价(元/千克)']

    # 预测结果和最佳参数
    all_forecasts = []
    best_params = []

    categories = df['分类名称'].unique()
    for category in categories:
        for col_name in forecast_cols:
            # 提取当前分类的数据
            category_df = df[df['分类名称'] == category]
            category_df = category_df.sort_index()

            # 提取列
            time_series = category_df[col_name]
            # 确保时间序列有频率信息
            time_series = time_series.resample('D').mean()
            # 使用线性插值填充缺失值
            time_series = time_series.interpolate(method='linear')

            # 进行预测
            forecast_series, fitted_values, order, seasonal_order = fit_sarima_model(time_series, forecast_days=15)

            # 存储预测结果
            forecast_df = forecast_series.reset_index()
            forecast_df.columns = ['日期', f'预测值_{col_name}']
            forecast_df['分类名称'] = category
            all_forecasts.append(forecast_df)

            # 存储最佳参数
            params_df = pd.DataFrame({
                'Order': [f'{order[0]}, {order[1]}, {order[2]}'],
                'SeasonalOrder': [
                    f'{seasonal_order[0]}, {seasonal_order[1]}, {seasonal_order[2]}, {seasonal_order[3]}'],
                '分类名称': [category],
                '列名称': [col_name]
            })
            best_params.append(params_df)

            # 绘制预测结果
            plot_results(time_series, fitted_values, forecast_series, category, col_name)

    # 合并所有分类的预测结果
    all_forecasts_df = pd.concat(all_forecasts, ignore_index=True)
    # 保存到 CSV 文件
    all_forecasts_df.to_csv('SARIMA_all_forecasts.csv', index=False)

    # 合并所有分类的最佳参数
    best_params_df = pd.concat(best_params, ignore_index=True)
    # 保存到 CSV 文件
    best_params_df.to_csv('SARIMA_best_params.csv', index=False)
