//点击验证码图片，重新加载验证码
function getVerifycode()
{
	$("#img").attr("src", getRootPath_web() + '/verificationCodeController/verifyCode?now=' + Math.random());
}

$(document).ready(function()
{
	//alert(getRootPath_web());
    var curWwwPath = window.document.location.href;
    console.log(curWwwPath);
    // 获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    console.log(pathName);
    var pos = curWwwPath.lastIndexOf(pathName);
    console.log("____")
    console.log(pos);
    var localhostPaht = curWwwPath.substring(0, pos);
    console.log(localhostPaht);
    // 获取主机地址，如： http://localhost:8083
    var indexC = pathName.substr(1).indexOf('/') + 1;
    console.log(indexC);




	$("#img").attr("src", getRootPath_web() + '/verificationCodeController/verifyCode');

	$("#form").validate(
	{
		submitHandler : function(form)
		{
			var verifycode = $("#verifycode").val();
			if (verifycode.length < 4)
			{
				$("#message").html("验证码长度应为4位!");
			}
			else
			{
				var pwd = $("#pwd").val();
				$('#pwd').val(sha256_digest(pwd));
				$('#form').ajaxSubmit(
				{
				    url : getRootPath_web() + '/loginController/login',
				    success : function(data)
				    {
					    if (data.success)
					    {
						    window.location.href = getRootPath_web() + "/index";
					    }
					    else
					    {
						    $("#message").html(data.msg);
						    $('#pwd').val("");
						    getVerifycode();
						    captchaObj.reset();
					    }
				    }
				});
			}
		}
	});
});