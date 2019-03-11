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
    }

    function initHtmlData(data) {
        $("#form").append(template('tpl', data));
        $("#fundId").selecter({
            url: '/fund/options'
        })
        $("#code").selecter({
            url: '/stock/options'
        })
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
