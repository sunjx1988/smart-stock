$(function () {
    searchList();
});

function searchList() {
    $("#tbody").datagrid({
        url:'/stocktrade/list'
    });
}
