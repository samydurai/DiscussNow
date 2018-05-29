package com.discussnow.resource.response;

import com.discussnow.model.Response;
import com.discussnow.repository.ResponseRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ResponseDynamicResource {

  @Autowired
  private ResponseRepository responseRepository;

  @GetMapping(value = "/pages/topic/{topicId}")
  public String getResponse(@PathVariable Long topicId, Map<String, Object> model) throws Exception{
    Iterable<Response> responseList = responseRepository.findByTopic_TopicId(topicId);
    List<Response> responses = new ArrayList<>();
    for (Response response : responseList) {
      responses.add(response);
    }
    model.put("responses", responses);
    return "response";
  }
}
