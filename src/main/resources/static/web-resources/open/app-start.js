$(document).ready(function () {
  $("#google-signin").click(function () {
    window.location.href = "http://localhost:8080/login";
  });

  $.get("/userInfo", function (auth) {
    if (auth.authenticated) {
      window.location.href = "http://localhost:8080/web-resources/closed/welcome.html"
    }
  });
});