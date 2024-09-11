//package com.pushpak.srv;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.MultipartConfig;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.Part;
//
//import com.pushpak.beans.UserBean;
//import com.pushpak.service.impl.UserServiceImpl;
//
///**
// * Servlet implementation class RegisterSrv
// */
//@WebServlet("/RegisterSrv")
//@MultipartConfig(maxFileSize = 16177215)
//public class RegisterSrv extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		response.setContentType("text/html");
//		String userName = request.getParameter("username");
//		Long mobileNo = Long.parseLong(request.getParameter("mobile"));
//		String emailId = request.getParameter("email");
//		String address = request.getParameter("address");
//		int pinCode = Integer.parseInt(request.getParameter("pincode"));
//		String password = request.getParameter("password");
//		String confirmPassword = request.getParameter("confirmPassword");
//		String status = "";
//		
//		
//		Part part = request.getPart("image");
//
//		InputStream inputStream = part.getInputStream();
//
//		InputStream profile_photo = inputStream;
//		
//		if (password != null && password.equals(confirmPassword)) {
//			UserBean user = new UserBean(userName, mobileNo, emailId, address, pinCode, password,profile_photo);
//
//			UserServiceImpl dao = new UserServiceImpl();
//
//			status = dao.registerUser(user);
//		} else {
//			status = "Password not matching!";
//		}
//
//		RequestDispatcher rd = request.getRequestDispatcher("register.jsp?message=" + status);
//
//		rd.forward(request, response);
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//
//		doGet(request, response);
//	}
//
//}






package com.pushpak.srv;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.pushpak.beans.UserBean;
import com.pushpak.service.impl.UserServiceImpl;

@WebServlet("/RegisterSrv")
@MultipartConfig(maxFileSize = 16177215) // limit upload file size to ~16MB
public class RegisterSrv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        // Retrieve form data
        String userName = request.getParameter("username");
        String mobileStr = request.getParameter("mobile");
        String emailId = request.getParameter("email");
        String address = request.getParameter("address");
        String pinCodeStr = request.getParameter("pincode");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String status ="Registration Successful";

        // Validate mobile number and pin code
        Long mobileNo;
        int pinCode;
        try {
            mobileNo = Long.parseLong(mobileStr);
            pinCode = Integer.parseInt(pinCodeStr);
        } catch (NumberFormatException e) {
            status = "Invalid number format for mobile or pin code!";
            request.setAttribute("message", status);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
            return;
        }

        // Validate passwords
        if (password == null || !password.equals(confirmPassword)) {
            status = "Password not matching!";
            request.setAttribute("message", status);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
            return;
        }

        // Process profile image
        Part part = request.getPart("image");
        InputStream profilePhoto = null;
        if (part != null && part.getSize() > 0) {
            profilePhoto = part.getInputStream();
        } else {
            status = "Profile image is required!";
            request.setAttribute("message", status);
            RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
            rd.forward(request, response);
            return;
        }

        // Create UserBean and register user
        UserBean user = new UserBean(userName, mobileNo, emailId, address, pinCode, password, profilePhoto);
        UserServiceImpl dao = new UserServiceImpl();
        status = dao.registerUser(user);

        // Clean up resources
        if (profilePhoto != null) {
            try {
                profilePhoto.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Forward status message to JSP
        
        request.setAttribute("message", status);
        RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
