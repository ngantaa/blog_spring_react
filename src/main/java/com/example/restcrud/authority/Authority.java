package com.example.restcrud.authority;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "auth_authority")
public class Authority implements GrantedAuthority {

    @Id
    @SequenceGenerator(
            name = "authority_sequence",
            sequenceName = "authority_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "authority_sequence"
    )
    @Column(name = "authority_id")
    private Long authorityId;
    @Column(name = "role_code")
    private String roleCode;
    @Column(name = "role_description")
    private String roleDescription;

    @Override
    public String getAuthority() {
        return null;
    }
}
