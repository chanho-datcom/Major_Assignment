function handlebuttonclick2(){
    alert("clear!!!")
    cl = document.getElementById("playlist");
    while(cl.hasChildNodes){
    cl.removeChild(cl.firstChild);
    }

}