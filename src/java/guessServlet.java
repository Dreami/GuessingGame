/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TecMilenio
 */
@WebServlet(urlPatterns = {"/guessServlet"})
public class guessServlet extends HttpServlet {
    private static boolean correctGuess = false;
    private static Integer mysteryNumber;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(mysteryNumber == null || correctGuess) {
            correctGuess = false;
            resetNumber();
            session.setAttribute("mysteryNumber", mysteryNumber);
        }
        int guess;
        try {
            guess = Integer.parseInt(request.getParameter("guess"));
            if(guess != mysteryNumber) {
                session.setAttribute("guess", guess);
                response.sendRedirect("index.jsp");
                
            } else {
                correctGuess = true;
                session.invalidate();
                try (PrintWriter out = response.getWriter()) {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet rpsServlet</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>Felicidades, adivinaste el numero.</h1>");
                    out.println("<img src=\"are-you-wizard.jpg\" alt=\"r-u-a-wizurd\"/></br/>");
                    out.println("<a href='index.jsp'>Regresar al juego</a>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
        } catch (Exception e) {
            try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet rpsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Adivina números enteros por favor.</h1>");
            out.println("</body>");
            out.println("</html>");
            }
        }
    }

    private void resetNumber() {
        mysteryNumber = ThreadLocalRandom.current().nextInt(0, 100 + 1);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
