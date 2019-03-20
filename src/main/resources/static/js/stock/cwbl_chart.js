var CwblChartPage = (function(){
    var data;
    var title = [
        "流动比率",
        "速动比率",
        "现金比率",
        "权益负债比率",
        "长期资产适合率",
        "流动资产对负债总额比率",
        "有息负债率",
        "不良债权比率",
        "存货流动负债比率（对金融企业无意义）",
        "债务偿付比率",
        "负债结构比率",
        "坏帐备抵率",
        "固定支出成本占总成本的比率",
        "利息支付倍数",
        "固定财务费用保付率",
        "清算价值比率",
        "营运资产与总资产的比率",
        "现金与总资产的比率",
        "净资产收益率",
        "总资产收益率",
        "资本金收益率",
        "主营业务利润率",
        "主营收入毛利润率(金融企业指标名称变化）",
        "主营收入税前利润率(金融企业指标名称变化）",
        "主营收入税后利润率(金融企业指标名称变化）",
        "扣除非经常损益后的净利润率",
        "营业利润率",
        "营业比率",
        "成本费用利润率",
        "销售期间费用率",
        "非经常性损益比率",
        "关联交易比率",
        "本期股利收益率",
        "股利支付比率",
        "收益留存比率",
        "应收帐款周转率",
        "应收帐款回收期(天) （对金融企业无意义）",
        "流动资产周转率",
        "固定资产周转率（对金融企业无意义）",
        "存货周转率（对金融企业无意义）",
        "存货销售期(天) （对金融企业无意义）",
        "总资产周转率(金融企业指标名称变化）",
        "净资产周转率",
        "主营利润比重",
        "流动资产对总资产的比率",
        "资产负债率",
        "资本化比率",
        "资本固定化比率",
        "资本周转率",
        "固定资产与长期负债率",
        "固定资产与股东权益比率",
        "固定资产净值率(%)",
        "权益系数",
        "长期负债比率(6)",
        "产权比率",
        "净值与负债比率",
        "净值与固定资产比率",
        "有形资产净值债务率",
        "股东权益比率",
        "主营业务增长率",
        "应收款项增长率",
        "净利润增长率",
        "固定资产投资扩张率",
        "总资产扩张率",
        "每股收益增长率",
        "净资产增长率(%)",
        "每股经营现金净流量(元)",
        "资产的经营现金流量回报率(%)",
        "净利润现金含量",
        "经营现金净流量对负债的比率",
        "经营活动产生的现金净流量增长率",
        "营业活动收益质量",
        "主营业务现金比率",
        "现金流量结构比率",
        "每股净资产",
        "调整后每股净资产",
        "每股收益",
        "扣除非经常损益的每股收益",
        "每股主营收入",
        "每股经营活动产生的现金流量净额",
        "每股资本公积"
    ];
    var dateData;
    var chartData;
    var series;
    var myChart;

    function getParam() {
        var param = $("#cwblForm").serializeObject();
        param.paramStockId = $("#id").val();
        return param;
    }

    function initEvents() {
        $("#cwblSearchBtn").on("click", function () {
            initChart();
        });

        $("#cwblForm #paramStartYear").selecter({
            url: "/stock_finance/date_options",
            param:{
                stockId :$("#id").val()
            }
        });

        $("#cwblForm #paramEndYear").selecter({
            url: "/stock_finance/date_options",
            param:{
                stockId :$("#id").val()
            }
        });

        $("#cwblForm #paramDateType").selecter({
            url: "/stock_finance/date_type_options"
        });
    }

    function initChart() {
        $.ajax({
            url: '/stock_finance/list',
            type: 'POST',
            contentType: 'application/json;charset=UTF-8',
            data: JSON.stringify(getParam()),
            success: function (ret) {
                data = ret.data;
                dateData = [];
                chartData = [];
                series = [];
                if(data){
                    for(var i in data){
                        dateData.push(data[i].date);
                        if(data[i].info == '{}'){
                            continue;
                        }
                        var info = JSON.parse(data[i].info);
                        for(var j in title){
                            if(!chartData[j]){
                                chartData[j] = [];
                            }
                            var num = info[title[j]].replace(/,/g,"");
                            if(isNaN(num)){
                                chartData[j].push(0);
                            }else{
                                chartData[j].push(Number(num));
                            }
                        }
                    }

                    for(var i in chartData){
                        series.push({
                            name: title[i],
                            type:'line',
                            data: chartData[i],
                            markPoint : {
                                data : [
                                    {type : 'max', name: '最大值'},
                                    {type : 'min', name: '最小值'}
                                ]
                            },
                            markLine : {
                                data : [
                                    {type : 'average', name: '平均值'}
                                ]
                            }
                        });
                    }
                }
                draw();
            }
        })
    }

    function draw() {
        var option = {
            title : {
                text: '',
                subtext: ''
            },
            tooltip : {
                trigger: 'axis'
            },
            grid:{
                y:300
            },
            legend: {
                data:title,
                selected: {
                    "流动比率": false,
                    "速动比率": false,
                    "现金比率": false,
                    "权益负债比率": false,
                    "长期资产适合率": false,
                    "流动资产对负债总额比率": false,
                    "有息负债率": false,
                    "不良债权比率": false,
                    "存货流动负债比率（对金融企业无意义）": false,
                    "债务偿付比率": false,
                    "负债结构比率": false,
                    "坏帐备抵率": false,
                    "固定支出成本占总成本的比率": false,
                    "利息支付倍数": false,
                    "固定财务费用保付率": false,
                    "清算价值比率": false,
                    "营运资产与总资产的比率": false,
                    "现金与总资产的比率": false,
                    "净资产收益率": false,
                    "总资产收益率": false,
                    "资本金收益率": false,
                    "主营业务利润率": false,
                    "主营收入毛利润率(金融企业指标名称变化）": false,
                    "主营收入税前利润率(金融企业指标名称变化）": false,
                    "主营收入税后利润率(金融企业指标名称变化）": false,
                    "扣除非经常损益后的净利润率": false,
                    "营业利润率": false,
                    "营业比率": false,
                    "成本费用利润率": false,
                    "销售期间费用率": false,
                    "非经常性损益比率": false,
                    "关联交易比率": false,
                    "本期股利收益率": false,
                    "股利支付比率": false,
                    "收益留存比率": false,
                    "应收帐款周转率": false,
                    "应收帐款回收期(天) （对金融企业无意义）": false,
                    "流动资产周转率": false,
                    "固定资产周转率（对金融企业无意义）": false,
                    "存货周转率（对金融企业无意义）": false,
                    "存货销售期(天) （对金融企业无意义）": false,
                    "总资产周转率(金融企业指标名称变化）": false,
                    "净资产周转率": false,
                    "主营利润比重": false,
                    "流动资产对总资产的比率": false,
                    "资产负债率": false,
                    "资本化比率": false,
                    "资本固定化比率": false,
                    "资本周转率": false,
                    "固定资产与长期负债率": false,
                    "固定资产与股东权益比率": false,
                    "固定资产净值率(%)": false,
                    "权益系数": false,
                    "长期负债比率(6)": false,
                    "产权比率": false,
                    "净值与负债比率": false,
                    "净值与固定资产比率": false,
                    "有形资产净值债务率": false,
                    "股东权益比率": false,
                    "主营业务增长率": false,
                    "应收款项增长率": false,
                    "净利润增长率": false,
                    "固定资产投资扩张率": false,
                    "总资产扩张率": false,
                    "每股收益增长率": false,
                    "净资产增长率(%)": false,
                    "每股经营现金净流量(元)": false,
                    "资产的经营现金流量回报率(%)": false,
                    "净利润现金含量": false,
                    "经营现金净流量对负债的比率": false,
                    "经营活动产生的现金净流量增长率": false,
                    "营业活动收益质量": false,
                    "主营业务现金比率": false,
                    "现金流量结构比率": false,
                    "每股净资产": false,
                    "调整后每股净资产": false,
                    "每股收益": false,
                    "扣除非经常损益的每股收益": false,
                    "每股主营收入": false,
                    "每股经营活动产生的现金流量净额": false,
                    "每股资本公积": false
                }
            },
            toolbox: {
                show : false,
                feature : {
                    mark : {show: true},
                    dataView : {show: true, readOnly: false},
                    magicType : {show: true, type: ['line', 'bar']},
                    restore : {show: true},
                    saveAsImage : {show: true}
                }
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data: dateData
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    axisLabel : {
                        formatter: '{value}'
                    }
                }
            ],
            series : series
        };
        myChart.setOption(option, true);
    }

    return {
        init: function () {
            myChart = echarts.init(document.getElementById('cwblChart'));
            initChart();
            initEvents();
        }
    }
})();

$(function () {
    CwblChartPage.init();
});

