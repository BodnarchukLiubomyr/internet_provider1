<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.11.2021
  Time: 23:31
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
                    <li><a class="btn btn-outline-light" href="/internet_provider?name=logout">Log Out</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</header>
<p class="h3 mt-4 mb-4 text-center text-black">My payments</p>
<c:if test="${payments == []}">
    <p class="h1 mt-4 mb-4 text-center text-white">You don't have any payments yet.</p>
</c:if>
<c:forEach var="payment" items="${payments}">
    <form class="ticket mt-5 mb-4 mx-auto bg-white p-4">
        <p class="h3 text-center text-black mb-4">Payment</p>
        <ul>
            <li>
                <p class="h4">${payment.name}</p>
            </li>
            <li>
                <p class="h5">Price: ${payment.price}</p>
            </li>
            <li>
                <p class="h5">Time: ${payment.time} min</p>
            </li>
            <br>
        </ul>
    </form>
</c:forEach>
<c:if test="${lastPage > 1}">
    <nav>
        <div class="d-flex justify-content-center mt-5 mb-4">
            <ul class="pagination">
                <c:if test="${currentPage == firstPage}">
                    <li class="page-item disabled">
                        <span class="page-link">Previous</span>
                    </li>
                </c:if>
                <c:if test="${currentPage != firstPage}">
                    <li class="page-item">
                        <a href="/myPayments?page=${currentPage-1}" style="text-decoration: none">
                            <span class="page-link">Previous</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="pageNumber" items="${pages}">
                    <c:choose>
                        <c:when test="${pageNumber.equals(currentPage)}">
                            <li class="page-item disabled">
                                <span class="page-link">${pageNumber}</span>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a href="//myPayments?page=${pageNumber}" style="text-decoration: none">
                                    <span class="page-link">${pageNumber}</span>
                                </a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${currentPage == lastPage}">
                    <li class="page-item disabled">
                        <span class="page-link">Next</span>
                    </li>
                </c:if>
                <c:if test="${currentPage != lastPage}">
                    <li class="page-item">
                        <a href="//myPayments?page=${currentPage+1}" style="text-decoration: none">
                            <span class="page-link">Next</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </nav>
</c:if>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous">
</script>
</body>
</html>
