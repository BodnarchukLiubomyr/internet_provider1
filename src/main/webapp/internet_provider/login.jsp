<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 24.11.2021
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
            <a href="/internet_provider" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap">
                    <use xlink:href="#bootstrap"></use>
                </svg>
            </a>

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/internet_provider/home" class="nav-link px-2 text-dark">Home</a></li>
            </ul>
            <ul class="nav navbar-right gap-4">
                <li><a class="btn btn-outline-dark" href="/internet_provider/registration">Create account</a></li>
            </ul>
        </div>
    </div>
</header>
<div class="container text-center">
    <form style="width: 330px; margin: auto; margin-top: 110px; padding: 30px;" action="/internet_provider/login"
          method="post">
        <h1 class="h3 mb-3 font-weight-normal text-light">Sign in</h1>
        <input type="login" name="login" class="form-control" placeholder="Login" required>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <div class="mt-3">
            <input type="submit" class="btn btn-sm btn-primary" value="Enter"
                   style="width: 270px; margin-bottom: 15px;">
            <p class="text-light">If you haven`t account,create new</p>
            <a href="/internet_provider/registration" class="btn btn-sm btn-warning" style="width: 270px;">Create
                account</a>
        </div>
    </form>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
</body>
</html>
