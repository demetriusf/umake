<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${!allowedAccess}">Acesso proibido</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta name="description" content="${siteDescription}" />
		<meta name="keywords" content="${siteKeywords}" />
		<meta name="reply-to" content="${siteEmail}" />
		
		<c:forEach items="${cssFiles}" var="css">		
		
			<link rel="stylesheet" type="text/css" href="<c:url value="${templateRoot}/${css.fileName}" />" media="${css.media}" />			
 
 		</c:forEach>
		
		<title>${currentPage.title} | ${siteTitle}</title>
	</head>
	
	<body>
	    <section id="content">
	
	        <header>
	
	            <img src="<c:url value="${templateRoot}/templateImg.png" />  " width="1000" height="250"/>
	
	        </header>
	
	        <section>
	
	            <aside>
	
	                <nav>
						${menu}	
	                </nav>
	
	            </aside>
	
	            <article>
	
	                <h1 style="font-size:40px;">${currentPage.title}</h1>
	                ${currentPage}
	            
	            </article>
	
	        </section>
	
	        <footer>
	
	                <nav>
	
	                    <ul>
	
	                        <li><a href="#">home</a></li>
	                        <li><a href="#">quem somos</a></li>
	                        <li><a href="#">fale conosco</a></li>
	                        <li><a href="#">cadastro</a></li>
	                        <li><a href="#">portifolio</a></li>
	
	                    </ul>
	
	                </nav>
	
	                <a id="poweredUmake" href="#">Powered by UMAKE</a>
	
	        </footer>
	
	    </section>	
	
	</body>

</html>