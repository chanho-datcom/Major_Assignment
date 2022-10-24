const mongoose = require('mongoose');
var dbURI = 'mongodb+srv://my_atlas_user:1234@cluster0.ziuoh.mongodb.net/Loc8r';
mongoose.connect(dbURI, {uesNewUrIParser: true});
var gracefulShutdown = function (msg, callback) {
    mongoose.connection.close(function () {
        console.log('mongoose disconnected throught ' + msg);
        callback();
    })
}





mongoose.connection.on('connected', function () {
    console.log('mongoose connected to ' + dbURI);
});
mongoose.connection.on('error', function (err) {
    console.log('mongoose connection error:' + err);
});
mongoose.connection.on('disconnected', function () {
    console.log('mongoose disconnected');
});



process.once('SIGUSR2', function () {
    gracefulShutdown('nodemon restart', function () {
        process.kill(process.pid, 'SIGUSR2');
    });
});
process.on('SIGINT', function () {
    gracefulShutdown('app termination', function () {
        process.exit(0);
    });
});
process.on('SIGTERM', function () {
    gracefulShutdown('heroku app shutdown', function () {
        process.exit(0);
    });
});

require('./locations')