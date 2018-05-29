requirejs.config({
  baseUrl: 'http://'+window.location.host + '/assets'
});

define(["js/app-start"], function (App) {
  var App = new App();
  App.populateUserInfo();
});