document.getElementsByTagName("h1")[0].style.fontSize = "6vw";

function changeColor(){
  alert('button clicked');
  var title = document.getElementById("title");
  title.className = "redback";
  title.style.color = "black";
}

function changeTitle(){
   title.innerHTML = "Hrvojka's page of interests";
}

function doYellow(){
  var can=document.getElementById("canvas");
  can.style.backgroundColor="white";
  var ctx = can.getContext("2d");
  ctx.fillStyle="yellow";
  ctx.fillRect(10,10,40,40);
  ctx.fillRect(60,10,40,40);
  
  ctx.fillStyle="black";
  ctx.font="30px Arial";
  ctx.fillText("Hi",10,70);
}

function doPink(){
  var can=document.getElementById("canvas");
  can.style.backgroundColor="pink";
}