<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de Ações</title>
<link rel="stylesheet" href="css/bootstrap-theme.css">
<link rel="stylesheet" href="css/bootstrap.min.css">

</head>
<body class="container bg-light">

	<h1 class="text-center">Registro de Ações</h1>
	<div id="mensagemView"></div>

	<form class="form" action="registrarAcao" method="post">

		<div class="form-group">
			<label for="valor">Código</label> <input id="codigo" name="codigo" type="text"
				class="form-control" required autofocus/>
		</div>

		<div class="form-group">
			<label for="data">Data</label> <input type="date" id="data" name="data"
				class="form-control" />
		</div>

		<div class="form-group">
			<label for="quantidade">Quantidade</label> <input type="number"
				min="1" step="1" id="quantidade" name="quantidade" class="form-control" value="1"
				required />
		</div>

		<div class="form-group">
			<label for="valor">Valor</label> <input id="valor" name="valor" type="number"
				class="form-control" min="0.01" step="0.01" value="0.0" required />
		</div>

		<button class="btn btn-dark" type="submit">Adicionar</button>
	</form>
	
	<br>
    <br>

    <table class="table table-striped table-bordered">
        <thead class="thead-dark text-center">
            <tr>
            	<th scope="col">CÓDIGO</th>
                <th scope="col">DATA</th>
                <th scope="col">QUANTIDADE</th>
                <th scope="col">VALOR</th>
                <th scope="col">VOLUME</th>
                <th scope="col">REMOVER / EDITAR</th>
            </tr>
        </thead>
        
        <tbody>
        </tbody>
        
        <tfoot>
        </tfoot>
    </table>
    
    
</body>
</html>