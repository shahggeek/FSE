<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FSE Assignment</title>
<style>
.center {
    text-align: center;
    border: 3px solid black;
    padding: 10px 0;
    color : red;
}
</style>
</head>
<body>
<div class="center">13_SpringBoot_Assignment1 (Converting 10_Spring_Assignment4 to SpringBoot)</div>
<p></p>
<hr>
<div>
	<h3> Add Subject</h3>
	 <h4> Please add Book for same Subject if interested </h4>
	<form:form action="addSubject" modelAttribute="subject" method="post">
		<table>
         <tr>
             <td>
                 <form:label path="subtitle">Subject Title</form:label>
             </td>
             <td>
                 <form:input path="subtitle" id="subtitle" />
             </td>
         </tr>
         <tr>
             <td>
                 <form:label path="durationInHrs">Duration In Hrs</form:label>
             </td>
             <td>
                 <form:input path="durationInHrs" id="durationInHrs" />
             </td>
         </tr>
        
         <table>
	        <c:forEach var="app" items="${subject.references}" varStatus="idx">
	        <tr>
		        <td><form:label path="references[${idx.index}].title">Title</form:label></td>
		        <td><form:input path="references[${idx.index}].title" /></td>
	        <tr>
	    	<tr>
		        <td><form:label path="references[${idx.index}].price">Book Price</form:label></td>
		        <td><form:input path="references[${idx.index}].price" /></td>
	        <tr> 
	        <tr>
		        <td><form:label path="references[${idx.index}].volume">Book Volume</form:label></td>
		        <td><form:input path="references[${idx.index}].volume" /></td>
	        <tr> 
	        <tr>
		        <td><form:label path="references[${idx.index}].publishDate">Book Publish Date:</form:label></td>
		        <td><form:input path="references[${idx.index}].publishDate" /></td>
	        <tr> 
	        </c:forEach>
     	 </table>
      </table>	
         <input type="submit" value="SubmitSubject">
    </form:form>
    
    <hr>
    <h3> Add Book</h3>
    <form:form action="addBook" modelAttribute="book" method="post">
			<table>
            <tr><td>Select Subject to which Book Should be added</td></tr>
        	<tr>
                <td><form:select path="bookId" items="${allSubjects}" itemValue="subjectId" itemLabel="subtitle" /></td>
            </tr>
         	</table>
         	<table>
	        <tr>
		        <td><form:label path="title">Title</form:label></td>
		        <td><form:input path="title" /></td>
	        <tr>
	    	<tr>
		        <td><form:label path="price">Book Price</form:label></td>
		        <td><form:input path="price" /></td>
	        <tr> 
	        <tr>
		        <td><form:label path="volume">Book Volume</form:label></td>
		        <td><form:input path="volume" /></td>
	        <tr> 
	        <tr>
		        <td><form:label path="publishDate">Book Publish Date:</form:label></td>
		        <td><form:input path="publishDate" /></td>
	        <tr> 
     	 </table>
      </table>	
         <input type="submit" value="Submit">
    </form:form>
    <hr>
    <h3>Select Subject To Delete</h3>
    <form:form method = "POST" modelAttribute="subject" action = "/DeleteSubject">
         <table>
        	<tr>
                <td><form:select path="subjectId" items="${allSubjects}" itemValue="subjectId" itemLabel="subtitle" /></td>
                <td>
                  <input type = "submit" value = "Delete Subject"/>
               </td>
            </tr>
         </table>  
      </form:form>
      <hr>
      <h3>Select Book To Delete</h3>
       <form:form method = "POST" modelAttribute="book" action = "/DeleteBook">
         <table>
        	<tr>
                <td><form:select path="bookId" items="${allBooks}" itemValue="bookId" itemLabel="title" /></td>
                  <td>
                  <input type = "submit" value = "Delete Book"/>
               </td>
            </tr>
         </table>  
      </form:form>
      <hr>
      <h3>Search Subject</h3>
      <form:form method = "POST" modelAttribute="subject" action = "/SearchSubject">
         <table>
          <tr>
		        <td><form:label path="subtitle">Search Subject Title</form:label></td>
		        <td><form:input path="subtitle" /></td>
		         <td>
                  <input type = "submit" value = "Search Subject"/>
               </td>
	        <tr>
         </table>  
      </form:form>
      <c:if test="${not empty searchSubject.subtitle}">
   			 <h3>Subject Found</h3>
   			  <p>Subject Title : ${searchSubject.subtitle} <br> Subject DurationInHrs: ${searchSubject.durationInHrs}  </p>
	 </c:if>
      <hr>
       <h3>Search Book</h3>
       <form:form method = "POST" modelAttribute="book" action = "/SearchBook">
         <table>
          <tr>
		        <td><form:label path="title">Search Book Title</form:label></td>
		        <td><form:input path="title" /></td>
		        <td>
                  <input type = "submit" value = "Search Book"/>
               </td>
	        <tr>
         </table>  
      </form:form>
     
	  <c:if test="${not empty searchBook.title}">
   			 <h3>Book Found</h3>
   			  <p>Book Title : ${searchBook.title} <br> Book Price : ${searchBook.price} <br> Book Volume: ${searchBook.volume}</p>
	 </c:if>
      <hr>
</div>
</body>
</html>