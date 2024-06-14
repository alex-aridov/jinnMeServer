package com.jinnme.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "t_user")
@Getter
@Setter
public class User extends UUIDIdentifiedEntitySuper {
    @Column(name = "platform_id", nullable = false, unique = true)
    private String platformId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "is_bot")
    private Boolean isBot;
    @Column(name = "language_code", columnDefinition = "varchar(10)")
    private String languageCode;
}
