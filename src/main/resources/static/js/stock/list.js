$(function () {
    searchList();
});

function searchList() {
    $("#tbody").datagrid({
        url:'/stock/list'
    });
}

function fetchAllFinanceInfo() {
    comfirm("确认全量更新财务数据?" ,function () {
        $.ajax({
            url: '/stock_finance/admin/finance_fetch_all',
            type: 'POST',
            success: function (ret) {
                if(ret.code != '0000'){
                    message(ret.msg);
                }
            }
        })
    })
}
