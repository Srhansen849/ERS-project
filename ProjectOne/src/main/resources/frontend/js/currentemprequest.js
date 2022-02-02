
 window.onload=function(){
	console.log("js is connected");
	//getInfo();
	getSessionEmployee();
	getEmployees();
	getPendingRequests();
	/*document.getElementById('submit').addEventListener('click', sendData);*/
}


 
 let sessemp;
 
 let ptableInfo = [];
 let etableInfo = [];
 let empList = [];
 
 //this is gettting the current session user
 function getSessionEmployee(){
	sessemp = new XMLHttpRequest();
	
	sessemp.onreadystatechange = function(){
		
		if(sessemp.readyState == 4 && sessemp.status == 200){
			let emp = JSON.parse(sessemp.responseText);
			sessemp=emp;
			console.log("Current Employee");
			console.log(emp);
		}
	}
	
	sessemp.open("GET", "http://localhost:9065/employee/sessemp");
	sessemp.send();

	
}


/*function filterPendingRequest(){
	let empid = document.getElementById('empid');
	let rows = document.getElementById('pendingRequests').getElementsByTagName('tbody')[0].rows;
	
	empid.addEventListener('keyup', function(event){
		
	});
}*/

//posts the list of pending request
function getPendingRequests(){
	
	let req = new XMLHttpRequest();
	
	req.open("GET", "http://localhost:9065/request/manpend");
	
	req.onreadystatechange = function(){
		//console.log(req);
		/*console.log("req.readyState");
		console.log(req.readyState);
		console.log("req.status");
		console.log(req.status);*/
		if(req.readyState == 4 && req.status >= 200 && req.status < 300){
			let pendarry = JSON.parse(req.responseText);
			ptableInfo = pendarry;
			console.log("ptableinfo");
			console.log(ptableInfo);
			insertPendingRequest();
		}
	}
	
	req.send();
	
}

//creates rows for pending requests
function insertPendingRequest(){
	
	let table = document.getElementById("pendingRequests");
	
	for(let i=0; i<ptableInfo.length; i++){
		for(let j=0; j<empList.length; j++){
			if(empList[j]==ptableInfo[i].employeeid){
				let type = "";
				if(ptableInfo[i].typeid==1){
					type += 'Travel';
				}else if(ptableInfo[i].typeid==2){
					type += 'Food';
				}else if(ptableInfo[i].typeid==3){
					type += 'Hotel';
				}else if(ptableInfo[i].typeid==4){
					type += 'Training';
				}else if(ptableInfo[i].typeid==5){
					type += 'Conference';
				}else if(ptableInfo[i].typeid==6){
					type += 'Other';
				}	
				
				let t = "";
				let tr = "<tr>";
				//tr += "<td> <input type='radio' name='rid'> </td>";
				tr += "<td>" + ptableInfo[i].requestid + "</td>";
				tr += "<td>" + ptableInfo[i].employeeid + "</td>";
				tr += "<td>" + ptableInfo[i].title + "</td>";
				tr += "<td>" + ptableInfo[i].description + "</td>";
				tr += "<td>" + ptableInfo[i].amount + "</td>";
				tr += "<td>" + ptableInfo[i].submition + "</td>";
				tr += `<td>${type}</td>`;
				tr += "<td>Pending</td>";
				t += tr;
				table.innerHTML += t;
			}
		}
	}
	
	/*for(let i=0; i<ptableInfo.length; i++){
		
		let type = "";
		if(ptableInfo[i].typeid==1){
			type += 'Travel';
		}else if(ptableInfo[i].typeid==2){
			type += 'Food';
		}else if(ptableInfo[i].typeid==3){
			type += 'Hotel';
		}else if(ptableInfo[i].typeid==4){
			type += 'Training';
		}else if(ptableInfo[i].typeid==5){
			type += 'Conference';
		}else if(ptableInfo[i].typeid==6){
			type += 'Other';
		}	
		
		let t = "";
		let tr = "<tr>";
		//tr += "<td> <input type='radio' name='rid'> </td>";
		tr += "<td>" + ptableInfo[i].requestid + "</td>";
		tr += "<td>" + ptableInfo[i].title + "</td>";
		tr += "<td>" + ptableInfo[i].description + "</td>";
		tr += "<td>" + ptableInfo[i].amount + "</td>";
		tr += "<td>" + ptableInfo[i].submition + "</td>";
		tr += `<td>${type}</td>`;
		tr += "<td>Pending</td>";
		t += tr;
		table.innerHTML += t;
	}*/
	
}

//posts the list of Employees
function getEmployees(){
	
	let emp = new XMLHttpRequest();
	
	emp.open("GET", "http://localhost:9065/manager/employee");
	
	emp.onreadystatechange = function(){
		//console.log(req);
		if(emp.readyState == 4 && emp.status >= 200 && emp.status < 300){
			let emparry = JSON.parse(emp.responseText);
			etableInfo = emparry;
			console.log("etableinfo");
			console.log(etableInfo);
			insertEmployees();
		}
	}
	
	emp.send();
}

//creates rows for employees
function insertEmployees(){
	
	let table = document.getElementById("employeetable");
	
	
	for(let i=0; i<etableInfo.length; i++){
		empList[i] = etableInfo[i].employeeid;
		console.log("empList");
		console.log(empList);
		let t = "";
		let tr = "<tr>";
		//tr += "<td> <input type='radio' name='eid' value='employeeID'> </td>";
		tr += "<td>" + etableInfo[i].employeeid + "</td>";
		tr += "<td>" + etableInfo[i].firstname + "</td>";
		tr += "<td>" + etableInfo[i].lastname + "</td>";
		tr += "<td>" + etableInfo[i].email + "</td>";
		t += tr;
		table.innerHTML += t;
	}
	
}


function sendData(event){
	console.log(event.target);
	let xhttp = new XMLHttpRequest();
	let reqid = document.getElementById("reqid").value;
	let comment = document.getElementById("comment").innerHTML;
	let status = document.getElementById("status").value;
	
	let data = {reqid, comment, status};
	
	//sends the data as a JSON in the body of the request
	xhttp.send(JSON.stringify(data));
}

/*function filterRequestTable(){
	
	let rad = document.getElementsByName('eid');
	console.log(rad);
	rad.forEach(radio =>{
		if(radio.checked){
			console.log(radio.value);
		}
	});
	
}*/
