function download(url, data, method){
    if(url){
        var inputs ='';
        if(data){
            data =typeof data =='string'? data : jQuery.param(data);
            jQuery.each(data.split('&'), function(){
                var pair =this.split('=');
                inputs+='<input type="hidden" name="'+ pair[0]+'" value="'+ pair[1]+'" />';
            });
        }

        jQuery('<form action="'+ url +'" method="'+(method||'post')+'">'+(inputs||'')+'</form>')
            .appendTo('body').submit().remove();
    }
}

function getAllMenu() {
    $(".sidebar a").each(function () {
        var url = $(this).attr("href");
        if (url != "#") {
            urls.push(url);
        }
    });
}

var urls = [];
$(function () {
    /**
     * 子菜单 弹开
     *
     *获取路径
     *
     */
    getAllMenu();
    var url = window.location.pathname;
    $("._active").removeClass("active");
    $(".sidebar a").each(function () {
        var _this = $(this);
        if ($.inArray(url, urls) != -1) {
            if (_this.attr("href") == url) {
                _this.parents("li").addClass("active");
                _this.parents("ul").first().addClass("menu-open");
                return false;
            }
        }
    });
});

//重置表格查询条件
function resetSearch(f){
    $(f.form).find("input:text").val("");
    $(f.form).find("input:hidden").val("");
    $(f.form).find("select").val("").trigger("change");
};

//表格
$.fn.datagrid = function (p) {

    this.templateHtml = $(this).data("templateHtml");
    if(!this.templateHtml){
        this.templateHtml = $(this).find("script").html();
        $(this).data("templateHtml",this.templateHtml);
    }
    var _this = this;
    var options = {
        pagination:true,
        pageNo:1,
        pageSize:10,
        page:1,
        currentCount:0
    };
    if(!p){
        p = {};
        options = $(this).data("options");
        p = options;
    }else{
        p.data = p.data?p.data:{};
        options = p?$.extend(options,p):options;
        $(this).data("options",options);
    }

    var postData = function (){
        var formData = options.data;
        if(formData){
            for(var key in formData){
                formData[key] = $.trim(formData[key]);
            }
            $.extend(options,{data:options});
        }
        $.ajax($.extend({
            type: 'POST',
            dataType:'json',
            success:function (data) {
                //列表数据
                var array = [];
                if(options.pagination){
                    array = data.list;
                }else{
                    array = data;
                }
                var html = "";

                _this.children().remove();
                if(null == array || array.length <= 0){
                    var tdLength = _this.prev().children().children().size();
                    html = "<tr><td align='center' colspan='"+tdLength+"'>暂无数据</td></tr>";
                    $(_this).parent().parent().parent().parent().children(".box-footer.dataTables_paginate").empty();
                }else{
                    var render = template.compile(_this.templateHtml);
                    html = render({list:array,page:(options.data.page==null?1:options.data.page),pageSize:(data.pageSize==null?options.pageSize:data.pageSize)});
                }
                _this.append(html);
                //开启分页功能
                if(options.pagination){
                    initPage(data);
                }
                if(p.callback)
                {
                    p.callback(data);
                }
            }
        },options));
    }
    var pagination;
    var initPage = function (opt) {
        if(pagination){
            pagination.off("page");
        }
        var pageDom = $(_this).parent().parent().parent().parent().children(".box-footer.dataTables_paginate");
        pagination = pageDom.bootpag({
            total: opt.pages,
            page: opt.pageNum,
            maxVisible: 10,
            firstLastUse: true,
            prev: '上一页',
            next: '下一页',
            first: '首页',
            last: '末页',
            leaps: true
        }).on("page", function (event, num) {
            $('#allChk').attr('checked',false);
        });

        pagination.on("page", function(event, num){
            var opt = $(_this).data("options");
            if(opt){
                options = opt;
            }
            $.extend(options.data,{
                page:num,
                row:options.pageSize
            });
            $(_this).data("options",options);
            postData();
        });
    }
    postData();
}

