package com.jinnme.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Message extends UUIDIdentifiedEntitySuper {
    @ManyToOne(fetch = FetchType.LAZY)
    private User from;
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;
    @Column(columnDefinition = "text")
    private String text;
}
