<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Booking Confirmation</title>
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
        .details {
            margin-bottom: 20px;
        }
        .details span {
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Booking Confirmation</h1>
        <div class="details">
            <p><span>Customer Name:</span> ${customerName}</p>
            <p><span>Email:</span> ${email}</p>
            <p><span>Movie:</span> ${movieName}</p>
            <p><span>Location:</span> ${location}</p>
            <p><span>Theater ID:</span> ${theaterId}</p>
            <p><span>Screen ID:</span> ${screenId}</p>
            <p><span>Selected Seats:</span></p>
            <ul>
                <c:forEach var="seat" items="${seats}">
                    <li>${seat}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
</body>
</html>
