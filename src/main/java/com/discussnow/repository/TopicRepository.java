package com.discussnow.repository;

import com.discussnow.model.Topic;
import com.discussnow.model.User;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends PagingAndSortingRepository<Topic, Long> {

  List<Topic> findAllByUser(User user);

  List<Topic> searchByTitle(String searchTerm);
}
