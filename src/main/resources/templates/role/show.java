<!DOCTYPE html>
<html xmlns:layout="http://www.ultraq.net.mx/thymeleaf/layout"
 layout:decorate="~{layouts/main}">
<head>
<title>Fiche d'un role</title>
</head>
<body>
	<div class="container">
	     <div layout:fragment="content">
	        <h1 th:text="${#strings.capitalize(role.role)}">Admin</h1>

	        <nav><a th:href="@{/roles}">Retour à l'index</a></nav>
	     </div>
	</div>
</body>
</html>