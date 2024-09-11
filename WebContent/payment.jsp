<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page
	import="com.pushpak.service.impl.*,com.pushpak.service.*,com.pushpak.beans.*,java.util.*,javax.servlet.ServletOutputStream,java.io.*"%>
<!DOCTYPE html>
<html>
<head>
<title>Payments</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<link rel="stylesheet" href="css/changes.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #E6F9E6;">

	<%
	/* Checking the user credentials */
	String userName = (String) session.getAttribute("username");
	String password = (String) session.getAttribute("password");

	if (userName == null || password == null) {
		response.sendRedirect("login.jsp?message=Session Expired, Login Again!!");
	}

	String sAmount = request.getParameter("amount");

	double amount = 0;

	if (sAmount != null) {
		amount = Double.parseDouble(sAmount);
	}
	%>

	<jsp:include page="header.jsp" />

	<div class="container">
		<div class="row"
			style="margin-top: 5px; margin-left: 2px; margin-right: 2px;">
			<form action="./OrderServlet" method="post"
				class="col-md-6 col-md-offset-3"
				style="border: 2px solid black; border-radius: 10px; background-color: #FFE5CC; padding: 10px;">
				<div style="font-weight: bold;" class="text-center">
					<div class="form-group">
						<%
							// Assuming you want to display a user profile image dynamically
							String profileImageUrl = "ShowUserProfileImage?username=" + userName;
						%>
						<img src="<%=profileImageUrl%>" alt="Payment Proceed" height="100px" />
						<h2 style="color: green;">Credit Card Payment</h2>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label for="cardholder">Name of Card Holder</label> 
						<input type="text" placeholder="Enter Card Holder Name" name="cardholder" class="form-control" id="cardholder" required>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12 form-group">
						<label for="cardnumber">Enter Credit Card Number</label> 
						<input type="text" placeholder="4242-4242-4242-4242" name="cardnumber" class="form-control" id="cardnumber" required>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 form-group">
						<label for="expmonth">Expiry Month</label> 
						<input type="number" placeholder="MM" name="expmonth" class="form-control" id="expmonth" max="12" min="1" required>
					</div>
					<div class="col-md-6 form-group">
						<label for="expyear">Expiry Year</label> 
						<input type="number" placeholder="YYYY" class="form-control" id="expyear" name="expyear" required>
					</div>
				</div>
				<div class="row text-center">
					<div class="col-md-6 form-group">
						<label for="cvv">Enter CVV</label> 
						<input type="number" placeholder="123" class="form-control" id="cvv" name="cvv" required> 
						<input type="hidden" name="amount" value="<%=amount%>">
					</div>
					<div class="col-md-6 form-group">
						<label>&nbsp;</label>
						<button type="submit" class="form-control btn btn-success">
							Pay : Rs <%=amount%>
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- End of Product Items List -->

	<%@ include file="footer.html"%>

</body>
</html>
