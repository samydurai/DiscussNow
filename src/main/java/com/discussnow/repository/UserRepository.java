package com.discussnow.repository;

import com.discussnow.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  @Nullable
  User findUsersByUserEmail(@NonNull String email);
}
