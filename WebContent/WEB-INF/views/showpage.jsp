<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
</head>


<script>
function clearData(){
	 $('#data').empty();
	 $('#total').empty();
	 $('#page').empty();
	 $('#row').empty();
	 $('#records_table').empty();
	 
}
///// Display data//////
function dataDisplay(msg){
	$('#total').html('Total : '+ msg.total);
	$('#page').html('Current page : '+ msg.currentPage);
	$('#row').html('Row per page :' + msg.size);
	
	
	var trHTML = '';
   $.each(msg.currentDepartmentList, function (i, item) {
       trHTML += '<tr><td>' + item.deptName + '</td><td>' + item.deptEmail;
   });
   $('#records_table').append(trHTML);	
}


/////////////////  Click GO!  /////////////////////////////////////

$(document).ready(function(){
	$("#next_btn").hide();
	$("#pre_btn").hide();
	$('#go_btn').on('click', function(){
		$("#next_btn").hide();
		$("#pre_btn").hide();
		$("#paragraphId").hide();
		clearData();
		
		//var deptId = $("#textId").value;
		
		$.ajax({
             type: "GET",
             dataType: "json",
           //  url: "http://localhost:8080/ZNumber/getResult/"+deptId
             url: "http://localhost:8080/ZNumber/getResult/"
             //data:{deptName : 'der', deptEmail: 'der@gmail.com'}
             //data:{"numbers" : numbers}
         }).done(function(msg) {
         	dataDisplay(msg);
         			
         	if(parseInt(msg.total) / parseInt(msg.rowPerPage) <= parseInt(msg.currentPage)){
         		$("#next_btn").hide();
         	}else{
         		$("#next_btn").show();
         	}
         });
    });
});

/////////////////////  Click Previous!  /////////////////////////////////////
	$(document).ready(function() {
		$('#pre_btn').on('click', function() {
			
			$("#next_btn").show();
			clearData();
			$.ajax({
				type : "GET",
				dataType : "json",
				url: "http://localhost:8080/ZNumber/getPrePageResult",
				
			}).done(function(msg) {
				
				dataDisplay(msg);
				if (1 == parseInt(msg.currentPage)) {
					$("#pre_btn").hide();
				}
			});

		});
	});
	
////////////////// Click Next /////////////////////////////	
	$(document).ready(function() {
		$('#next_btn').on('click', function() {
		$("#pre_btn").show();
			clearData();
	           $.ajax({
	                type: "GET",
	                dataType: "json",
	                url: "http://localhost:8080/ZNumber/getNextPageResult",
	            }).done(function(msg) {
	            	dataDisplay(msg);
	            	if(parseInt(msg.total) / parseInt(msg.rowPerPage) <= parseInt(msg.currentPage)){
	            		$("#next_btn").hide();
	            	}
	            });
	    });
	});
	
/////////////////////////////////////////////////////////////
//*********** Get single Department **********//

$(document).ready(function() {
		$('#getSingle_btn').on('click', function() {
		    var deptId = $('#number').val();
		    
			$.ajax({
	                type: "GET",
	                dataType: "json",
	                url: "http://localhost:8080/ZNumber/getResult/" + deptId,
	            }).done(function(msg) {
	            	$("#jasonMessage").append(msg.deptName);	            	
	            });
	    });
	});

///////////////////////////////////////////////////////////
//********** Save new Department **************//


$(document).ready(function() {
		$('#saveDept_btn').on('click', function() {
			$("#paragraphId").hide();
			//alert("Mouse up over p1!");
			var deptName = $('#DeptName').val();
			var deptEmail = $('#DeptEmail').val();
			$.ajax({
	                type: "POST",
	                //dataType: "json",
	                url: "http://localhost:8080/ZNumber/save",
	                data:{
	                	  "DepartmentName" : deptName ,
	                	  "DepartmentEmail": deptEmail
	                     }    
			}).done( 
					$("#addSuccess").append("Congrast!! ")

	            );
	    });
	});


//********** (pass by JSON) Save new Department **************//


$(document).ready(function() {
		$('#saveDept_byJson_btn').on('click', function() {
			$("#paragraphId").hide();
			alert("Mouse up over p1!");
			var deptNameGet = $('#DeptName').val();
			var deptEmailGet = $('#DeptEmail').val();
			
						
			var jsonObjects = [{deptName:deptNameGet, deptEmail:deptEmailGet},
			                   {deptName:'TryList', deptEmail:'TryList@gmail.com'}
			                  ];
			//alert(JSON.stringify(jsonObjects))
			
			$.ajax({
	                type: "POST",
	                //dataType: "json",
	                url: "http://localhost:8080/ZNumber/saveJson",
	                contentType: 'application/json; charset=utf-8',
	                data:JSON.stringify(jsonObjects)
	                	   
	                      
			        	
	                
			}).done( 
					$("#addSuccess").append("Congrast!! ")

	            );
	    });
	});



	
</script>



<body>
 <center>
		<div>
			<h3>All Departments</h3>

			<button name="go_btn" id="go_btn">GO</button>
			<button name="pre_btn" id="pre_btn">Previous</button>
			<button name="next_btn" id="next_btn">Next</button>
			<button name="getSingle_btn" id="getSingle_btn"> GetSingle</button>
			<button name="saveDept_btn" id="saveDept_btn">Save new Department</button>
			<button name="saveDept_byJson_btn" id="saveDept_byJson_btn">Save new Department by Json</button>
		</div>

		<table id="records_table" border='1'>
			<tr>
				<th>Department Name</th>
				<th>Department Email</th>
			</tr>
		</table>

		
		<div>
		 <input type="number" name="number" id="number"/>
		</div>

		
		
		<from id="ajaxRestForm" name = "ajaxRestFrom">
		  Department Name: <input type="text" name="DepatName" id="DeptName"/>
		  Department Email: <input type="email" name="DeptEmail" id="DeptEmail"/>
		</from>
		
		

	<div name="total" id="total"></div>
	<div name="page" id="page"></div>
	<div name="row" id="row"></div>
	<p id="paragraphId">This is a paragraph.</p>
	<p>Get Single Department: <span id="jasonMessage"></span></p>
	<p id="addSuccess">Add successfully:</p>
</center>


</body>
</html>
