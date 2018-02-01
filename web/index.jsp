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
        <% 
        HttpSession s = request.getSession();    
        String output = "Estoy pensando en un número...";
        
        Integer mysteryNumber = (Integer) s.getAttribute("mysteryNumber");
            if(mysteryNumber != null) {
                String hl = "";
                int guess = (int) s.getAttribute("guess");
                
                if (guess < mysteryNumber)
                    hl = "alto";
                else if (guess > mysteryNumber)
                    hl = "bajo";
                
                output = "No es esa, intenta un numero más " + hl;
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
