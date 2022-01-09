package com.example.prodj.repo;

import com.example.prodj.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Post, Long> {

}
