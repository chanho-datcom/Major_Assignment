const express = require('express');
const router = express.Router();

const ctrlMain = require('../controllers/main');

router.get('/', ctrlMain.index);



/* GET home page. 
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express & Nodemon by Chanho Park ' });
});
*/


const homepageController = (req, res) => {
  res.render('index', {title: 'Express'});
};
router.get('/', homepageController);

module.exports = router;