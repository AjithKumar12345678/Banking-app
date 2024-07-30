<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Account Login</title>
    <link href="https://fonts.googleapis.com/css?family=Poppins&display=swap" rel="stylesheet">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body, html {
            height: 100%;
            font-family: 'Poppins', sans-serif;
        }
        .page {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100%;
            background-color: #f8f9fa;
        }
        .form-control-custom {
            background-color: #ffffff;
            backdrop-filter: blur(32px);
            padding: 25px;
            border-radius: 24px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .title {
            font-size: 28px;
            font-weight: 800;
            margin-bottom: 20px;
            text-align: center;
        }
        .input-field {
            position: relative;
            margin-bottom: 20px;
        }
        .input {
            width: 100%;
            padding: 10px 15px;
            border: 1.5px solid #ecedec;
            border-radius: 8px;
            background: transparent;
        }
        .input:focus {
            border-color: #2d79f3;
        }
        .label {
            position: absolute;
            top: 50%;
            left: 15px;
            transform: translateY(-50%);
            color: #ccc;
            transition: all 0.3s ease;
            pointer-events: none;
        }
        .input:focus + .label,
        .input:not(:placeholder-shown) + .label {
            top: -10px;
            left: 10px;
            font-size: 12px;
            color: #2d79f3;
            background-color: #ffffff;
            padding: 0 5px;
        }
        .submit-btn {
            width: 100%;
            padding: 15px;
            border: none;
            border-radius: 11px;
            background-color: #ff7f3e;
            color: #fff;
            font-size: 18px;
            font-weight: 700;
            cursor: pointer;
            transition: all 0.3s;
        }
        .submit-btn:hover {
            background-color: #ff571e;
        }
        .admin {
            text-align: center;
            margin-top: 20px;
        }
        .admin a {
            color: #007bff;
            text-decoration: none;
        }
        .admin a:hover {
            text-decoration: underline;
        }
        .forgorp {
            display: block;
            text-align: right;
            margin-bottom: 20px;
            color: #007bff;
            text-decoration: none;
        }
        .forgorp:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body class="page">
    <form class="form-control-custom" action="LoginServlet" method="post">
        <p class="title">Customer Login</p>
        <div class="input-field">
            <input required="" class="input" type="text" name="accountNo" placeholder=" " />
            <label class="label" for="accountNo">Enter Account Number</label>
        </div>
        <div class="input-field">
            <input required="" class="input" type="password" name="password" placeholder=" " />
            <label class="label" for="password">Enter Password</label>
        </div>
        <button class="submit-btn" type="submit">Sign In</button>
        <div class="admin">
            <a href="AdminLogin.jsp" class="shift">Login as Admin</a>
        </div>
    </form>
</body>
</html>
