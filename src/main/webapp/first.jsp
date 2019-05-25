<html>
<head>
    <title>First JSP</title>
</head>
<body>
    <h1>First JSP</h1>
    <p>Hello from JSP</p>

    <ul>
    <% for (int i = 0; i < 10; i++) {%>
        <li>List item <%=i %></li>
    <%}%>
    </ul>
</body>
</html>