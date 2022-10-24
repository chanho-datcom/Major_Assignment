function getTimeFromString(timeString) {
    var theTime = new Date()
    var time = timeString.match(/(\d+)(?::(\d\d))?\s*(p?)/);
    theTime.setHours(parseInt(time[1]) + (time[3] ? 12 : 0));
    theTime.setMinutes(parseInt(time[2]) || 0);
    return theTime.getTime();
}


function Movie(title, genre, rating, showtimes){
    this.title = title;
    this.genre = genre;
    this.rating = rating;
    this.showtimes = showtimes;
    this.getNextShowing = function(){
        var now = new Date().getTime();

        for(var i = 0; i < this.showtimes.length; i++){
            var showtime = getTimeFromString(this.showtimes[i]);
            if((showtime - now) > 0){
             return "Next showing of " + this.title + " is " + this.showtimes[i];
        }
    }
    return null;
    };
}

var banzaiMovie = new Movie("buckaroo banzai", 
                            "cult classic", 
                            5, 
                            ["1:00pm", "5:00pm", "7:00pm", "11:00pm"]);
                            
var plan9Movie = new Movie("Plan 9 from Outer Space", 
                            "cult classic", 
                            2, 
                            ["3:00pm", "7:00pm", "11:00pm"]);


var forbiddenplanetMovie = new Movie("Forbidden Planet", 
                                    "cult Sci-fi", 
                                    5, 
                                    ["5:00pm", "10:00pm"]);

alert(banzaiMovie.getNextShowing() + " 2016250027박찬호");
alert(plan9Movie.getNextShowing() + " 2016250027박찬호");
alert(forbiddenplanetMovie.getNextShowing() + " 2016250027박찬호");
