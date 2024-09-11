package com.pushpak.utility;

import jakarta.mail.MessagingException;

public class MailMessage {
    public static void registrationSuccess(String emailId, String name) {
        String recipient = emailId;
        String subject = "Registration Successful";
        String htmlTextMessage = "" + 
            "<html>" + 
            "<body>" +
            "<h2 style='color:green;'>Welcome to ShopNest</h2>" + 
            "<br>Hi " + name + "," +
            "<br><br>Thanks for signing up with ShopNest.<br>" +
            "We are delighted to have you with us. At ShopNest, we offer a diverse range of products to meet all your needs, not just electronics. Explore our extensive collection of various items including home goods, fashion, and more." +
            "<br>We are currently offering up to 60% OFF on many products. Be sure to visit our site and discover the range of products available." +
            "<br><br>As a thank you for your support, we offer free delivery on all orders and a wide selection of top brands.<br><br>As a Welcome gift for our new customers, enjoy an additional 10% OFF up to 500 Rs on your first purchase. " +
            "<br>To claim this offer, simply enter the promo code below.<br><br><br>" +
            "PROMO CODE: SHOPNEST500<br><br><br>" +
            "Have a great day!<br>" +
            "</body>" + 
            "</html>";

        try {
            JavaMailUtil.sendMail(recipient, subject, htmlTextMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void transactionSuccess(String recipientEmail, String name, String transId, double transAmount) {
        String recipient = recipientEmail;
        String subject = "Order Placed at ShopNest";
        String htmlTextMessage = "<html>" + 
            "<body>" + 
            "<p>" + 
            "Hey " + name + ",<br/><br/>" +
            "We are glad that you shopped with ShopNest!" + 
            "<br/><br/>" +
            "Your order has been placed successfully and is under process to be shipped." +
            "<br/><h6>Please Note that this is a demo project email and you have not made any real transaction with us till now!</h6>" +
            "<br/>" +
            "Here are your transaction details:<br/><br/>" +
            "<font style=\"color:red;font-weight:bold;\">Order Id:</font>" +
            "<font style=\"color:green;font-weight:bold;\">" + transId + "</font><br/>" +
            "<br/>" +
            "<font style=\"color:red;font-weight:bold;\">Amount Paid:</font> <font style=\"color:green;font-weight:bold;\">" +
            transAmount + "</font>" +
            "<br/><br/>" +
            "Thanks for shopping with us!<br/><br/>" +
            "Come Shop Again! <br/><br/><font style=\"color:green;font-weight:bold;\">ShopNest</font>" +
            "</p>" + 
            "</body>" + 
            "</html>";

        try {
            JavaMailUtil.sendMail(recipient, subject, htmlTextMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void orderShipped(String recipientEmail, String name, String transId, double transAmount) {
        String recipient = recipientEmail;
        String subject = "Hurray!! Your Order has been Shipped from ShopNest";
        String htmlTextMessage = "<html>" + 
            "<body>" + 
            "<p>" + 
            "Hey " + name + ",<br/><br/>" +
            "We are glad that you shopped with ShopNest!" + 
            "<br/><br/>" +
            "Your order has been shipped successfully and is on the way to be delivered." +
            "<br/><h6>Please Note that this is a demo project email and you have not made any real transaction with us till now!</h6>" +
            "<br/>" +
            "Here are your transaction details:<br/><br/>" +
            "<font style=\"color:red;font-weight:bold;\">Order Id:</font>" +
            "<font style=\"color:green;font-weight:bold;\">" + transId + "</font><br/>" +
            "<br/>" +
            "<font style=\"color:red;font-weight:bold;\">Amount Paid:</font> <font style=\"color:green;font-weight:bold;\">" +
            transAmount + "</font>" +
            "<br/><br/>" +
            "Thanks for shopping with us!<br/><br/>" +
            "Come Shop Again! <br/><br/><font style=\"color:green;font-weight:bold;\">ShopNest</font>" +
            "</p>" + 
            "</body>" + 
            "</html>";

        try {
            JavaMailUtil.sendMail(recipient, subject, htmlTextMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void productAvailableNow(String recipientEmail, String name, String prodName, String prodId) {
        String recipient = recipientEmail;
        String subject = "Product " + prodName + " is Now Available at ShopNest";
        String htmlTextMessage = "<html>" + 
            "<body>" + 
            "<p>" + 
            "Hey " + name + ",<br/><br/>" +
            "We are glad that you shopped with ShopNest!" + 
            "<br/><br/>" +
            "As per your recent browsing history, we noticed that you were searching for an item that was not available in sufficient quantity at that time.<br/><br/>" +
            "We are excited to inform you that the product named <font style=\"color:green;font-weight:bold;\">" + prodName + 
            "</font> with product Id <font style=\"color:green;font-weight:bold;\">" + prodId +
            "</font> is now available in our store!" +
            "<br/><h6>Please Note that this is a demo project email and you have not made any real transaction with us or ordered anything till now!</h6>" +
            "<br/>" +
            "Here are the details of the product now available to shop:<br/><br/>" +
            "<font style=\"color:red;font-weight:bold;\">Product Id:</font> <font style=\"color:green;font-weight:bold;\">" +
            prodId + "</font><br/>" +
            "<br/>" +
            "<font style=\"color:red;font-weight:bold;\">Product Name:</font> <font style=\"color:green;font-weight:bold;\">" +
            prodName + "</font>" +
            "<br/><br/>" +
            "Thanks for shopping with us!<br/><br/>" +
            "Come Shop Again! <br/><br/><br/><font style=\"color:green;font-weight:bold;\">ShopNest</font>" +
            "</p>" + 
            "</body>" + 
            "</html>";

        try {
            JavaMailUtil.sendMail(recipient, subject, htmlTextMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static String sendMessage(String toEmailId, String subject, String htmlTextMessage) {
        try {
            JavaMailUtil.sendMail(toEmailId, subject, htmlTextMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            return "FAILURE";
        }
        return "SUCCESS";
    }
}
