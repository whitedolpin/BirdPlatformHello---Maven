/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.birdtradingplatform.admin.controller;

import com.birdtradingplatform.dao.AccountDAO;
import com.birdtradingplatform.model.Account;
import com.birdtradingplatform.model.Address;
import com.birdtradingplatform.model.UserGoogleDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Minh Quan
 */
@WebServlet(name = "GetDataForAdminProfile", urlPatterns = {"/GetDataForAdminProfile"})
public class GetDataForAdminProfile extends HttpServlet {
    private final String PROFILE_URL = "adminProfile.jsp";
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
            throws ServletException, IOException, ClassNotFoundException, SQLException, NamingException {
        String url = PROFILE_URL;
        try {
            HttpSession session = request.getSession();
            Account adminDTO = (Account) session.getAttribute("dto");
            Account admin = new Account();
            AccountDAO dao = new AccountDAO();
            String gmail = null;
             
           UserGoogleDto ggDTO = (UserGoogleDto) session.getAttribute("GOOGLE_ACC");
           String changeGmail = (String) request.getAttribute("CHANGE_GMAIL");
          
            if (changeGmail != null) {
                gmail = changeGmail;
            }else if (ggDTO != null) {
                gmail = ggDTO.getEmail();
            }else if (adminDTO != null) {
                gmail = adminDTO.getEmail();
            }
            Account account = dao.CheckLoginbyGmail(gmail);
            if (account != null) {
                request.setAttribute("ACCOUNT_EXIST_IN_DB", true);              
            }
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetDataForAdminProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetDataForAdminProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GetDataForAdminProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GetDataForAdminProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GetDataForAdminProfile.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(GetDataForAdminProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
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
