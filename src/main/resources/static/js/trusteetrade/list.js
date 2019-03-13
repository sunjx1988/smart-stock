$(function () {
    searchList();
});

function searchList() {
    $("#tbody").datagrid({
        url:'/trusteetrade/list'
    });
}

function confirmTrade(id) {
    comfirm("确认交易?",function () {
        $.ajax({
            url: "/trusteetrade/confirm/" + id,
            type: "POST",
            success: function (ret) {
                if(ret.code != '0000'){
                    message(ret.msg);
                }else{
                    location.href = "/trusteetrade";
                }
            }
        })
    })
}
