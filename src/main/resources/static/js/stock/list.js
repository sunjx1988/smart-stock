$(function () {
    searchList();
});

function searchList() {
    $("#tbody").datagrid({
        url:'/stock/list'
    });
}
