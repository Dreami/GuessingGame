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
        if(mysteryNumber == null || correctGuess) {
            resetNumber();
        }
        
        int guess;
        
        try {
            guess = Integer.parseInt(request.getParameter("guess"));
            if(guess != mysteryNumber) {
                correctGuess = false;
                request.setAttribute("correctGuess", correctGuess);
                if(guess < mysteryNumber) {
                    request.setAttribute("hl", "alto");
                } else if (guess > mysteryNumber) {
                    request.setAttribute("hl", "bajo");
                }
            } else {
                correctGuess = true;
                request.setAttribute("correctGuess", correctGuess);
            }
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
