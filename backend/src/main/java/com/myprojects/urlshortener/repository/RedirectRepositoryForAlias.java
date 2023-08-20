package com.myprojects.urlshortener.repository;

import com.myprojects.urlshortener.entity.RedirectWithAlias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository("redirectRepositoryForAlias")
public interface RedirectRepositoryForAlias extends JpaRepository<RedirectWithAlias, Long> {
    Optional<RedirectWithAlias> findByAlias(String alias);

    Boolean existsByAlias(String alias);

    void deleteByExpiryTimestampBefore(LocalDateTime currentTimestamp);

}
