<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>BookMyMovie</title>
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
        form {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        select {
            padding: 5px;
            margin-right: 10px;
        }
        button {
            padding: 5px 10px;
            background-color: #333;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        button:hover {
            background-color: #555;
        }
        #movies {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 20px;
        }
        .movie {
            background-color: #fff;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            text-align: center;
            text-decoration: none;
            color: inherit;
        }
        .movie h2 {
            font-size: 1.5em;
            color: #333;
        }
        .movie p {
            font-size: 1em;
            color: #666;
        }
        .movie:hover {
            background-color: #f9f9f9;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to BookMyMovie</h1>

        <!-- Filter Dropdown -->
        <form action="filterMovies" method="get">
            <label for="location">Filter by Location:</label>
            <select name="location" id="location">
                <option value="All">All Locations</option>
                <option value="Chennai">Chennai</option>
                <option value="Madurai">Madurai</option>
                <option value="Salem">Salem</option>
            </select>
            <button type="submit">Filter</button>
        </form>

        <!-- Movie Details -->
        <div id="movies">
            <c:forEach var="movie" items="${movies}">
                <a href="bookMovie?movieName=${movie.movieName}&location=${movie.screen.theaterRef.location}" class="movie">
                    <div>
                        <h2>${movie.movieName}</h2>
                        <p><strong>Language:</strong> ${movie.language}</p>
                        <p><strong>Location:</strong> ${movie.screen.theaterRef.location}</p>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
</body>
</html>
