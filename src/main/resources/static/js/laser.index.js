$.ajax(
{
    type : "get",
    url : getRootPath_web() + "/resourceController/tree",
    async : true,
    dataType : "json",
    success : function(data)
    {
	    var rootul = $("<ul class='nav'></ul>");
	    for (var i = 0; i < data.length; i++)
	    {
		    if (!data[i].pid)
		    {
			    var item = data[i];
			    var rootli = $('<li></li>');
			    var roota = $('<a data-toggle="collapse" data-root="true"><i class="pe-7s-note2"></i></a>');
			    var rootp = $("<p></p>");
			    if (data[i].url != "" && data[i].url != null && data[i].url != undefined)
			    {
				    roota.attr("href", data[i].url);
			    }
			    rootp.append(data[i].text);
			    roota.append(rootp);
			    rootli.append(roota);
			    rootul.append(rootli);
			    getChildNodes(data, item, rootli, roota, rootp);
		    }
	    }
	    $("#menu").html(rootul);
    },
    error : function(data)
    {
	    eval(data.responseText);
    }
});

function getChildNodes(data, item, li, a, p)
{
	var subdiv = $('<div class="collapse"   id=' + item.id + '></div>');
	var subul = $('<ul class="nav"></ul>');
	subdiv.append(subul);
	var flag = false;
	for (var i = 0; i < data.length; i++)
	{
		if (item.id == data[i].pid)
		{
			var subli = $("<li></li>");
			a.attr("href", "#" + item.id);
			subli.append("<a onclick=hrefPage(this,'" + data[i].attributes.url + "?menu=true')  >" + data[i].text + "</a>");
			subul.append(subli);
			flag = true;
		}

	}
	if (flag)
	{
		p.append('<b class="caret"></b>');
		li.append(subdiv);
	}
}

function hrefPage(a, url)
{
	var iframe = $("#main");
	if (iframe.attr("src") != getRootPath_web() + url)
	{
		var lis = $("#menu").find("li");
		for (var i = 0; i < lis.length; i++)
		{
			$(lis[i]).removeClass("active");
		}
		$(a).parent().addClass("active");

		var ul_breadcrumb = $("#breadcrumb");
		if ($(a).text())
		{
			ul_breadcrumb.find("li").not(":first").remove();
			ul_breadcrumb.append("<li><a href='#'><span class='icon icon-beaker'> </span>" + $(a).parents("li").find("a[data-root='true']").text() + "</a></li>");
			ul_breadcrumb.append("<li><a href='#'><span class='icon icon-beaker'> </span>" + $(a).text() + "</a></li>");
		}
		else
		{
			ul_breadcrumb.find("li").not(":first").remove();
			console.log("点击的是首页");
		}

		iframe.attr("src", getRootPath_web() + url);

		$("html").removeClass("nav-open");
		$('.close-layer').remove();
		$toggle = $('.navbar-toggle').removeClass('toggled');
		$("body").mLoading();
		if (!iframe.readyState || iframe.readyState == "complete")
		{
			iframe.on("load", function()
			{
				$("body").mLoading("hide");
			});
		}
	}
}

function logout()
{
	swal(
	{
	    title : "你确定要退出系统吗？",
	    type : "warning",
	    showCancelButton : true,
	    confirmButtonText : "确定",
	    cancelButtonText : "取消",
	    allowOutsideClick : false
	}, function(isConfirm)
	{
		if (isConfirm)
		{
			$.post(getRootPath_web() + "/loginController/logout");
			window.location.href = getRootPath_web();
		}
	});
}

function updatepwd()
{
	$.ajax(
	{
	    url : getRootPath_web() + '/adminController/editCurrentUserPwdPage',
	    type : 'post',
	    cache : false,
	    // 超时时间设置，单位毫秒
	    success : function(data)
	    {
		    parent.BootstrapDialog.show(
		    {
		        id : 'userUpdatePwd',
		        title : '修改密码',
		        message : $("<div></div>").html(data),
		        type : BootstrapDialog.TYPE_PRIMARY, // Default value is
		        closable : false,
		        buttons : [
		        {
		            label : '添加',
		            icon : ' glyphicon glyphicon-ok',
		            cssClass : 'btn-success',
		            action : function(dialog)
		            {
			            parent.BootstrapDialog.dialog = dialog;
			            dialog.getModalBody().find("form").submit();
		            }
		        },
		        {
		            id : 'cancel',
		            label : '取消',
		            icon : 'glyphicon glyphicon-remove',
		            cssClass : 'btn-danger',
		            action : function(dialog)
		            {
			            dialog.close();
		            }
		        } ],
		    });

	    },
	    error : function(data)
	    {
		    ajaxDialogError(data);
	    }
	});
}
