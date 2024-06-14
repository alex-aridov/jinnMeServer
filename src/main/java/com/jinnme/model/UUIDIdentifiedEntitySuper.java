package com.jinnme.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@ToString
@Getter
public abstract class UUIDIdentifiedEntitySuper {
    @Setter(AccessLevel.NONE)
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter(AccessLevel.NONE)
    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    @Column(updatable = false, nullable = false)
    private UUID id;
}
