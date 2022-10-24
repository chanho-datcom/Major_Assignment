var watchId = null;
var map = null;
window.onload = getMyLocation;

function getMyLocation() {
    if(navigator.geolocation){
        var watchButton = document.getElementById("watch");
        watchButton.onclick = watchLocation;
        var clearWatchButton = document.getElementById("clearWatch");
        clearWatchButton.onclick = clearWatch;
    } else{
        alert("Oops, no geolocation support");
    }
}


function displayLocation(position){
    var latitude = position.coords.latitude;
    var longitude = position.coords.longitude;
    
    var div = document.getElementById("location");
    div.innerHTML = "Your are at Latitude: " + latitude + ", longitude: " + longitude;
    div.innerHTML += " (with " + position.coords.accuracy + "meters accuracy)";

    if (map == null) {
        showMap(position.coords);
    } else{
      scrollMapToPosition(position.coords);
   }
}

function showMap(coords){
    var kakaoLatAndLong = new daum.maps.LatLng(coords.latitude, coords.longitude);

    var mapOptions = {
        zoom: 10,
        center: kakaoLatAndLong,
        mapTypeId: daum.maps.MapTypeId.ROADMAP
    };
    var mapDiv = document.getElementById("map");
    map = new daum.maps.Map(mapDiv, mapOptions);

    
}

function addMarker(map, latlong, title, content){
    var markerOptions = {
        position: latlong,
        map: map,
        title: title,
        clickable: true
    };
    var marker = new daum.maps.Marker(markerOptions);

    var infoWindowOptions = {
        content: content,
        position: latlong
    };

    var infoWindow = new daum.maps.InfoWindow(infoWindowOptions);

    daum.maps.event.addListener(marker, 'click', function(){infoWindow.open(map);});
}

//function displayMarker(locPosition){
//    var imageSrc = 'icon.png',
//    imageSize = new daum.maps.Size(64, 69),
//    imageOption = {offset : new daum.maps.Points(27, 69)};

//    var markerImage = new daum.maps.markerImage(imageSrc, imageSize, imageOption);
    
//    var marker = new daum.maps.Marker({
//        map: map,
//        position: locPosition,
//       image: markerImage
//    });

//    marker.setMap(map);
//    map.setCenter(locPosition);
//}

function displayError(error){
    var errorTypes = {
        0: "Unknown error",
        1: "Permission denied by user",
        2: "Position is not available",
        3: "Request timed out"
    };

    var errorMessage = errorTypes[errer.code];
    if(error.code == 0 || error.code == 2){
        errorMessage = errorMessage + " " + error.message;
    }
    var div = document.getElementById("location");
    div.innerHTML = errorMessage;
}

function watchLocation() {
    watchId = navigator.geolocation.watchPosition(displayLocation, displayError);
}

function scrollMapToPosition(coords){
   var latitude = coords.latitude;
   var longitude = coords.longitude;
   var latlong = new daum.maps.LatLng(latitude, longitude);

   map.panTo(latlong);

   addMarker(map, latlong, "Your new location", "You moved to: "+ latitude+"," +longitude);
}

function clearWatch() {
    if(watchId){
        navigator.geolocation.clearWatch(watchId);
        watchId != null;
    }
}