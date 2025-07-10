<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Transactions - Expense Sheet System</title>
</head>
<body>
<h2>📊 Transaction History</h2>

<div style="margin-bottom: 20px;">
    <g:link action="create" class="btn">➕ Add New Transaction</g:link>
    <g:if test="${params.userId}">
        <g:link controller="transaction" action="exportCsv" params="[userId: params.userId]"
                class="btn btn-secondary" style="margin-left: 10px;">
            📄 Export CSV
        </g:link>
    </g:if>
</div>

<g:if test="${transactionList}">
    <table>
        <thead>
        <tr>
            <th>Date</th>
            <th>User</th>
            <th>Description</th>
            <th>Amount (ZAR)</th>
            <th>Amount (USD)</th>
            <th>Running Balance</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${transactionList}" var="transaction">
            <tr>
                <td><g:formatDate date="${transaction.dateCreated}" format="yyyy-MM-dd HH:mm"/></td>
                <td>${transaction.user?.name}</td>
                <td>${transaction.description}</td>
                <td class="currency">R${transaction.amount}</td>
                <td class="currency">$${transaction.usdAmount ?: 'N/A'}</td>
                <td class="currency">R${transaction.runningBalance}</td>
                <td>
                    <g:link action="show" id="${transaction.id}" class="btn" style="padding: 5px 10px; font-size: 12px;">
                        View
                    </g:link>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div style="margin-top: 20px; text-align: center;">
        <g:paginate total="${transactionCount ?: 0}" />
    </div>
</g:if>
<g:else>
    <div style="text-align: center; padding: 40px; background: #f8f9fa; border-radius: 8px;">
        <h3>No transactions yet!</h3>
        <p>Start tracking your expenses by adding your first transaction.</p>
        <g:link action="create" class="btn">➕ Add First Transaction</g:link>
    </div>
</g:else>
</body>
</html>