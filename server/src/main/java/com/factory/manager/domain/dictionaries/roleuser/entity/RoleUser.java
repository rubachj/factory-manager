package com.factory.manager.domain.dictionaries.roleuser.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import static com.factory.manager.core.constants.TableName.ROLE_USER;

@Data
@Entity
@Table(name = ROLE_USER)
public class RoleUser {

    @Id
    private Long id;
    private String role;

}
