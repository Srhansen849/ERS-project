/**
 * 
 */
 
 
 window.onload=function(){
	console.log("js is connected");
	//getInfo();
	getSessionEmployee();
	getHistoryRequests();
}
 
 let sessemp;
 
 let htableInfo = [];
 
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


//posts the list of history request
function getHistoryRequests(){
	
	let req = new XMLHttpRequest();
	
	req.open("GET", "http://localhost:9065/request/history");
	
	req.onreadystatechange = function(){
		//console.log(req);
		if(req.readyState == 4 && req.status >= 200 && req.status < 300){
			let pastarry = JSON.parse(req.responseText);
			htableInfo = pastarry;
			console.log("htableinfo");
			console.log(htableInfo);
			insertHistoryRequest();
		}
	}
	
	req.send();
	
}

//creates rows for history requests
function insertHistoryRequest(){
	
	let table = document.getElementById("historyRequests");
	
	for(let i=0; i<htableInfo.length; i++){
		
		let type = "";
		if(htableInfo[i].typeid==1){
			type += 'Travel';
		}else if(htableInfo[i].typeid==2){
			type += 'Food';
		}else if(htableInfo[i].typeid==3){
			type += 'Hotel';
		}else if(htableInfo[i].typeid==4){
			type += 'Training';
		}else if(htableInfo[i].typeid==5){
			type += 'Conference';
		}else if(htableInfo[i].typeid==6){
			type += 'Other';
		}		
				/*console.log("type");
				console.log(type);*/
		
		/*for(let j=0; j<htableInfo.length; j++){
			
		}*/
		let t = "";
		let tr = "<tr>";
		tr += "<td>" + htableInfo[i].requestid + "</td>";
		tr += "<td>" + htableInfo[i].title + "</td>";
		tr += "<td>" + htableInfo[i].description + "</td>";
		tr += "<td>" + htableInfo[i].amount + "</td>";
		tr += "<td>" + htableInfo[i].submition + "</td>";
		tr += "<td>" + htableInfo[i].resolution + "</td>";
		tr += `<td>${type}</td>`;
		if(htableInfo[i].statusid==3){
			tr += "<td>Approved</td>";
		}else{
			tr += "<td>Denied</td>";
		}
		tr += "<td>" + htableInfo[i].comment + "</td>";
		t += tr;
		table.innerHTML += t;
	}
	
}