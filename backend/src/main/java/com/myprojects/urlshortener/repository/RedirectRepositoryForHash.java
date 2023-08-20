package com.myprojects.urlshortener.repository;

import com.myprojects.urlshortener.entity.RedirectWithHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("redirectRepositoryForHash")
public interface RedirectRepositoryForHash extends JpaRepository<RedirectWithHash, Long>{
    Optional<RedirectWithHash> findByHash(String hash);

    Boolean existsByHash(String hash);

}
