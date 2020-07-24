# cordova-plugin-realwearforcerefresh
A simple Cordova plugin to trigger a refresh for RealWear WearML commands.
Currently they don't automatically update when the page changes, so SPAs don't work correctly.

This plugin simply creates a button in the Android activity and then immediately removes it. The changes to the activity are detected and then the WearML commands are refreshed.
Hopefully this workaround won't be required in the long term.

# Usage
Whenever a refresh is required, call `window.cordova.plugins.realwearforcerefresh.forceRefresh(success, error)`.
I've just been piggybacking on RealWear's wearml_engine.js getCommands method, which gets called whenever the DOM changes.
To do so, wherever your app is initialised you can just add something like:
'''javascript
if (wearML &&
    wearML.getCommands &&
    window.cordova &&
    window.cordova.plugins &&
    window.cordova.plugins.realwearforcerefresh) {
  function success() {
    console.log("WearML commands refreshed.");
  }
  function error(error) {
    console.error("WearML command refresh failed.");
    console.error(error);
  }

  var prev = wearML.getCommands;
  wearML.getCommands = function () {
    prev();
      window.cordova.plugins.realwearforcerefresh.forceRefresh(
        success,
        error);
  }
}
'''
