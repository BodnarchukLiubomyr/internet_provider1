<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.11.2021
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Internet Provider</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
            <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"></use>
                </svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="#" class="nav-link px-2 text-secondary">Home</a></li>
                <li><a href="/internet_provider/suggestTariffs" class="nav-link px-2 text-white">Internet</a></li>
                <li><a href="/internet_provider/suggestTariffs" class="nav-link px-2 text-white">Cable_TV</a></li>
                <li><a href="/internet_provider/suggestTariffs" class="nav-link px-2 text-white">Phone</a></li>
                <li><a href="/internet_provider/suggestTariffs" class="nav-link px-2 text-white">IP_TV</a></li>
            </ul>

            <ul class="nav navbar-right gap-4">
                <c:if test="${user != null}">
                    <li><a class="mt-2 text-warning">${user.details.firstnameEn} ${user.details.lastnameEn}</a></li>
                    <c:if test="${user.role == \"USER\"}">
                        <li><a class="btn btn-outline-light" href="/internet_provider/userPayments">My payments</a></li>
                    </c:if>
                    <li><a class="btn btn-outline-light" href="/internet_provider?name=logout">Log Out</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</header>
<div class="row row-cols-1 row-cols-md-3 mb-3 text-center"
     style="margin: auto; justify-content: center; align-items: center;">
    <div class="col">
        <div class="card mb-4 rounded-3 shadow-sm center"
             style=" width: 400px; margin: auto; margin-top:110px; vertical-align: middle ">
            <div class="card-header py-3" style="vertical-align: middle;">
                <h1 class="h3 mb-3 font-weight-normal">Wallet</h1>
                <form style="width: 330px; margin: auto; padding: 30px;" method="post">
                    <li class="mt-2 text-info">Available funds:${funds}$</li>
                    <input type="number" name="change" class="form-control" placeholder="Top up wallet">
                    <div class="mt-3">
<%--                        <button type="button" class="ks-btn ks-btn__yellow action ks-text--16 action"--%>
<%--                                data-action="paymentCard">Top up--%>
<%--                        </button>--%>
                        <input type="submit" name="action" class="ks-btn ks-btn__yellow action ks-text--16 action" value="Top up">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>