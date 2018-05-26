package com.discussnow.repository;

import com.discussnow.model.Reply;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends PagingAndSortingRepository<Reply, Long> {

  Reply findByParentReply(Reply parentReply);
}
