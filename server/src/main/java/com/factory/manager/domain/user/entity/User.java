package com.factory.manager.domain.user.entity;

import com.factory.manager.domain.dictionaries.roleuser.entity.RoleUser;
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

import static com.factory.manager.core.constants.SequenceName.SEQ_APP_USER;
import static com.factory.manager.core.constants.TableName.APP_USER;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = APP_USER)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_APP_USER)
    @SequenceGenerator(allocationSize = 1, name = SEQ_APP_USER)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_ROLE_USER")
    private RoleUser role;
    private Boolean accountExpired;
    private Boolean accountLocked;
    private Boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.getRole()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}
