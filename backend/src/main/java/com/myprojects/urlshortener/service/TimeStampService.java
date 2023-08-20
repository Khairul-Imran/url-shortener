package com.myprojects.urlshortener.service;

import com.myprojects.urlshortener.entity.RedirectWithAlias;
import com.myprojects.urlshortener.entity.RedirectWithHash;
import com.myprojects.urlshortener.repository.RedirectRepositoryForAlias;
import com.myprojects.urlshortener.repository.RedirectRepositoryForHash;
import com.myprojects.urlshortener.request.RedirectCreationRequestUsingAlias;
import com.myprojects.urlshortener.request.RedirectCreationRequestUsingHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TimeStampService {

    @Autowired
    @Qualifier("redirectRepositoryForAlias")
    private RedirectRepositoryForAlias redirectRepositoryForAlias;

    @Autowired
    @Qualifier("redirectRepositoryForHash")
    private RedirectRepositoryForHash redirectRepositoryForHash;

    public void insertEntryWithExpiryTimestampAlias(RedirectCreationRequestUsingAlias redirectCreationRequestUsingAlias) {
        LocalDateTime currentTimestamp = LocalDateTime.now();
        LocalDateTime expiryTimestamp = currentTimestamp.plusMinutes(15);

        redirectCreationRequestUsingAlias.setExpiryTimestamp(expiryTimestamp);
        //redirectRepositoryForAlias.save(redirectCreationRequestUsingAlias);
    }

    public void insertEntryWithExpiryTimestampHash(RedirectCreationRequestUsingHash redirectCreationRequestUsingHash) {
        LocalDateTime currentTimestamp = LocalDateTime.now();
        LocalDateTime expiryTimestamp = currentTimestamp.plusMinutes(15);

        redirectCreationRequestUsingHash.setExpiryTimestamp(expiryTimestamp);
        //redirectRepositoryForHash.save(redirectCreationRequestUsingHash);
    }

}
