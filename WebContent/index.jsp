<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de Investimentos</title>
<link rel="stylesheet" href="css/bootstrap-theme.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="fontawesome/css/all.css">

</head>
<body class="container bg-light">

	<h1 class="text-center">Registro de Investimentos</h1>

	<!-- MENSAGENS DE CONFIRMACAO DELETE -->
	<c:if test="${deleteSuccess eq true}">
		<div class="alert alert-success" role="alert">Investimento
			excluído com sucesso!</div>
	</c:if>
	<c:if test="${deleteSuccess eq false}">
		<div class="alert alert-danger" role="alert">Falha ao excluir o
			investimento!</div>
	</c:if>
	
	<!-- MENSAGENS DE CONFIRMACAO SALVAR -->
	<c:if test="${saveSuccess eq true}">
		<div class="alert alert-success" role="alert">Investimento
			adicionado com sucesso!</div>
	</c:if>
	<c:if test="${saveSuccess eq false}">
		<div class="alert alert-danger" role="alert">Falha ao adicionar
			o novo investimento.</div>
	</c:if>

	<form class="form" action="registrarAcao" method="post">

		<div class="form-group">
			<label for="valor">Código do Investimento ou Ação</label> <input
				id="codigo" name="codigo" type="text" class="form-control" maxlength="10" required
				autofocus />
		</div>

		<div class="form-group">
			<label for="data">Data</label> <input type="date" id="data"
				name="data" class="form-control" />
		</div>

		<div class="form-group">
			<label for="quantidade">Quantidade</label> <input type="number"
				min="1" step="1" id="quantidade" name="quantidade"
				class="form-control" value="1" required />
		</div>

		<div class="form-group">
			<label for="valor">Valor</label> <input id="valor" name="valor"
				type="number" class="form-control" min="0.01" step="0.01"
				value="0.0" required />
		</div>

		<button class="btn btn-dark" type="submit">Adicionar</button>
	</form>

	<br>
	<br>

	<table class="table table-striped table-bordered">
		<thead class="thead-dark text-center">
			<tr>
				<th scope="col">ID</th>
				<th scope="col">CÓDIGO</th>
				<th scope="col">DATA</th>
				<th scope="col">QUANTIDADE</th>
				<th scope="col">VALOR</th>
				<th scope="col">VOLUME</th>
				<th scope="col"></th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${investimentos}" var="inv">
				<tr>
					<td><c:out value="${inv.id}"></c:out></td>
					<td><c:out value="${fn:toUpperCase(inv.codigo)}"></c:out></td>
					<td><fmt:formatDate pattern="dd/MM/yyyy" value="${inv.data}"></fmt:formatDate></td>
					<td><c:out value="${inv.quantidade}"></c:out></td>
					<td>R$<c:out value="${inv.valor}"></c:out></td>
					<td>R$<c:out value="${inv.valor * inv.quantidade}"></c:out></td>
					<td class="text-center"><i class="fas fa-edit mr-3"> </i><a
						href="registrarAcao?action=delete&idAcao=${inv.id}"><i
							class="fas fa-times"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>

		<tfoot>
		</tfoot>
	</table>


</body>
</html>