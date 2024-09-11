package com.pushpak.srv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pushpak.service.impl.UserServiceImpl;

@WebServlet("/ShowUserProfileImage")
public class ShowUserProfileImage extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ShowUserProfileImage() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("username");
        if (userName == null || userName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Username is missing.");
            return;
        }

        UserServiceImpl dao = new UserServiceImpl();
        byte[] image = dao.getUserProfilePhoto(userName);

        if (image == null) {
            File noImageFile = new File(request.getServletContext().getRealPath("images/noimage.jpg"));
            BufferedImage noImage = ImageIO.read(noImageFile);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(noImage, "jpg", baos);
            image = baos.toByteArray();
        }

        response.setContentType("image/jpeg");
        response.setContentLength(image.length);

        try (ServletOutputStream sos = response.getOutputStream()) {
            sos.write(image);
            sos.flush();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
