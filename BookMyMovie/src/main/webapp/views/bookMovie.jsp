<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title> BookMyMovie - ${movieName} in ${location}  </title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .theater {
            background-color: #fff;
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .theater h2 {
            font-size: 1.5em;
            color: #333;
        }
        .theater h3 {
            font-size: 1.2em;
            color: #666;
        }
        .theater a {
            display: block;
            margin-top: 10px;
            padding: 10px 15px;
            background-color: #333;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
        .theater a:hover {
            background-color: #555;
        }
    </style>
</head>
<body>

    <div class="container">
        <h1>Theaters Showing "${movieName}" in ${location} "</h1>
        <c:forEach var="theater" items="${theaters}">
            <div class="theater">
                <h2>${theater.theaterName}</h2>
                <p><strong>Location:</strong> ${theater.location}</p>
                <c:forEach var="screen" items="${theater.screens}">
                    <c:if test="${screen.movieRef.movieName == movieName}">
                        <h3>Screen: ${screen.screenName}</h3>
                        <p><strong>Language:</strong> ${screen.movieRef.language}</p>
                        <a href="bookTicket?theaterId=${theater.theaterId}&screenId=${screen.screenId}&movieName=${screen.movieRef.movieName}&location=${location}&origin=${theater.origin}">Book Now</a>
                    </c:if>
                </c:forEach>
            </div>
        </c:forEach>
    </div>
</body>
</html>
