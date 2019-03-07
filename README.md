# smart-stock
使用爬虫从财经网站收集股票财务数据，定制化的分析、展示数据以辅助股票投资决策

- 功能概要
    -
    
- 通过爬虫获取原始数据
- 定制化图表直观展示历史数据的趋势走势
- 定制化初步快速筛选出符合条件的股票

- 类似八爪鱼的功能,可配置链接,要获取的数据字段,xpath,获取数据

- 详细功能
    -
```
1.保底年化5%，盈利部分扣除5%后，投资方还可得60%分成，投资方没有本金和基础利息亏损风险
2.募资锁定最低1年，最低1K，单位为K，半年一次加仓机会
3.募资额度等于自有资金额度，就是能接受净值45%的跌幅，保证投资方本金+利息
4.净值份额计算，起始净值为1元，投入1W，即为1W份，若年中加仓时候净值为2元，投入1W，即为5K份
5.募资和股票买卖记入系统
6.交易记录: 股票代码\名称、数量、买入成本、总金额、买入时间
7.持仓信息: 股票代码、数量、成本价、当前价、持仓成本、市值、成本总额、盈亏、盈亏率
8.募资记录: 买卖份额、用户、交易时间、到期时间、锁定剩余天数、交易净值、交易总金额
9.用户信息: 总份额、用户、本金总额、市值、市值占基金市值比例、利息、盈利（含利息）、盈亏率，盈利走势图
10.基金信息: 当前净值、仓位水平、总份额、本金、总市值、盈亏比例，发起方盈利额，投资方盈利额，盈亏总额
11.净值走势图
12.仓位走势图
13.盈亏率走势图
14.定时公布股票交易记录和持仓记录
```

- 技术选型
    -
```
前端
页面数据渲染 art-template
页面样式布局 bootstrap
jquery
分页 jquery-bootpag
报表 Highcharts-7.0.3
报表 Highcharts-Gantt-7.0.3

后端
spring-boot
thymeleaf
druid
mybatis
mysql
```

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
