define(["js/constants",
      "js/topic/topicHandler"],
    function (constants, topicHandler) {

      function AppStart() {
        this.topicHanlder = new topicHandler();
      }

      AppStart.prototype = $.extend(Object.create(AppStart.prototype), {

        loadAllTopics: function () {
          this.topicHanlder.loadAll();
        },

        logIn: function () {
          window.location.href = constants.REDIRECT_ROUTES.LOGIN;
        },

        populateUserInfo: function () {
          $.get(constants.USER_INFO_ROUTES.USER_INFO_LOAD_PRINCIPAL,
              function (auth) {
                if (auth.authenticated) {
                  var usrRequest = $.ajax({
                    url: "/user/create",
                    type: "POST",
                    data: {}
                  });
                  usrRequest.done(function (userInformation) {
                    $("#user-name-label").text(userInformation.name);
                    $("#sign-in").hide();
                    $("#user-topic").show();
                    $("#user-logout").show();
                    $("#user-name-label").data("user", userInformation);
                  })
                  .fail(function ($XHR, textStatus) {
                    alert("Cannot create user : " + textStatus)
                  });
                }
              });
        },

        logOut: function () {
          window.location.href = constants.REDIRECT_ROUTES.LOGOUT;
        },

        openModal: function () {
          $("#add-topic-modal").modal('show');
          $("#topic-title").val("");
          $("#topic-desc").val("");
        },

        saveTopic: function () {
          var topicTitle = $("#topic-title").val(),
              topicDesc = $("#topic-desc").val(),
              topicSaveRequest = constants.TOPIC_SAVE_REQ_TEMPLATE;
          $('#add-topic-modal').modal('toggle');
          if (topicTitle.length && topicDesc.length) {
            var topicResourceObject = {}
            topicResourceObject["topicId"] = "-100";
            topicResourceObject["title"] = topicTitle;
            topicResourceObject["description"] = topicDesc
            topicSaveRequest.topic.added.push(topicResourceObject);
            var topicRequest = $.ajax({
              url: "/topic/save",
              type: "POST",
              data: JSON.stringify(topicSaveRequest),
              contentType: 'application/json; charset=utf-8',
              dataType: 'json',
              async: false
            });
            topicRequest.done(function (resp) {
              var topics = resp.topicResourceObjects;
              $.each(topics, function (index, topic) {
                var el = "<a href=\"#\" class=\"list-group-item list-group-item-action disabled\">"
                    + topic.title + "</a>";
                el = $($.parseHTML(el));
                el.data("topic", topic);
                $("#topics").append(el);
              });
            })
            .fail(function ($XHR, textStatus) {
              $("#topic-alert").show();
              $("#topic-alert").fadeTo(5000, 500).slideUp(500, function () {
                $("#topic-alert").slideUp(500);
              });
            });
          }
        },

        redirectToResponse: function ($el) {
          var topic = $el.data("topic");
          window.location.href = constants.TOPIC_ROUTES.TOPIC_REDIRECT_TO_RESPONSE
              + topic.topicId;
        }
      });
      return AppStart;
    });