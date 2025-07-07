<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Expense Tracker"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:stylesheet src="application.css"/>
    <g:layoutHead/>
</head>
<body>
<div class="navbar">
    <div class="navbar-content">
        <g:link controller="home" action="index" class="brand">Expense Tracker</g:link>

        <g:if test="${session.currentUser}">
            <div class="nav-links">
                <g:link controller="transaction" action="index">Transactions</g:link>
                <g:link controller="transaction" action="create">Add Transaction</g:link>
                <g:link controller="user" action="profile">Profile</g:link>
                <g:link controller="user" action="logout">Logout</g:link>
            </div>
        </g:if>
    </div>
</div>

<div class="main-content">
    <g:if test="${flash.message}">
        <div class="alert alert-success">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="alert alert-error">${flash.error}</div>
    </g:if>

    <g:layoutBody/>
</div>

<asset:javascript src="application.js"/>
</body>
</html>