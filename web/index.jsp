<%-- 
    Document   : index
    Created on : 24/01/2018, 04:44:54 PM
    Author     : TecMilenio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String output = null;
            if(request.getAttribute("correctGuess") == null) {
            output = "Estoy pensando en un número...";
        } else if(!((boolean) request.getAttribute("correctGuess")) &&
                request.getAttribute("hl") != null) {
            String hl = request.getAttribute("hl").toString();
            output = "No es esa, intenta un numero más " + hl;
        } else if((boolean) request.getAttribute("correctGuess")) {
            output = "Es correcto! Ahora adivina el siguiente número.";
        }
        %>
        <h1><%=output%></h1>
        <div>
            <form name='guessForm' method='GET' action='guessServlet'>
               <input name='guess'><button type='submit'>Adivinar</button>
            </form>
        </div>
    </body>
</html>
