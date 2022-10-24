var lastTime = 0;
window.onload = function () {
   
    HandleRefresh();

}

function HandleRefresh() {
    var url = "http://openapi.seoul.go.kr:8088/584164474b636b7339344a646e4f47/json/CardSubwayStatsNew/1/567/20191113";
        
    $.getJSON(url, updateData);
}

function updateData(str) {
    var data = str.CardSubwayStatsNew.row;
    var dataDiv = document.getElementById("dataDiv");
    for (var i = 0; i < data.length; i++) {
        var div = document.createElement("div");
        div.setAttribute("class", "dataItem");
        div.innerHTML = data[i].USE_DT + "일자 " + data[i].SUB_STA_NM + ", " + data[i].RIDE_PASGR_NUM + "명 탑승, " + data[i].ALIGHT_PASGR_NUM + "명 하차";
        dataDiv.appendChild(div);
    }
} 