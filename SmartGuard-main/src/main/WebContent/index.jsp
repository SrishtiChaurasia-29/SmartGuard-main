<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Crimson+Text&family=Signika&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" 
    integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>
    <title>Hostel Checkin-Checkout</title>
    <style>
    	body{
    		font-family: 'Signika', sans-serif;
    		margin:0;
    	}
    	#detail-success{
    		color:green;
    		letter-spacing:1px;
    		font-size:16px;
    		font-weight:bold;
    	}
    	#detail-failure{
    		color:red;
    		letter-spacing:1px;
    		font-size:16px;
    		font-weight:bold;
    	}
    	span{
    		font-weight:bold;
    	}
        .hideme{
        	display:none;
        }
        #main{
        	height:100vh;
			display: flex;
    		justify-content: center;
    		align-content: center;
    		flex-direction: column;
    		align-items: center;
        }
        input{
        	padding:10px;
        	outline:none;
        	border-radius:0px;
        	border:1px solid black;
        }
        button{
        	font-family: 'Signika', sans-serif;
        	padding:10px;
        	font-weight:bold;
        	letter-spacing:1px;
			font-size:14px;
        }
        .btn{
			border-radius:0px;
			}
        label{
        	font-size:20px;
        }
        .details{
        	display:flex;
        	padding:15px;
        	box-shadow: rgba(100, 100, 111, 0.2) 0px 7px 29px 0px;
        }
        .setting{
        	margin:10px;
        }
        img{
        	padding:10px;
        }
        .back{
        	margin:20px;
        	padding:20px;
        	box-shadow: rgba(0, 0, 0, 0.35) 0px 5px 15px;
        }
    </style>
</head>
<body>
<div id="main">
	<div class="back">
	<form action="<%=request.getContextPath()%>/fetchdetails" method="post">
		<label for="rollnumber">Enter Your RollNumer:</label>
		<input type = "number" name = "rollnumber" id = "rollnumber" autocomplete="off">
		<button type="submit" class="btn btn-success"><i class='fas fa-search'></i> Check</button>
	</form>
	<c:if test="${hostler != null}">
	<p id="detail-success">Details for rollnumber <c:out value='${hostler.rollnumber}' /> is shown below</p>
	<div class="details">
    	<div class="setting">
    		<p><span>Name:</span> <c:out value='${hostler.name}' /></p>
    		<p><span>Course:</span> <c:out value='${hostler.course}' /></p>
    		<p><span>Branch:</span> <c:out value='${hostler.branch}' /></p>
    		<p><span>Year:</span> <c:out value='${hostler.year}' /></p>
    		<p><span>Hostel Name:</span> <c:out value='${hostler.hostelname}' /></p>
    	</div>
    	<img src="data:image/jpg;base64,${hostler.base64image}" height="200px" width="150px"/>
    </div>
    <br>
    <div id="showoncheck" class="showoncheck">
    <form action="<%=request.getContextPath()%>/checkoutinsert" method="post">
    	<label for="placetogo">PlaceToGo:</label>
    	<input type="text" name="placetogo" id="placetogo" autocomplete="off">
    	<input type = "number" name = "hid" value="<c:out value='${hostler.id}' />" class="hideme">
    	<button type="submit" class="btn btn-success"><i class="fas fa-sign-out-alt"></i> Checkout</button>
    </form>
    </div>
    </c:if>
    <c:if test="${hostler == null }">
    	<p id="detail-failure">No Data Found for this RollNumber</p>
    </c:if>
    <a href="<%=request.getContextPath()%>/listhostler"><button class="btn btn-success"><i class='fas fa-table'></i> Go To Table</button></a>
    </div>
</div>
    <script>
    	
    </script>
</body>


</html>