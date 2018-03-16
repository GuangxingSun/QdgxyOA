$(document).ready(function(){
		$("select[name='grade']").change(function() {
			var grade = $("select[name='grade']").val();
		    var major = $("select[name='major']").val();
		    if (grade != "" && major != "") {
		    	AjaxClazz(grade, major);
		    }
		});
		$("select[name='major']").change(function() {
			var grade = $("select[name='grade']").val();
		    var major = $("select[name='major']").val();
		    if (grade != "" && major != "") {
		    	AjaxClazz(grade, major);
		    }
		});
		function AjaxClazz(grade, major) {
			$.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/clazz_jsonClazz.do",
                data:{//设置数据源
                    grade:grade,
                    major:major
                },
                dataType:"json",//设置需要返回的数据类型
                success:function(data){
                    //var d = eval("("+data+")");//将数据转换成json类型
                    $("select[name='clazzIds']").empty();
                    $.each(data, function(i, value) {
                    	$("select[name='clazzIds']").append(//
                    			"<option value='"+value.id+"'>"+value.grade+"级 "+value.major+"专业 "+value.clazz+"班 </option>");
                    });
                },
                error:function(){
                    alert("系统异常，无法进行筛选！");
                }//这里不要加","
            });
		}
	});