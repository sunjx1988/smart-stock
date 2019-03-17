var TrusteeByDayPage = (function(){
    var data;
    var dateData = [];
    var principalData = [];
    var totalData = [];
    var incomeData = [];

    function initChart() {
        $.ajax({
            url: '/trustee_by_day/list',
            type: 'POST',
            data:{
                trusteeId : $("#id").val()
            },
            success: function (ret) {
                data = ret.list;
                if(data){
                    for(var i in data){
                        dateData.push(data[i].date);
                        principalData.push(data[i].principal);
                        totalData.push(data[i].total);
                        incomeData.push(data[i].income);
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
                text: '本金 / 资产 / 收益',
                subtext: ''
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['本金','资产','收益']
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
            series : [
                {
                    name:'本金',
                    type:'line',
                    data: principalData,
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
                    name:'资产',
                    type:'line',
                    data: totalData,
                    markPoint : {
                        data : [
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
                    name:'收益',
                    type:'line',
                    data: incomeData,
                    markPoint : {
                        data : [
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
    TrusteeByDayPage.init();
});

