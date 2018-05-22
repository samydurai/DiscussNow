package com.discussnow.repository;

import com.discussnow.model.Response;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponseRepository extends PagingAndSortingRepository<Response, Long> {

  Long countByTopic_TopicId(Long topicId);
}
