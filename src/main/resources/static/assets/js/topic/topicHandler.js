define(["js/constants"], function (constants) {

  function TopicHandler() {
  }

  TopicHandler.prototype = $.extend(Object.create(TopicHandler.prototype), {
    loadAll : function () {
      $.get(constants.TOPIC_ROUTES.TOPIC_LOAD_ALL, function (resp) {
        $.each(resp, function (index, topic) {
          var el = "<a href=\"#\" class=\"list-group-item list-group-item-action\">"
              + topic.title + "</a>";
          el = $($.parseHTML(el));
          el.data("topic", topic);
          $("#topics").append(el);
        });
      });
    }
  });
  return TopicHandler;
});