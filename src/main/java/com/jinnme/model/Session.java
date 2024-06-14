package com.jinnme.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Session extends UUIDIdentifiedEntitySuper {
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(20)")
    private SessionStatus status = SessionStatus.ACTIVE;
}
