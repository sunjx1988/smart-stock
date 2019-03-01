$(function () {
    searchList();

    $("#resetBtn").on("click",function () {
       $(this).resetSearch();
    });
});

function searchList() {
    $("#tbody").datagrid({
        url:'/user/list',
        data: {

        }
    });
    return false;
}
