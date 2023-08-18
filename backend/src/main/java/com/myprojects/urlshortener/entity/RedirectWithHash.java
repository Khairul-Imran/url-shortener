package com.myprojects.urlshortener.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.NaturalId;

@Entity
public class RedirectWithHash {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    @JsonIgnore
    private Long id;

    @NaturalId
    @Column(unique = true, nullable = true) // Hash has to be unique, and can be null.
    private String hash;

    @Column(nullable = false) // Required.
    private String url;

    public RedirectWithHash() {

    }

    public RedirectWithHash(final String hash, final String url) {
        this.hash = hash;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Redirect{" +
                "id=" + id +
                ", hash='" + hash + '\'' +
                ", url='" + url + '\'' +
                '}';
    }


}
