<g:set var="layout" value="main" scope="request"/>

<div class="welcome-page">
    <h1>Welcome to Expense Tracker</h1>

    <g:if test="${!session.currentUser}">
        <div class="welcome-content">
            <p>Track your expenses and manage your budget efficiently.</p>
            <g:link action="setupUser" class="btn btn-primary btn-large">Get Started</g:link>
        </div>
    </g:if>

    <g:else>
        <div class="user-dashboard">
            <p>Welcome back! You're already logged in.</p>
            <g:link controller="transaction" action="index" class="btn btn-primary">View Transactions</g:link>
        </div>
    </g:else>
</div>