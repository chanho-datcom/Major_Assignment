const express = require('express');
const router = express.Router();

const ctrlLocations = require('../controllers/locations');
const ctrlOthers = require('../controllers/others');

//Locations pages
router.get('/', ctrlLocations.homelist);
router.get('/locations/:locationid', ctrlLocations.locationInfo);
router
    .route('/locations/:locationid/reviews/new')
    .get(ctrlLocations.addReview)
    .post(ctrlLocations.doAddReview);

//others pages
router.get('/about', ctrlOthers.about);

module.exports = router;