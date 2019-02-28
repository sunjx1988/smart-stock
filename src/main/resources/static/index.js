$(function () {
    searchList();
});

function searchList() {
    $("#tbody").datagrid({
        url:'/user/list',
        data: {

        }
    });
    return false;
}
