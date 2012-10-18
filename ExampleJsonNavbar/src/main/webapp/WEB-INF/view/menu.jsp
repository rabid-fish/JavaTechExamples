<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>${title}</title>
	<style type="text/css">
		
		li { list-style-type: none; }
		li.depth_0 { margin-left:  0px; }
		li.depth_1 { margin-left: 20px; }
		li.depth_2 { margin-left: 40px; }
		li.depth_3 { margin-left: 60px; }
		
	</style>
</head>
<body>
	
	<h3>Breadcrumb</h3>
	
	<div class="">
		<c:forEach items="${list}" var="menuItem">
			<span>${menuItem.name}</span> &gt;
		</c:forEach>
		<span>${name}</span>
	</div>
	
	
	<h3>Index</h3>
	
	<div>
		<ul>
			<c:forEach items="${menuItems}" var="menuItem">
				<li class="depth_${menuItem.depth}"><a href="${menuItem.url}">${menuItem.name}</a></li>
			</c:forEach>
		</ul>
	</div>

</body>
</html>
