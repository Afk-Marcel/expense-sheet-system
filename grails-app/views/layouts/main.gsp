<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Expense Sheet System"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>
    <style>
    body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background: #f5f5f5; }
    .container { max-width: 800px; margin: 0 auto; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
    .header { text-align: center; margin-bottom: 30px; padding-bottom: 20px; border-bottom: 2px solid #e0e0e0; }
    .header h1 { color: #333; margin: 0; }
    .nav { text-align: center; margin-bottom: 30px; }
    .nav a { margin: 0 15px; padding: 10px 20px; background: #007cba; color: white; text-decoration: none; border-radius: 4px; }
    .nav a:hover { background: #005a87; }
    .alert { padding: 15px; margin-bottom: 20px; border-radius: 4px; }
    .alert-success { background: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
    .alert-error { background: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
    .form-group { margin-bottom: 20px; }
    .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
    .form-group input, .form-group select, .form-group textarea { width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px; font-size: 16px; }
    .btn { padding: 12px 24px; background: #007cba; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; }
    .btn:hover { background: #005a87; }
    .btn-secondary { background: #6c757d; }
    .btn-secondary:hover { background: #545b62; }
    table { width: 100%; border-collapse: collapse; margin-top: 20px; }
    th, td { padding: 12px; text-align: left; border-bottom: 1px solid #ddd; }
    th { background: #f8f9fa; font-weight: bold; }
    .currency { text-align: right; font-family: monospace; }
    </style>

    <g:layoutHead/>
</head>
<body>
<div class="container">
    <div class="header">
        <h1>💰 Expense Sheet System</h1>
        <p>Track your expenses with real-time ZAR to USD conversion</p>
    </div>

    <div class="nav">
        <g:link controller="home" action="index">🏠 Home</g:link>
        <g:link controller="transaction" action="index">📊 Transactions</g:link>
        <g:link controller="transaction" action="create">➕ Add Expense</g:link>
        <g:link controller="user" action="index">👥 Users</g:link>
    </div>

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