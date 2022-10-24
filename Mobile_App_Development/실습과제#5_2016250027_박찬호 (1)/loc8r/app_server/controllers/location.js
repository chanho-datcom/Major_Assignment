const homelist = (req, res) => {
    res.render('location-list', 
    {title:'Loc8r - Find places to work with wifi',
     pageHeader:{
         title:'Loc8r',
         strapline:'Find places to work with wifi near you!'
    }
});
};

const locationInfo = (req, res) => {
    res.render('location-info', {title: 'Location info' });
};

const addReview = (req, res) => {
    res.render('location-review-form', {title:'Add review'});
};

module.exports ={
    homelist,
    locationInfo,
    addReview
};
