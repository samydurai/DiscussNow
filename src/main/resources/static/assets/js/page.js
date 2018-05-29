requirejs.config({
  baseUrl: 'assets/libs',
  paths: {
    js: '../js'
  }
});

define(["js/app-start"], function (App) {
  var appStart = new App();
  $("#topic-alert").hide();
  appStart.loadAllTopics();
  appStart.populateUserInfo();
  $("#sign-in").click(function () {
    appStart.logIn();
  });
  $("#user-logout").click(function () {
    appStart.logOut();
  });
  $("#user-topic").click(function () {
    appStart.openModal();
  });
  $("#topic-submit").click(function () {
    appStart.saveTopic();
  });
  $("#topics").on("click", "a", function () {
    appStart.redirectToResponse($(this));
  });
});