<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Setup Account - Expense Sheet System</title>
</head>
<body>
<h2>🚀 Setup Your Account</h2>
<p>Let's get started by creating your expense tracking account.</p>

<g:form action="createUser" style="margin-top: 30px;">
    <div class="form-group">
        <label for="name">Your Name:</label>
        <input type="text" id="name" name="name" required maxlength="50"
               placeholder="Enter your full name"
               value="${params.name ?: ''}"/>
    </div>

    <div class="form-group">
        <label for="startingBalance">Starting Balance (ZAR):</label>
        <input type="number" id="startingBalance" name="startingBalance"
               required min="0" step="0.01"
               placeholder="1000.00"
               value="${params.startingBalance ?: ''}"/>
        <small style="color: #666; font-size: 14px;">
            How much money do you currently have available for tracking?
        </small>
    </div>

    <div style="margin-top: 30px;">
        <button type="submit" class="btn">🎯 Create My Account</button>
        <g:link action="index" class="btn btn-secondary" style="margin-left: 15px;">
            ← Back to Home
        </g:link>
    </div>
</g:form>

<div style="margin-top: 40px; padding: 20px; background: #e7f3ff; border-radius: 8px;">
    <h4 style="margin: 0 0 10px 0;">💡 Getting Started Tips:</h4>
    <ul style="margin: 0; padding-left: 20px;">
        <li>Enter your current available balance in South African Rand (ZAR)</li>
        <li>All transactions will automatically convert to USD using live exchange rates</li>
        <li>You can export your transaction history to CSV anytime</li>
        <li>Your running balance will update automatically with each expense</li>
    </ul>
</div>
</body>
</html>