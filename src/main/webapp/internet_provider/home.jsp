<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.11.2021
  Time: 13:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Internet Provider</title>
    <link rel="stylesheet" href="/style/home.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
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
                <li><a href="/internet_provider/suggestTariffs?service=Internet" class="nav-link px-2 text-black">Internet</a></li>
                <li><a href="/internet_provider/suggestTariffs?service=Cable_TV" class="nav-link px-2 text-black">Cable_TV</a></li>
                <li><a href="/internet_provider/suggestTariffs?service=Phone" class="nav-link px-2 text-black">Phone</a></li>
                <li><a href="/internet_provider/suggestTariffs?service=IP_TV" class="nav-link px-2 text-black">IP_TV</a></li>
            <%--                <c:forEach var="service" items="${services}">--%>
<%--                        <li><a href="/internet_provider/suggestTariffs?service_en=${service}" class="nav-link px-2 text-black">${service}</a>--%>
<%--                        </li>--%>
<%--                </c:forEach>--%>
            </ul>
            <ul class="nav navbar-right gap-4">
                <c:if test="${user != null}">
                    <c:if test="${user.role == \"USER\"}">
                        <li><a class="mt-2 text-warning"
                               href="/internet_provider/ownWallet">${user.details.firstnameEn} ${user.details.lastnameEn}</a>
                        </li>
                    </c:if>
                    <c:if test="${user.role == \"ADMIN\"}">
                        <li class="mt-2 text-warning">${user.details.firstnameEn} ${user.details.lastnameEn}</li>
                    </c:if>
                    <c:if test="${user.role == \"USER\"}">
                        <li><a class="btn btn-outline-dark" href="/internet_provider/userPayments">My payments</a></li>
                        <li><a class="btn btn-outline-dark" href="/internet_provider/userSuggestion">Suggest tariff</a></li>
                    </c:if>
                    <c:if test="${user.role == \"ADMIN\"}">
                        <li class="mt-2 text-warning">ADMIN</li>
                        <li><a class="btn btn-outline-dark" href="/internet_provider/addTariff">Add new tariff</a></li>
                        <li><a class="btn btn-outline-dark" href="/internet_provider/suggestionExamples">User Suggestion</a></li>
                    </c:if>
                    <li><a class="btn btn-outline-dark" href="/internet_provider/home?name=logout">Log Out</a></li>
                </c:if>
            </ul>
            <c:if test="${user == null}">
                <li><a class="btn btn-outline-dark" href="/internet_provider/registration">Create account</a></li>
                <li class="ms-2"><a class="btn btn-outline-dark" href="/internet_provider/login">Log In</a></li>
            </c:if>
        </div>
    </div>
</header>
<div class="pricing-header p-3 pb-md-4 mx-auto text-center">
    <h1 class="display-4 fw-normal text-light">Novelty</h1>
</div>
<div class="sort d-flex justify-content-center">
    <ul class="nav gap-4">
        <li>
            <form class="mt-4 mb-4" style="width: 500px; height: 125px" method="get">
                <p class="h3 text-center text-light mb-2">Sort by:</p>
                <ul class="nav gap-4 d-flex justify-content-center">
                    <li>
                        <input type="submit" class="btn btn-outline-light btn-width" value="Name" name="sortBy">
                    </li>
                    <li>
                        <input type="submit" class="btn btn-outline-light btn-width" value="Price" name="sortBy">
                    </li>
                </ul>
                <p class="text-light mt-2 text-center">Sorted: ${sortBy} ${sortOrder}</p>
            </form>
        </li>
    </ul>
</div>
<div class="row row-cols-1 col-6 mx-auto row-cols-md-3 mb-3 text-center">
    <c:forEach var="tariff" items="${tariffs}">
        <div class="col">
            <div class="card mb-4 rounded-3 shadow-sm">
                <div class="card-body">
                    <div class="card-header py-3 text-white bg-primary border-primary">
                        <h4 class="my-0 fw-normal">${tariff.name_en}</h4>
                    </div>
                    <form class="mb-4" method="post">
                        <input name="tariff_id" value="${tariff.id}" type="hidden">
                        <ul class="list-unstyled mt-3 mb-4">
                            <li><p class="h5 text-info">${tariff.service.service_en}</p></li>
                            <li><p class="h5 text-info">${tariff.description}</p></li>
                            <li><p class="h5 text-info">Time: ${tariff.time} days</p></li>
                            <li><p class="h5 text-info">Price: ${tariff.price} $</p></li>
                        </ul>
                        <c:if test="${user.role == \"USER\"}">
                            <input type="submit" class="btn btn-warning mb-4 btn-width" value="Choose">
                        </c:if>
                        <c:if test="${user.role == \"ADMIN\"}">
                            <input type="submit" class="btn btn-danger mb-4 btn-width" name="button" value="Delete"/>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>

