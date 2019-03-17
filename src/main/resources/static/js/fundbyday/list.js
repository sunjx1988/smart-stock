var FundByDayPage = (function(){
    var data;
    var dateData = [];
    var positionData = [];
    var netValueData = [];
    var returnRateData = [];

    function initChart() {
        $.ajax({
            url: '/fund_by_day/list',
            type: 'POST',
            data:{
                fundId : $("#id").val()
            },
            success: function (ret) {
                data = ret.list;
                if(data){
                    for(var i in data){
                        dateData.push(data[i].date);
                        positionData.push(data[i].position * 100);
                        netValueData.push(data[i].netUnitValue);
                        returnRateData.push(data[i].rateOfReturn * 100);
                    }
                }
                draw();
            }
        })
    }

    function draw() {
        var myChart = echarts.init(document.getElementById('lineChart'));
        var option = {
            title : {
                text: '仓位 / 净值 / 回报率',
                subtext: ''
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['仓位','净值(元)','回报率']
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
                    // data : ['周一','周二','周三','周四','周五','周六','周日']
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
            series : [
                {
                    name:'仓位',
                    type:'line',
                    // data:[11, 11, 15, 13, 12, 13, 10],
                    data: positionData,
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
                },
                {
                    name:'净值(元)',
                    type:'line',
                    // data:[1, -2, 2, 5, 3, 2, 0],
                    data: netValueData,
                    markPoint : {
                        data : [
                            // {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5},
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name : '平均值'}
                        ]
                    }
                },
                {
                    name:'回报率',
                    type:'line',
                    // data:[1, -2, 2, 5, 3, 2, 0],
                    data: returnRateData,
                    markPoint : {
                        data : [
                            // {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                            {type : 'max', name: '最大值'},
                            {type : 'min', name: '最小值'}
                        ]
                    },
                    markLine : {
                        data : [
                            {type : 'average', name : '平均值'}
                        ]
                    }
                }
            ]
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
    FundByDayPage.init();
});

