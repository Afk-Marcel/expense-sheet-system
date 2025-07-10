<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Add Transaction - Expense Sheet System</title>
</head>
<body>
<h2>➕ Add New Expense</h2>
<p>Record a new expense transaction.</p>

<g:form action="save" style="margin-top: 30px;">
    <div class="form-group">
        <label for="user">Select User:</label>
        <g:select name="user.id"
                  from="${expense.sheet.system.User.list()}"
                  optionKey="id"
                  optionValue="name"
                  noSelection="['': 'Choose User...']"
                  class="form-control"
                  required="true"/>
    </div>

    <div class="form-group">
        <label for="amount">Amount (ZAR):</label>
        <input type="number"
               id="amount"
               name="amount"
               required
               min="0.01"
               step="0.01"
               placeholder="150.00"
               style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px;"/>
        <small style="color: #666; font-size: 14px;">
            Enter the amount you spent
        </small>
    </div>

    <div class="form-group">
        <label for="description">Description:</label>
        <input type="text"
               id="description"
               name="description"
               required
               maxlength="250"
               placeholder="What did you spend money on?"
               style="width: 100%; padding: 10px; border: 1px solid #ddd; border-radius: 4px;"/>
        <small style="color: #666; font-size: 14px;">
            Brief description of the expense (e.g., "Groceries at Pick n Pay")
        </small>
    </div>

    <div style="margin-top: 30px;">
        <button type="submit" class="btn">💰 Record Transaction</button>
        <g:link action="index" class="btn btn-secondary" style="margin-left: 15px;">
            ← Back to Transactions
        </g:link>
    </div>
</g:form>

<div style="margin-top: 40px; padding: 20px; background: #e7f3ff; border-radius: 8px;">
    <h4 style="margin: 0 0 10px 0;">ℹ️ What happens next:</h4>
    <ul style="margin: 0; padding-left: 20px;">
        <li><strong>Running balance</strong> will be calculated automatically</li>
        <li><strong>USD amount</strong> will be converted using live exchange rates</li>
        <li><strong>Your account balance</strong> will be updated instantly</li>
        <li><strong>Transaction history</strong> will be available for CSV export</li>
    </ul>
</div>
</body>
</html>