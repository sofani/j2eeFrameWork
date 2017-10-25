<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="articles">Home</a></li>
            <li><a href="login">Login</a></li>
             <li><a href="new_user">Register</a></li>
             <c:if test="${pageContext.request.userPrincipal.name != null}">
            <li><a	href="javascript:formSubmit()">Log Out</a></li>
            </c:if>
            	
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li role="separator" class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
		     <li><a href="#">Logged as ${pageContext.request.userPrincipal.name}</a></li> 		
	       </c:if>
          </ul>
        </div><!--/.nav-collapse -->
        
      </div>
    </nav>

<br/>
<br/>
<div class="jumbotron">
      <div class="container">
        <h1>Young Eritrean Professionals YEP</h1>
        <p >A non-political, non-religious, and non-ethnic organization of Eritrean Professional residing in the bay area. Aimed at cultivating and maintaining 
        professional networking and information sharing.</p>
        
<form action="https://www.paypal.com/cgi-bin/webscr" method="post" target="_top">
<input type="hidden" name="cmd" value="_donations">
<input type="hidden" name="business" value="XSK8ZFUA3WJDN">
<input type="hidden" name="lc" value="US">
<input type="hidden" name="item_name" value="Habesha-Products">
<input type="hidden" name="currency_code" value="USD">
<input type="hidden" name="bn" value="PP-DonationsBF:btn_donateCC_LG.gif:NonHosted">
<input type="image" src="https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif" border="0" name="submit" alt="PayPal - The safer, easier way to pay online!">
<img alt="" border="0" src="https://www.paypalobjects.com/en_US/i/scr/pixel.gif" width="1" height="1">
</form>
    
    
      </div>
    </div>

    <c:url value="/j_spring_security_logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	
    