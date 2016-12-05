$(function(){
	/** 查询新闻中心8条数据 开始 */
	function getNewsList(){
		var param = {
			"pageNum":1,
			"pageSize":8
		};
		$.ajax({
		 	type: "POST",
		 	url: "/article/list?type=news",
			data: JSON.stringify(param),
			dataType:"json",
			async: true,
			success:function(response){
				if(response.code == "0"){
					var list = response.data;
					var length = list.length;
					var strHtml = "";
					for(var i=0;i<length;i++){
						strHtml +='<div class="line">'
								 +'    <div>'
								 +'    		<a href="#" title="'+list[i].name+'">'+list[i].name+'</a>'
								 +'    </div>'
								 +'    <span class="date">('+list[i].create_date+')</span>'
								 +'</div>'
					}
					
					document.getElementById('newsList').innerHTML = strHtml;
					
				}
			}
		});
	}
	//调用新闻查询
	getNewsList();
	/** 查询新闻中心8条数据 结束 */
});
