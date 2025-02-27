package com.ctw.workstation.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.id.uuid.UuidGenerator;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "T_RACK")
public class Rack{
    public static final String GET_ALL = "Rack.getAll";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackidgenerator")
    @SequenceGenerator(name = "rackidgenerator", sequenceName = "SEQ_RACK_ID", allocationSize = 1)
    public Long id;

    @Column(name = "serial_number", length = 20, nullable = false)
    public String serialNumber;

    @Temporal(TemporalType.DATE)
    @Column(name = "assembled_at")
    public Date assembledAt;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created_at")
    public Timestamp createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "modified_at")
    public Timestamp modifiedAt;

    @Column(name = "default_location", length = 10)
    public String defaultLocation;

    @Column(name = "status")
    public String rackStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", updatable = false, insertable = false)
    public Team team;

    public Rack(String serialNumber, Date assembledAt, String defaultLocation, String rackStatus) {
        this.serialNumber = serialNumber;
        this.assembledAt = assembledAt;
        this.defaultLocation = defaultLocation;
        this.rackStatus = rackStatus;
    }

    public Rack() {

    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getAssembledAt() {
        return assembledAt;
    }

    public void setAssembledAt(Date assembledAt) {
        this.assembledAt = assembledAt;
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

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public void setDefaultLocation(String defaultLocation) {
        this.defaultLocation = defaultLocation;
    }

    public String getRackStatus() {
        return rackStatus;
    }

    public void setRackStatus(String rackStatus) {
        this.rackStatus = rackStatus;
    }
}
