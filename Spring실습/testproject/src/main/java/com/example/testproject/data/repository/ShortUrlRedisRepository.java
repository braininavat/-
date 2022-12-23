package com.example.testproject.data.repository;

import com.example.testproject.data.dto.ShortUrlResponseDTO;
import org.springframework.data.repository.CrudRepository;

public interface ShortUrlRedisRepository extends CrudRepository<ShortUrlResponseDTO,String> {
}
