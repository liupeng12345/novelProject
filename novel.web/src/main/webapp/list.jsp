<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css" />
<script type="text/javascript" src="js/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="js/bootstrap.min.js" ></script>
<title>小说章节</title>
<script>

</script>
</head>
<body>
    	<div class="container">
    		 <div class="row">
    		 	 <div class="col-md-offset-4 col-md-offset-4" >
    		 	 	<h1>小说名</h1>
    		 	 </div>
    		 </div>
              <div class="row">
    		 	 <div class="col-md-offset-4 col-md-offset-4" >
    		 	 	<h5>作者：</h5>
    		 	 </div>
    		 </div>
    		 <br>
    		 <br>
    		 <div clas="row">
    		 	<form class="form-inline" action="novel.jsp" method="get" onsubmit="test();" >
    		 		<div class="col-sm-offset-3 col-md-offset-9">
                      <input type="text" class="form-control" id="keyword" name="keyword"/>&nbsp;
                      <input type="submit" class="btn btn-primary" value="搜索" id="btn"/>
    		 		</div>
    		 	</form>
    		 </div>
    		 <br>
    		 <br>
    		 <div  class="row">
                <div class="col-lg-2">
                	<h2>列表：</h2>
                </div>    		 	
    		 </div>
    		 <div class="row" id="chapterlist">
    		 	
    		 </div>
    	</div>
    	<script type="text/javascript">
    	 $(document).ready(function(){
    		 $.ajax({
    				url:"./chapter.do",
    				type:"POST",
    				dataType:"json",
    				data :{
    					"url":"<%=request.getParameter("url")%>"
    				},
    				
    				success: function (data) {
    					var msg="";
    					var ins=eval(data.data);
    					$.each(ins,function(i){
    						msg+= " <div class='col-lg-2'><a href='txt.jsp?url="+ins[i].url+"'>"+ins[i].title+"</a></div> "
    					});
    					$("#chapterlist").html(msg);
    				},
    				error: function() {
    					alert("请求失败！");
    				},
    			}); 
         })
		
        </script>
 
</body>
</html>