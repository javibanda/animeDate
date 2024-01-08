package org.javibanda.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"admin_user\"")
@Getter
@Setter
public class AdminUser {
    @Id
    @Column(name = "email")
    private String email;


}
