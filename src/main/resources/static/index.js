$(function () {
    searchList();
});

function searchList() {
    $("#table").DataTable({
        ajax:{
            type: 'get',
            url: 'datatable.json',
        },
        columns: [
            { title:'name', data: 'name',
                render: function ( data, type, row, meta ) {
                    return '<a href="'+data+'">'+data+'</a>';
                }},
            { data: 'position' },
            { data: 'salary' },
            { data: 'start_date' },
            { data: 'office' },
            { data: 'extn' }
        ]
    });
    return false;
}
