var ChartPage = (function(){
    var data;
    var dateData = [];
    var title = [
        "营业收入",
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
    var chartData = [];
    var series = [];

    function initChart() {
        $.ajax({
            url: '/stock_finance/list',
            type: 'POST',
            data:{
                paramStockId : $("#id").val()
            },
            success: function (ret) {
                data = ret.data;
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
                                chartData[j].push(num);
                            }
                        }
                    }

                    for(var i in chartData){
                        series.push({
                            name: title[i],
                            type:'line',
                            data: chartData[i]
                        });
                    }
                    console.log(chartData);
                    console.log(series);
                }
                draw();
            }
        })
    }

    function draw() {
        var myChart = echarts.init(document.getElementById('lineChart'));
        var option = {
            title : {
                text: '最新财务指标',
                subtext: ''
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:title
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

        myChart.setOption(option);
    }

    return {
        init: function () {
            initChart();
        }
    }
})();

$(function () {
    ChartPage.init();
});

