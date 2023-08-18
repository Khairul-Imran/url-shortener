package com.myprojects.urlshortener.service;

import com.myprojects.urlshortener.entity.RedirectWithAlias;
import com.myprojects.urlshortener.entity.RedirectWithHash;
import com.myprojects.urlshortener.exception.BadRequestException;
import com.myprojects.urlshortener.exception.NotFoundException;
import com.myprojects.urlshortener.repository.RedirectRepositoryForAlias;
import com.myprojects.urlshortener.repository.RedirectRepositoryForHash;
import com.myprojects.urlshortener.request.RedirectCreationRequestUsingAlias;
import com.myprojects.urlshortener.request.RedirectCreationRequestUsingHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Tells spring that the business logic is here.
public class RedirectService {

    private RedirectRepositoryForAlias redirectRepositoryForAlias;
    private RedirectRepositoryForHash redirectRepositoryForHash;

    @Autowired
    public RedirectService(RedirectRepositoryForAlias redirectRepositoryForAlias, RedirectRepositoryForHash redirectRepositoryForHash) {
        this.redirectRepositoryForAlias = redirectRepositoryForAlias;
        this.redirectRepositoryForHash = redirectRepositoryForHash;
    }

    // Secondary constructors, delegates to the primary constructor
    public RedirectService(RedirectRepositoryForAlias redirectRepositoryForAlias) {
        this(redirectRepositoryForAlias, null);
    }

    public RedirectService(RedirectRepositoryForHash redirectRepositoryForHash) {
        this(null, redirectRepositoryForHash);
    }


    public Optional<RedirectWithAlias> createRedirectForAlias(RedirectCreationRequestUsingAlias redirectCreationRequestUsingAlias) {
        if(redirectRepositoryForAlias.existsByAlias(redirectCreationRequestUsingAlias.getAlias())) {
            throw new BadRequestException("Alias already exists.");
        }
        System.out.println("Redirect Request " + redirectCreationRequestUsingAlias.toString());
        RedirectWithAlias redirectWithAlias = redirectRepositoryForAlias.save(new RedirectWithAlias(redirectCreationRequestUsingAlias.getAlias() , redirectCreationRequestUsingAlias.getUrl()));
        System.out.println("Redirect " + redirectWithAlias);

        return Optional.ofNullable(redirectWithAlias);
    }

    public Optional<RedirectWithHash> createRedirectForHash(RedirectCreationRequestUsingHash redirectCreationRequestUsingHash) {
        if(redirectRepositoryForHash.existsByHash(redirectCreationRequestUsingHash.getHash())) {
            throw new BadRequestException("Hash already exists.");
        }

        System.out.println("Redirect Request " + redirectCreationRequestUsingHash.toString());
        RedirectWithHash redirectWithHash = redirectRepositoryForHash.save(new RedirectWithHash(redirectCreationRequestUsingHash.getHash() , redirectCreationRequestUsingHash.getUrl()));
        System.out.println("Redirect " + redirectWithHash);

        return Optional.ofNullable(redirectWithHash);
    }

    public RedirectWithAlias getAliasRedirect(String alias) {
        RedirectWithAlias redirectWithAlias = redirectRepositoryForAlias.findByAlias(alias).
                orElseThrow(() -> new NotFoundException("Hey, we don't have that alias! Try making it."));

        return redirectWithAlias;
    }

    public RedirectWithHash getHashRedirect(String hash) {
        RedirectWithHash redirectWithHash = redirectRepositoryForHash.findByHash(hash).
                orElseThrow(() -> new NotFoundException("Hey, we don't have that hash!"));

        return redirectWithHash;
    }

}
