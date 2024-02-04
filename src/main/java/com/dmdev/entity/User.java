package com.dmdev.entity;

import com.dmdev.converter.BirthdayConverter;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @Column(name = "username", nullable = false, length = 128)
    private String username;

    @Column(name = "firstname", length = 128)
    private String firstname;

    @Column(name = "lastname", length = 128)
    private String lastname;

    @Column(name = "birth_date")
    private Birthday birthDate;

    @Type(type = "jsonb")
    @Column(name = "info")
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;
}
