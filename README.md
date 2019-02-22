# smart-stock
使用爬虫从财经网站收集股票财务数据，定制化的分析、展示数据以辅助股票投资决策

- 功能概要
    -
    
- 通过爬虫获取原始数据
- 定制化图表直观展示历史数据的趋势走势
- 定制化初步快速筛选出符合条件的股票

- 类似八爪鱼的功能,可配置链接,要获取的数据字段,xpath,获取数据


- 数据接口相关
    -
    
- 实时股票列表接口
``
http://quote.tool.hexun.com/hqzx/quote.aspx?type=2&market=0&sorttype=0&updown=down&page=1&count=5000&time=
``

- 公司简介
```
http://stockdata.stock.hexun.com/gszl/s000536.shtml
```

- 股票财务指标
```
时间参数
年度 accountdate=yyyy.12.31
前三季 accountdate=yyyy.09.30
中期 accountdate=yyyy.06.30
第一季 accountdate=yyyy.03.15

最新财务指标
//2018年度
http://stockdata.stock.hexun.com/2008/zxcwzb.aspx?stockid=000536&accountdate=2018.12.31

财务比率
//2018年度
http://stockdata.stock.hexun.com/2008/cwbl.aspx?stockid=000536&accountdate=2018.12.31

资产负债
//2018年度
http://stockdata.stock.hexun.com/2008/zcfz.aspx?stockid=000536&accountdate=2018.12.31

利润表
//2018年度
http://stockdata.stock.hexun.com/2008/lr.aspx?stockid=000536&accountdate=2018.12.31

现金流量
//2018年度
http://stockdata.stock.hexun.com/2008/xjll.aspx?stockid=000536&accountdate=2018.12.31

主营收入
//2018年度
http://stockdata.stock.hexun.com/2008/zysrfb.aspx?stockid=000536&accountdate=2018.12.31

资产减值
//2018年度
http://stockdata.stock.hexun.com/2008/zcjz.aspx?stockid=000536&accountdate=2018.12.31

应收账款
//2018年度
http://stockdata.stock.hexun.com/2008/yszk.aspx?stockid=000536&accountdate=2018.12.31

其他应收账款
//2018年度
http://stockdata.stock.hexun.com/2008/qtyszk.aspx?stockid=000536&accountdate=2018.12.31
```
