function save(item){
    var playlistarray = getStoreArray("playlist");
    playlistarray.push(item);
    localStorage.setItem("playlist", JSON.stringify(playlistarray));
}

function loadplaylist() {
    var playlistarray = getSavedSongs();
    var ul = document.getElementById("playlist");
    if(playlistarray != null){
        for(var i =0; i < playlistarray.length; i++) {
            var li = document.createElement("li");
            li.innerHTML = playlistarray[i];
            ul.appendChild(li);
        }
    }
}

function getSavedSongs() {
    return getStoreArray("playlist");
}

function getStoreArray(key){
    var playlistarray = localStorage.getItem(key);
    if(playlistarray == null || playlistarray == ""){
        playlistarray = new Array();
    }
    else {
        playlistarray = JSON.parse(playlistarray);
    }
    return playlistarray;
}