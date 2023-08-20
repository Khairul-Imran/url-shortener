package com.myprojects.urlshortener;

import com.myprojects.urlshortener.repository.RedirectRepositoryForAlias;
import com.myprojects.urlshortener.repository.RedirectRepositoryForHash;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExpiryCleanupTask {

    private final RedirectRepositoryForAlias redirectRepositoryForAlias;
    private final RedirectRepositoryForHash redirectRepositoryForHash;

    public ExpiryCleanupTask(RedirectRepositoryForAlias redirectRepositoryForAlias, RedirectRepositoryForHash redirectRepositoryForHash) {
        this.redirectRepositoryForAlias = redirectRepositoryForAlias;
        this.redirectRepositoryForHash = redirectRepositoryForHash;
    }

    @Scheduled(fixedRate = 60000) // Runs every minute.
    public void removeExpiredEntries() {
        LocalDateTime currentTimestamp = LocalDateTime.now();
        redirectRepositoryForAlias.deleteByExpiryTimestampBefore(currentTimestamp);
        redirectRepositoryForHash.deleteByExpiryTimestampBefore(currentTimestamp);
    }

}
