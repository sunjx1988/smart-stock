var PAGE = (function () {
    var id = null;
    var mode = null;

    function initHtml() {
        id = id || $("#id").val();
        mode = mode || $("#mode").val();

        if(id){
            $.ajax({
                type:"post",
                url: "/fund/" + id,
                success: function (result) {
                    initHtmlData(result.data);
                }
            })
        }else{
            initHtmlData();
        }

        $("#tradeStockBtn").on("click", function () {
            $("#tradeStockModal").modal("show");
        });

        $("#tradeStockCommitBtn").on("click", function () {
            $.ajax({
                url: '/stocktrade/save',
                type: 'POST',
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify($("#tradeStockForm").serializeObject()),
                success: function (ret) {
                    if(ret.code != '0000'){
                        message(ret.msg);
                    }else{
                        location.href = "/stocktrade"
                    }
                }
            })
        });

        $("#trusteeTradeBtn").on("click", function () {
            $("#trusteeTradeModal").modal("show");
        });

        $("#trusteeTradeCommitBtn").on("click", function () {
            $.ajax({
                url: '/trusteetrade/save',
                type: 'POST',
                contentType: 'application/json;charset=UTF-8',
                data: JSON.stringify($("#trusteeTradeForm").serializeObject()),
                success: function (ret) {
                    if(ret.code != '0000'){
                        message(ret.msg);
                    }else{
                        location.href = "/trusteetrade"
                    }
                }
            })
        });
    }

    function initHtmlData(data) {
        $("#form").append(template('tpl', data));
        $("#fundId").selecter({
           url: '/fund/options'
        });
        $("#code").selecter({
           url: '/stock/options'
        });
        $("#trusteeId").selecter({
           url: '/trustee/options'
        });
        $("#interestRate").selecter({
           url : '/trusteetrade/interestrate/options'
        });
    }

    return {
        init : function () {
            initHtml();
        }
    };
})();

$(function(){
    PAGE.init();
});
