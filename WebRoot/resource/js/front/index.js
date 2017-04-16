$(function(){

	//调用新闻查询
	getNewsList();

	//调用公司业务查询
	getBusinessList();

    $('#general').mouseenter(function(){
        $('#general_job_head').css("background-image"," url("+_CTX+"/resource/img/general.png)");
        $('#moveGeneralJob').stop().animate({top:0},500)
    });
    $('#job').mouseenter(function(){
        $('#general_job_head').css("background-image"," url("+_CTX+"/resource/img/job.png)");
        $('#moveGeneralJob').stop().animate({top:-220},500)
    });

	//查询联系我们
	getContact();

	//查询人力资源
	getManpower();

});

/** 查询新闻中心8条数据 开始 */
function getNewsList(){
	var param = {
		"pageNum":1,
		"pageSize":8
	};
	$.ajax({
		type: "POST",
		url: _CTX+"/article/list?type=news",
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
						+'    		<a href="'+_CTX+'/article?type=news&id='+list[i].id+'" title="'+list[i].name+'">'+list[i].name+'</a>'
						+'    </div>'
						+'    <span class="date">('+list[i].create_date+')</span>'
						+'</div>'
				}

				document.getElementById('newsList').innerHTML = strHtml;
			}
		}
	});
}
/** 查询新闻中心8条数据 结束 */

/** 查询公司8条数据 开始 */
function getBusinessList(){
	var param = {
		"pageNum":1,
		"pageSize":8
	};
	$.ajax({
		type: "POST",
		url: _CTX+"/article/list?type=business",
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
						+'    		<a href="'+_CTX+'/article?type=business&id='+list[i].id+'" title="'+list[i].name+'">'+list[i].name+'</a>'
						+'    </div>'
						+'    <span class="date">('+list[i].create_date+')</span>'
						+'</div>'
				}

				document.getElementById('businessList').innerHTML = strHtml;
			}
		}
	});
}
/** 查询公司业务8条数据 结束 */

/** 查询公司8条数据 开始 */
function getContact(){
	$.ajax({
		type: "POST",
		url: _CTX+"/contact/detail",
		dataType:"json",
		async: true,
		success:function(response){
			if(response.code == "0"){
				var strHtml = "";

				strHtml +='<div class="contact_cnt">'
							+' <div class="c_left">公司名称：</div>'
					+' <div class="c_right">'+response.data.company_name+'</div>'
					+' </div>'
					+' <div class="contact_cnt">'
					+' <div class="c_left">联系电话：</div>'
					+' <div class="c_right">'+response.data.mobile_phone+'</div>'
					+' </div>'
					+' <div class="contact_cnt">'
					+' <div class="c_left">固定电话：</div>'
					+' <div class="c_right">'+response.data.landline_telephone+'</div>'
					+' </div>'
					+' <div class="contact_cnt">'
					+' <div class="c_left">联系人：</div>'
					+' <div class="c_right">'+response.data.link_man+'</div>'
					+' </div>'
					+' <div class="contact_cnt">'
					+' <div class="c_left">Email：</div>'
					+' <div class="c_right">'+response.data.email+'</div>'
					+' </div>'
					+' <div class="contact_cnt">'
					+' <div class="c_left">公司地址：</div>'
					+' <div class="c_right">'+response.data.address+'</div>'
					+' </div>';
				document.getElementById('contactIndex').innerHTML = strHtml;
			}
		}
	});
}
/** 查询公司业务8条数据 结束 */

function getManpower(){
	$.ajax({
		type: "POST",
		url: _CTX+"/manpower/getDetail",
		dataType:"json",
		async: true,
		success:function(response){
			if(response.code == "0"){
				document.getElementById("manpowerHtml").innerHTML = " &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+response.data.title;
			}
		}
	});
}
