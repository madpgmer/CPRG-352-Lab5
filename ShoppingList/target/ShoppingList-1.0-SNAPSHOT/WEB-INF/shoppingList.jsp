<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <h1>Shopping List</h1>
        <table>
            <tr>
                <td>
                    <p> Hello, <strong> ${username} </strong> </p>
                </td>
                <td>
                    <a href="ShoppingList?action=logout">Logout</a>
                </td>
            </tr>
        </table>   
                
        <h2>List</h2>
        
        <form action="ShoppingList" method="post">
            <input type="hidden" name="action" value="add">             
            <%-- this input above will not be displayed to the user. It will only be inside the HTML--%>
            <table>
                <tr>
                    <th><label for="item">Add item:</label></th>
                    <th><input type="text" name="item" id="item"></th>
                    <th><button type="submit"> Add</button></th>
                </tr>                           
            </table>            
        </form>
        
        <form action="ShoppingList" method="post">
            <input type="hidden" name="action" value="delete"> 
            <table>
                <c:forEach items="${listOfItems}" var="item"> 
                <tr>
                    <th><input type="radio" name="item", value="${item}"></th>
                    <th><label for="myList">${item}</label></th>
                </tr> 
                </c:forEach>
            </table>  
                           
            <button type="submit">Delete</button>
        </form>
    </body>
</html>
