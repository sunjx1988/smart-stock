var ZxcwzbChartPage = (function(){
    var data;
    var title = [
        "净利润（元)",
        "利润总额（元)",
        "扣除非经常性损益后的净利润（元)",
        "总资产（元)",
        "股东权益（元)",
        "经营活动产生的现金流量净额（元)",
        "基本每股收益（元)",
        "净资产收益率（摊薄)(%)",
        "每股经营活动产生的现金流量净额（元)",
        "每股净资产（元)",
        "调整后每股净资产（元)",
        "境外会计准则净利润（元)",
        "扣除非经常性损益后的每股收益(元)"
    ];
    var dateData;
    var chartData;
    var series;
    var myChart;

    function getParam() {
        var param = $("#zxcwzbForm").serializeObject();
        param.paramStockId = $("#id").val();
        return param;
    }

    function initEvents() {
        $("#zxcwzbSearchBtn").on("click", function () {
            initChart();
        });

        $("#zxcwzbForm #paramStartYear").selecter({
            url: "/stock_finance/date_options",
            param:{
                stockId :$("#id").val()
            }
        });

        $("#zxcwzbForm #paramEndYear").selecter({
            url: "/stock_finance/date_options",
            param:{
                stockId :$("#id").val()
            }
        });

        $("#zxcwzbForm #paramDateType").selecter({
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
                y:100
            },
            legend: {
                data:title,
                selected: {
                    "净利润（元)": false,
                    "利润总额（元)": false,
                    "扣除非经常性损益后的净利润（元)": false,
                    "总资产（元)": false,
                    "股东权益（元)": false,
                    "经营活动产生的现金流量净额（元)": false,
                    "基本每股收益（元)": true,
                    "净资产收益率（摊薄)(%)": false,
                    "每股经营活动产生的现金流量净额（元)": true,
                    "每股净资产（元)": true,
                    "调整后每股净资产（元)": false,
                    "境外会计准则净利润（元)": false,
                    "扣除非经常性损益后的每股收益(元)": false
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
            myChart = echarts.init(document.getElementById('zxcwzbChart'));
            initChart();
            initEvents();
        }
    }
})();

$(function () {
    ZxcwzbChartPage.init();
});

