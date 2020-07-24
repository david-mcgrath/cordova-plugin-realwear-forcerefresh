var exec = require('cordova/exec');

exports.forceRefresh = function (success, error) {
    exec(success, error, 'realwearforcerefresh', 'forceRefresh', []);
};