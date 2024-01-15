<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Crimson+Text&family=Signika&display=swap" rel="stylesheet">
	<meta charset="ISO-8859-1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
	<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
	<title>Search & Download</title>
	<style>
		body{
			font-family: 'Signika', sans-serif;
		}
		h1{
			text-align:center;
		}
		.btn{
			font-family: 'Signika', sans-serif;
			text-align:center;
			padding:10px;
			font-weight:bold;
			letter-spacing:1px;
			font-size:14px;
			border-radius:0px;
		}
		label{
			margin:10px;
        }
        input{
        	padding:10px;
        	width:300px;
        	outline:none;
        	border-radius:0px;
        	border:1px solid black;
        }
        table{
        	margin:10px;
        }
        td a{
        	text-decoration:none;
        }
        .nothing{
        	text-align:center;
        	font-weight:bold;
        	color:red;
        	letter-spacing:1px;
        }
        #backbtn,.completelist{
        	margin:10px;
        }
        td:hover{
        	background-color:hsl(120deg 100% 90%);
        	transition:.8s;
        	cursor:pointer;
        }
        .back{
        	margin:20px;
        	padding:20px;
        	box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        }
	</style>
	</head>
	<body>
	    <h1 style="letter-spacing:1px;margin-top:20px;">Hostlers Details</h1>
    	<hr><br>
    	<div class="back">
		<form action="<%=request.getContextPath()%>/fetchthroughdate" method="post">
    		<label for="fetchdate">Fetch Details Through Date:</label>
    		<input type = "date" name = "date" id = "fetchdate" placeholder="Enter Date">
    		<button type="submit" class="btn btn-success"><i class='fas fa-search'></i> Search</button>
    	</form>
    	<table align="center" cellpadding="10px" cellspacing="2px" border="1" width="90%">
    	<thead>
        <tr bgcolor="honeydew">
            <th>Name</th>
            <th>Roll Number</th>
            <th>Hostel Name</th>
            <th>Time Out</th>
            <th>Time In</th>
            <th>Place To Go</th>
        </tr>
     	</thead>
     <c:if test = "${fn:length(hostlers) >= 1}">
     <tbody>
     	<c:forEach var="hostler" items="${hostlers}">
        <tr>
            <td><c:out value='${hostler.name}' /></td>
            <td><c:out value='${hostler.rollnumber}' /></td>
            <td><c:out value='${hostler.hostelname}' /></td>
            <td><c:out value='${hostler.datetime_out}' /></td>
            <c:if test = "${hostler.datetime_in != null}">
            	<td><c:out value='${hostler.datetime_in}' /></td>
            </c:if>
            <c:if test = "${hostler.datetime_in == null}">
            	<td style="color:red;background-color:hsl(0deg 78% 79%);"><c:out value='Not in Till now'/></td>
            </c:if>
            <td><c:out value='${hostler.placetogo}' /></td>
        </tr>
        </c:forEach>
	 </tbody>
     </c:if>
	<c:if test = "${fn:length(hostlers) == 0}">
	<tbody>
		<tr>
			<td colspan="9" class="nothing">Nothing To Show Here!!</td>
		</tr>
	</tbody>
	</c:if>
	<tfoot>
		<tr bgcolor="honeydew">
            <th>Name</th>
            <th>Roll Number</th>
            <th>Hostel Name</th>
            <th>Time Out</th>
            <th>Time In</th>
            <th>Place To Go</th>
        </tr>
	</tfoot>
    </table>
    <a href="<%=request.getContextPath()%>/list" id="backbtn"><button class="btn btn-success">
	<i class='fas fa-table'></i> Back To Table</button></a>
	<button class="btn btn-success" onclick="window.print()"><i class='fas fa-print'></i> Print this page</button>
	</div>
	</body>
</html>