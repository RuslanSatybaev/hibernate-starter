package com.dmdev.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "username")
@ToString(exclude = {"company", "profile"})
@Builder
@Entity
@Table(name = "users", schema = "public")
@TypeDef(name = "dmdev", typeClass = JsonBinaryType.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @AttributeOverride(name = "birthDate", column = @Column(name = "birth_date"))
    private PersonalInfo personalInfo;

    @Column(name = "username", unique = true)
    private String username;

    @Type(type = "dmdev")
    @Column(name = "info")
    private String info;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
}
