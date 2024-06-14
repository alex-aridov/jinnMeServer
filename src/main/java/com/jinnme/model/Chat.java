package com.jinnme.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Chat extends UUIDIdentifiedEntitySuper {
    @Column(name = "platform_id", nullable = false, unique = true)
    private String platformId;
    private String type;
    private String title;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "username")
    private String userName;
}
