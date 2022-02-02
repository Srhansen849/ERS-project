/**
 * 
 */
 
 window.onload=function(){
	console.log("js is connected");
	//getInfo();
	getSessionEmployee();
	getTypeList();
	document.getElementById('submit').addEventListener('click', sendData);
}
 
 let sessemp;
 
 let listInfo = [];
  
 //this is gettting the current session user
 function getSessionEmployee(){
	sessemp = new XMLHttpRequest();
	
	
	sessemp.onreadystatechange = function(){
		
		if(sessemp.readyState == 4 && sessemp.status == 200){
			let emp = JSON.parse(sessemp.responseText);
			sessemp=emp;
			console.log(emp);
		}
	}
	
	sessemp.open("GET", "http://localhost:9065/employee/sessemp");
	sessemp.send();

	
}

//this is getting the type list from java
function getTypeList(){
	
	let req = new XMLHttpRequest();
	
	req.open("GET", "http://localhost:9065/request/types");
	
	req.onreadystatechange = function(){
		//console.log(req);
		if(req.readyState == 4 && req.status >= 200 && req.status < 300){
			let listarry = JSON.parse(req.responseText);
			listInfo = listarry;
			console.log(listInfo);
			insertTypeList();
		}
	}
	
	req.send();
	
}


//this will create the list of type in the drop down
function insertTypeList(){
	let typename = document.getElementById('type');
	
	for (let i = 0; i<listInfo.length; i++){
		let j = i+1;
		//let type = document.createTextNode(listInfo[i].typename);
		console.log(type);
		typename.innerHTML += `<option value=${j}>` + listInfo[i].typename + "</option>";
	}
}



//this will send the data input to the database
function sendData(event){
	console.log(event.target);
	let xhttp = new XMLHttpRequest();
	let title = document.getElementById("title").value;
	let amount = document.getElementById("amount").value;
	let type = document.getElementById("type").value;
	let description = document.getElementById("description").innerHTML;
	
	let data = {title, description, amount, type};
	
	//sends the data as a JSON in the body of the request
	xhttp.send(JSON.stringify(data));
}





/*async function getInfo(){
	try{
		let response = await getSessionEmployee();
		getTypeList(response);
	}
	catch(error){
		console.log(error);
	}
}*/



