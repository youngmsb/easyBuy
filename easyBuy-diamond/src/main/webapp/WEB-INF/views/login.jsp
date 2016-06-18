<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>SuperDiamond 配置管理服务器</title>
    <link href='<c:url value="/resources/css/bootstrap/css/bootstrap.css" />' rel="stylesheet" >
    <style type="text/css">
      /* Override some defaults */
      html, body {
        background-color: #eee;
      }
      body {
        padding-top: 40px;
      }
      .container {
        width: 300px;
      }

      /* The white background content wrapper */
      .container > .content {
        background-color: #fff;
        padding: 20px;
        margin: 0 -20px;
        -webkit-border-radius: 10px 10px 10px 10px;
           -moz-border-radius: 10px 10px 10px 10px;
                border-radius: 10px 10px 10px 10px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.15);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.15);
                box-shadow: 0 1px 2px rgba(0,0,0,.15);
      }

    .login-form {
      margin-left: 65px;
    }

    legend {
      margin-right: -50px;
      font-weight: bold;
      color: #404040;
    }

    </style>

</head>
<body>
  <div class="container">
      <div class="content">
          <div class="row">
              <div class="login-form">
                  <img src='<c:url value="/resources/css/bootstrap/img/diamond.png"/>' class="diamond"/>
                  <form action='<c:url value="/login.do"><c:param name="method" value="login"/></c:url>' method="post" autocomplete="off" >
                      <fieldset>
                      	  <c:if test="${sessionScope.message != null}">
	                      	  <div class="alert alert-error clearfix" style="margin-bottom: 5px;width: 195px; padding: 2px 15px 2px 10px;">
	  							${sessionScope.message}
							  </div>
						  </c:if>
                          <div class="clearfix">
                              <input type="text" placeholder="用户名" name="username" value="${sessionScope.userCode}" >
                          </div>
                          <div class="clearfix">
                              <input type="password" placeholder="密码" name="password">
                          </div>
                          <div class="btnGroup">
                              <button class="btn btn-primary" type="submit">登 录</button>
                              <button class="btn" type="reset">重 置</button>
                          </div>
                      </fieldset>
                  </form>
              </div>
          </div>
      </div>
  </div> <!-- /container -->
</body>
</html>