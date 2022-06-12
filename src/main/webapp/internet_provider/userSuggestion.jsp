
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Internet Provider</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body style="background-image: url(https://cdn.pixabay.com/photo/2022/05/29/04/35/trails-7228378_960_720.jpg)">
<header class="p-3 bg-light text-black">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"></use>
                </svg>
            </a>
            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/internet_provider/home" class="nav-link px-2 text-black">Home</a></li>
            </ul>
            <ul class="nav navbar-right gap-4">
                <c:if test="${user != null}">
                    <li><a class="mt-2 text-warning">${user.details.firstnameEn} ${user.details.lastnameEn}</a></li>
                    <li><a class="btn btn-outline-dark" href="/internet_provider/home?name=logout">Log Out</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</header>
<div class="container text-center mt-5 mb-4">
    <form style="width: 400px; margin: auto; padding: 30px;" method="post" action="/internet_provider/userSuggestion.jsp">
        <h1 class="h3 mb-3 font-weight-normal text-light">UserSuggestion</h1>
        <div class="dropdown mb-2">
            <select name="service" class="btn btn-outline-light dropdown-toggle" required>
                <c:forEach items="${services}" var="service">
                    <option value="${service.id}">${service.service_en}</option>
                </c:forEach>
            </select>
        </div>
        <input name="name_en" type="text" class="start form-control" placeholder="Tariff name" required>
        <input name="description" type="text" placeholder="Description" class="in form-control" required>
        <input name="time" type="text" class="in form-control" placeholder="Time (days)" required>
        <input name="price" type="number" placeholder="Price" class="finish form-control mb-2" required>
        <div>
            <input type="submit" name="action" class="btn btn-sm btn-success" value="Send" style="width: 270px;">
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous">
</script>
</body>
</html>
