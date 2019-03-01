$(function () {
    searchList();
});

function searchList() {
    $("#tbody").datagrid({
        url:'/user/list'
    });
}
