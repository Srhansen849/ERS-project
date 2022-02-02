/**
 * 
 */
 
 window.onload=function(){
	console.log("js is connected");
	//getInfo();
	getSessionEmployee();
	getTypeList();
	getPendingRequests();
	getRejectedRequests();
	/*getRole();*/
	document.getElementById('submit').addEventListener('click', sendData);
}
 
 let sessemp;

 
 let ptableInfo = [];
 let rtableInfo = [];
 let listInfo = [];
 
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

function getTypeList(){
	
	let req = new XMLHttpRequest();
	
	req.open("GET", "http://localhost:9065/request/types");
	
	req.onreadystatechange = function(){
		//console.log(req);
		if(req.readyState == 4 && req.status >= 200 && req.status < 300){
			let listarry = JSON.parse(req.responseText);
			listInfo = listarry;
			console.log("listInfo");
			console.log(listInfo);
			insertTypeList();
		}
	}
	
	req.send();
}

//posts the list of pending request
function getPendingRequests(){
	
	let req = new XMLHttpRequest();
	
	req.open("GET", "http://localhost:9065/request/pending");
	
	req.onreadystatechange = function(){
		//console.log(req);
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
		/*for(let j=0; j<ptableInfo.length; j++){
			if(listInfo[j].typeid == ptableInfo[i].typeid){
				type += listInfo[j].typename;
				break;
				console.log("type");
				console.log(type);
			}
			else{
				continue;
			}
		}*/
		
		
		let t = "";
		let tr = "<tr>";
		tr += "<td>" + ptableInfo[i].requestid + "</td>";
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

//posts the list of rejected request
function getRejectedRequests(){
	
	let req = new XMLHttpRequest();
	
	req.open("GET", "http://localhost:9065/request/rejected");
	
	req.onreadystatechange = function(){
		//console.log(req);
		if(req.readyState == 4 && req.status >= 200 && req.status < 300){
			let rejearry = JSON.parse(req.responseText);
			rtableInfo = rejearry;
			console.log("rtableinfo");
			console.log(rtableInfo);
			insertRejectedRequest();
		}
	}
	
	req.send();
}

//creates rows for rejected requests
function insertRejectedRequest(){
	
	let table = document.getElementById("rejectedRequests");
	
	
	for(let i=0; i<rtableInfo.length; i++){
		
		let type = "";
		if(rtableInfo[i].typeid==1){
			type += 'Travel';
		}else if(rtableInfo[i].typeid==2){
			type += 'Food';
		}else if(rtableInfo[i].typeid==3){
			type += 'Hotel';
		}else if(rtableInfo[i].typeid==4){
			type += 'Training';
		}else if(rtableInfo[i].typeid==5){
			type += 'Conference';
		}else if(rtableInfo[i].typeid==6){
			type += 'Other';
		}
		/*for(let j=0; j<rtableInfo.length; j++){
			if(listInfo[j].typeid == rtableInfo[i].typeid){
				type = listInfo[j].typename;
				break;
				console.log("type");
				console.log(type);
			}
			else{
				continue;
			}
		}*/
		let t = "";
		let tr = "<tr>";
		tr += "<td>" + rtableInfo[i].requestid + "</td>";
		tr += "<td>" + rtableInfo[i].title + "</td>";
		tr += "<td>" + rtableInfo[i].description + "</td>";
		tr += "<td>" + rtableInfo[i].amount + "</td>";
		tr += "<td>" + rtableInfo[i].submition + "</td>";
		tr += "<td>" + rtableInfo[i].resolution + "</td>";
		tr += `<td>${type}</td>`;
		tr += "<td>Rejected</td>";
		tr += "<td>" + rtableInfo[i].comment + "</td>";
		t += tr;
		table.innerHTML += t;
	}
	
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



/*function getRole(){
	
	let upemp = document.getElementById("update");
	let stemp = document.getElementById("stats");
	if(sessemp.roleid=3){
		upemp.style.display = "hidden";	
		stemp.style.display = "hidden";	
	}
	
}*/
 