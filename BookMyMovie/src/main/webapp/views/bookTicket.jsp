<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html>
<head>
    <title>Book Ticket - ${movieName}</title>
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
            margin-bottom: 30px;
        }
        .seat-layout {
            display: grid;
            grid-template-columns: 40px repeat(10, 1fr);
            gap: 10px;
            justify-items: center;
            align-items: center;
        }
        .row-label {
            font-weight: bold;
            text-align: center;
        }
        .seat {
            width: 40px;
            height: 40px;
            background-color: #ccc;
            text-align: center;
            line-height: 40px;
            cursor: pointer;
            border-radius: 5px;
            box-shadow: 0 1px 2px rgba(0, 0, 0, 0.1);
        }
        .seat.sold {
            background-color: #f00;
            color: #fff;
            cursor: not-allowed;
        }
        .seat input {
            display: none;
        }
        .seat.selected {
            background-color: #333;
            color: #fff;
        }
        .book-button {
            display: block;
            width: 100%;
            padding: 15px 20px;
            margin-top: 30px;
            background-color: #333;
            color: #fff;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .book-button:hover {
            background-color: #555;
        }
    </style>
    <script>
        function toggleSeat(checkbox) {
            const seatLabel = checkbox.parentElement;
            if (checkbox.checked) {
                seatLabel.classList.add('selected');
            } else {
                seatLabel.classList.remove('selected');
            }
        }

        function validateForm(event) {
            const selectedSeats = document.querySelectorAll('.seat.selected');
            if (selectedSeats.length === 0) {
                alert('Please select at least one seat.');
                event.preventDefault();
                return false;
            }
            return true;
        }
    </script>
</head> 
<body>
    <div class="container">
        <h1>Select Seats for "${movieName}"</h1>
        <form action="bookTicketConfirm" method="get" onsubmit="return validateForm(event)">
            <input type="hidden" name="theaterId" value="${theaterId}">
            <input type="hidden" name="screenId" value="${screenId}">
            <input type="hidden" name="movieName" value="${movieName}">
            <input type="hidden" name="location" value="${location}">
            <input type="hidden" name="origin" value="${origin}">
            
            <div class="seat-layout">
                <c:forEach var="rowLabel" begin="0" end="4">
                    <div class="row-label">${(rowLabel == 0 ? 'A' : (rowLabel == 1 ? 'B' : (rowLabel == 2 ? 'C' : (rowLabel == 3 ? 'D' : 'E'))))}</div>
                    <c:forEach var="col" begin="1" end="10">
                        <c:set var="seat" value="${(rowLabel == 0 ? 'A' : (rowLabel == 1 ? 'B' : (rowLabel == 2 ? 'C' : (rowLabel == 3 ? 'D' : 'E'))))}${col}"/>
                        <%
                            String seatValue = (String) pageContext.findAttribute("seat");
                            boolean isSold = false;
                            for (String soldSeat : (List<String>) pageContext.findAttribute("soldSeats")) {
                                if (soldSeat.equals(seatValue)) {
                                    isSold = true;
                                    break;
                                }
                            }
                        %>
                        <c:choose>
                            <c:when test="<%= isSold %>">
                                <div class="seat sold"><%= seatValue %></div>
                            </c:when>
                            <c:otherwise>
                                <label class="seat">
                                    <input type="checkbox" name="seats" value="<%= seatValue %>" onclick="toggleSeat(this)">
                                    <%= seatValue %>
                                </label>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:forEach>
            </div>
            <button type="submit" class="book-button">Book Tickets</button>
        </form>
    </div>
</body>
</html>
