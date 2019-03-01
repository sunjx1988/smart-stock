var PAGE = (function () {
    var id = null;
    var mode = "insert";

    function initHtml() {
        id = id || $("#id").val();
        mode = mode || $("#mode").val();

        if(id){
            $.ajax({
                type:"post",
                url: "/user/" + id,
                success: function (result) {
                    initHtmlData(result.data);
                }
            })
        }else{
            initHtmlData();
        }
    }

    function initHtmlData(data) {
        $("#form").append(template('tpl', data));
    }

    function getFormData() {
        return JSON.stringify($("#form").serializeObject());
    }

    function initEvent() {
        $("#saveBtn").on("click", function () {
            $.ajax({
                type: "post",
                contentType: 'application/json;charset=UTF-8',
                url: "/user/save",
                data: getFormData(),
                success: function (result) {
                    message(result.msg);
                }
            })
        })
    }

    return {
        init : function () {
            initHtml();
            initEvent();
        }
    };
})();

$(function(){
    PAGE.init();
});
