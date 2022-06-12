<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 02.04.2022
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Internet Provider</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body style="background-image: url(https://cdn.pixabay.com/photo/2022/05/29/04/35/trails-7228378_960_720.jpg)">
<header class="p-3 bg-light text-dark">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"></use>
                </svg>
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/internet_provider/home" class="nav-link px-2 text-dark">Home</a></li>
            </ul>
            <ul class="nav navbar-right gap-4">
                <c:if test="${user.role == \"USER\"}">
                    <li><a class="mt-2 text-warning"
                           href="/internet_provider/ownWallet">${user.details.firstnameEn} ${user.details.lastnameEn}</a>
                    </li>
                </c:if>
                <li><a class="btn btn-outline-dark" href="/internet_provider/home?name=logout">Log Out</a></li>
            </ul>
        </div>
    </div>
</header>
<div class="container text-center">
    <form style="width: 330px; margin: auto; margin-top: 110px; padding: 30px;" action="/internet_provider/placeChoose"
          method="post">
        <h1 class="h3 mb-3 font-weight-normal text-light">Select place</h1>
        <input type="text" placeholder="Street" class="form-control" name="street" required>
        <input type="text" placeholder="District" class="form-control" name="district" required>
        <input type="text" placeholder="City" class="form-control" name="city" required>
        <div class="mt-3">
            <input type="submit" class="btn btn-sm btn-primary" value="Enter">
            <p class="text-light">If you don`t want to change anything, you can skip</p>
            <a href="/internet_provider/render" class="btn btn-sm btn-primary" style="width: 270px;">Skip</a>
        </div>
    </form>
</div>
</body>
</html>
