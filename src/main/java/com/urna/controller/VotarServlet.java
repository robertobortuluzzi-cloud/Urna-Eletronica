package com.urna.controller;

import com.urna.service.UrnaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/votar")
public class VotarServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroStr = request.getParameter("numero");

        if (numeroStr == null || numeroStr.trim().isEmpty()) {
            response.sendRedirect("index.jsp");
            return;
        }

        try {
            int numero = Integer.parseInt(numeroStr.trim());
            UrnaService urna = (UrnaService) getServletContext().getAttribute("urnaService");

            if (urna != null) {
                urna.registrarVoto(numero);
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("index.jsp");
            return;
        }

        response.sendRedirect("index.jsp");
    }
}