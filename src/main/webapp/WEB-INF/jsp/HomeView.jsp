<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<style>
.error {
	color: #ff0000;
}

.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
	<h1>Spring MVC Hello World Example</h1>

	<h2>Hello World</h2>

	<form:form method="POST" commandName="SudokuWeb" modelAttribute="sudokuWebForm">
	<table>
			<c:forEach begin="0" end="8" var="row">
			<tr>
			    <c:forEach begin="0" end="8" var="column">
			        <td>
                    <form:input path="matrix[${row}][${column}]" size="1"/>
		            <form:errors path="matrix[${row}][${column}]" cssClass="errorblock" element="div" />
                    </td>
                </c:forEach>
            </tr>
            </c:forEach>
        </table>

        <table>
			<tr>
				<td colspan="3">
				    <input type="submit" />
				</td>
			</tr>
		</table>
	</form:form>

</body>
</html>