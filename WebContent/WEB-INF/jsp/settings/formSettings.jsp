<%@include file="../../../admin/header.jsp" %>

<c:if test="${retorno == true}">

	<p>Configuracao ${tipoSubmit} com sucesso!</p>

</c:if>
<c:if test="${retorno == false}">

	<p>ERRO! Configuracao n�o foi ${tipoSubmit}.</p>

</c:if>

	<div id="dialog-confirm">
	   <p>Voc� realmente deseja deletar essa configuracao?</p>
	</div>
	
<form action="<c:url value="/adm/settings" />" method="post" id="formSettings">
	<table>
		<c:forEach var="config" items="${configs}">
			<tr>
				<td>${config.name}: </td>
				<td><input name="${config.slug}" id="${config.slug}" value="${config.value}" /></td>
			</tr>
		</c:forEach>
		<tr>
			<td>Template Ativo: </td>
			<td>
				<select name="current-template">
					<c:forEach var="template" items="${templates}">
						<option <c:if test="${template.name eq currentTemplate}">selected="selected"</c:if> value="${template.name}">${template.name}</option>
					</c:forEach>				
				</select>
			</td>
		</tr>	
		<tr>
			<td>
				<button name="_method" class="submit" value="put">Editar</button>
			</td>
		</tr>
							
	</table>

</form>

<%@include file="../../../admin/footer.jsp" %>