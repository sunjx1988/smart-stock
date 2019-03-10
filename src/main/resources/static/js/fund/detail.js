var PAGE = (function () {
    var id = null;
    var mode = null;

    function initHtml() {
        id = id || $("#id").val();
        mode = mode || $("#mode").val();

        if(id){
            $.ajax({
                type:"post",
                url: "/fund/" + id,
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

    return {
        init : function () {
            initHtml();
        }
    };
})();

$(function(){
    PAGE.init();
});
