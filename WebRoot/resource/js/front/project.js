var array = new Array();
$(function(){

    //查询项目列表
    getList();

});

/** 获取文章分类开始 */
function getList(){
    $.ajax({
        type: "POST",
        url: _CTX+"/project/getList",
        dataType:"json",
        async: true,
        success:function(response){
            if(response.code == "0"){
                array = response.data;
                var strHtml = "";
                for(var i=0;i<response.data.length;i++){
                    strHtml +='<div onclick="setHtml('+response.data[i].id+')" class="project_li" style="background-image: url('+_CTX+'\'/resource/img/ll0'+(i+1)+'.jpg\');">'+response.data[i].name+'</div>'
                    if(i==0){
                        setHtml(response.data[i].id);
                    }
                }
                document.getElementById("projectTop").innerHTML = strHtml;
            }
        }
    });
}
/** 获取文章分类结束 */

function setHtml(id){
    for(var i=0;i<array.length;i++){
        if(array[i].id == id){
            document.getElementById("projetHtml").innerHTML = array[i].content;
        }
    }
}