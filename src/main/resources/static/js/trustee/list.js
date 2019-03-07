$(function () {
    searchList();
});

function searchList() {
    $("#tbody").datagrid({
        url:'/trustee/list'
    });
}
