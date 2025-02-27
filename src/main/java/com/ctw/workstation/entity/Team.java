package com.ctw.workstation.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "T_TEAM")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackidgenerator")
    @SequenceGenerator(name = "rackidgenerator", sequenceName = "SEQ_TEAM_ID", allocationSize = 1)
    public Long id;
    @Column(name= "product", length = 30)
    public String product;
    @Column(name = "name", length = 50, nullable = false)
    public String name;
    @Column(name = "default_location", length = 10)
    public String defaultLocation;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at")
    public Timestamp createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "modified_at")
    public Timestamp modifiedAt;

    public Team(String product, String name, String defaultLocation) {
        this.product = product;
        this.name = name;
        this.defaultLocation = defaultLocation;
    }

    public Team() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Timestamp modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}