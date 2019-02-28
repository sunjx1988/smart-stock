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


$.extend( $.fn.dataTable.defaults, {
    //本地数据搜索开关
    searching:false,
    //分页条数设置开关
    lengthChange:false,
    //排序开关
    ordering:false,
    processing: true,
    serverSide: false,
    ajax:{
      type: 'post'
    },
    //国际化
    language:{
        url: "/plugins/datatable/zh-CN.lang"
    }
} );

