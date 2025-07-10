<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Home - Expense Sheet System</title>
</head>
<body>
<h2>Welcome to Your Expense Tracker</h2>

<g:if test="${users}">
    <p>Select your account to continue:</p>

    <div style="display: grid; gap: 15px; margin-top: 20px;">
        <g:each in="${users}" var="user">
            <div style="padding: 20px; border: 1px solid #ddd; border-radius: 8px; background: #f9f9f9;">
                <h3 style="margin: 0 0 10px 0;">${user.name}</h3>
                <p style="margin: 5px 0;">
                    <strong>Current Balance:</strong>
                    <span class="currency">R${user.currentBalance}</span>
                </p>
                <p style="margin: 5px 0;">
                    <strong>Started with:</strong>
                    <span class="currency">R${user.startingBalance}</span>
                </p>
                <p style="margin: 15px 0 0 0;">
                    <g:link controller="transaction" action="index" params="[userId: user.id]" class="btn">
                        View Transactions
                    </g:link>
                    <g:link controller="transaction" action="exportCsv" params="[userId: user.id]" class="btn btn-secondary" style="margin-left: 10px;">
                        📄 Export CSV
                    </g:link>
                </p>
            </div>
        </g:each>
    </div>

    <div style="margin-top: 30px; text-align: center;">
        <g:link action="setupUser" class="btn btn-secondary">+ Create New User</g:link>
    </div>
</g:if>
</body>
</html>