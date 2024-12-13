<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Ticket Confirmation - ${movieName}</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 10px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .ticket-details {
            margin-bottom: 20px;
        }
        .ticket-details span {
            font-weight: bold;
        }
        .checkout-button {
            display: block;
            width: 100%;
            padding: 15px 20px;
            background-color: #333;
            color: #fff;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .checkout-button:hover {
            background-color: #555;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Confirm Your Booking for "${movieName}"</h1>
        <form action="/completeBooking" method="get">
            <input type="hidden" name="theaterId" value="${theaterId}">
            <input type="hidden" name="screenId" value="${screenId}">
            <input type="hidden" name="movieName" value="${movieName}">
            <input type="hidden" name="location" value="${location}">
            <input type="hidden" name="origin" value="${origin}">
            
            <div class="form-group">
                <label for="customerName">Customer Name</label>
                <input type="text" id="customerName" name="customerName" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required>
            </div>
            
            <div class="ticket-details">
                <p><span>Movie:</span> ${movieName}</p>
                <p><span>Theater:</span> ${MyTheater.theaterName}</p>
                <p><span>Screen:</span> ${MyScreen.screenName}</p>
                <p><span>Location:</span> ${location}</p>
                <p><span>Selected Seats:</span> 
                    <c:forEach var="seat" items="${seats}">
                        ${seat}
                        <input type="hidden" name="seats" value="${seat}">
                    </c:forEach>
                </p>
            </div>
            <button type="submit" class="checkout-button">Check Out</button>
        </form>
    </div>
</body>
</html>
