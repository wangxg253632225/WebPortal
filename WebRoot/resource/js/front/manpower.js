$(function(){
    //查询项目列表
    getDetail();

});

/** 获取文章分类开始 */
function getDetail(){
    $.ajax({
        type: "POST",
        url: _CTX+"/manpower/getDetail",
        dataType:"json",
        async: true,
        success:function(response){
            if(response.code == "0"){
                document.getElementById("manpowerHtml").innerHTML = response.data.content;
            }
        }
    });
}
/** 获取文章分类结束 */