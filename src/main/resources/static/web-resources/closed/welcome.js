$(document).ready(function () {
  var usrRequest = $.ajax({
    url: "/user/create",
    type: "POST",
    data: {}
  });
  usrRequest.done(function (userInformation) {
    $("#user-name-label").text(userInformation.name);
  })
  .fail(function (jqXHR, textStatus) {
    alert("Cannot create user : " + textStatus)
  });

  $("#user-logout").click(function () {
    window.location.href = "http://localhost:8080/logout";
  });
});