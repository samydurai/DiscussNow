define([], function () {
  return {
    BASE_URL : 'http://localhost:8080',
    REDIRECT_ROUTES: {
      LOGIN: 'http://localhost:8080/login',
      LOGOUT : 'http://localhost:8080/logout'
    },

    TOPIC_ROUTES: {
      TOPIC_SAVE: '/topic/save',
      TOPIC_LOAD_ALL: '/topic/loadAll',
      TOPIC_REDIRECT_TO_RESPONSE : "http://localhost:8080/pages/topic/"
    },

    USER_INFO_ROUTES: {
      USER_INFO_LOAD_PRINCIPAL: '/userInfo',
      CREATE_USER: '/user/create'
    },

    TOPIC_SAVE_REQ_TEMPLATE : {
      topic : {
        added : [],
        updated : [],
        deleted : []
      }
    }
  }
});