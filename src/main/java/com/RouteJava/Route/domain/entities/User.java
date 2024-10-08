package com.RouteJava.Route.domain.entities;

import com.RouteJava.Route.util.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity( name = "users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 150, nullable = false, unique = true)
    private String userName;
    @Column(length = 150, nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToOne( mappedBy = "user")
    private Carrier carrier;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /**
         * SimpleGrantedAuthority permite registrar el permiso
         */

        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }



    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
