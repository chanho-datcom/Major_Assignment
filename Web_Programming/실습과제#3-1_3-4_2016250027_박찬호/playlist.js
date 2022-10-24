window.onload = init;
function init(){
    var button = document.getElementById("addbutton");
    button.onclick = handlebuttonclick;
    var button2 = document.getElementById("cb");
    button2.onclick = handlebuttonclick2;
    loadplaylist();
}
{
function handlebuttonclick(){
    alert("button was clicked!");
    var textinput = document.getElementById("songtextinput");
    var songname = textinput.value;
    var li = document.createElement("li");
    li.innerHTML = songname;
    var ul = document.getElementById("playlist");
    ul.appendChild(li);
    save(songname);
}
}
