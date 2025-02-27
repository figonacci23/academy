package com.ctw.workstation.entity;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "T_BOOKING")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingidgenerator")
    @SequenceGenerator(name = "bookingidgenerator", sequenceName = "SEQ_BOOKING_ID", allocationSize = 1)
    public Long id;
    @Column(name = "rack_id")
    public Long rackId;
    @Column(name = "requester_id")
    public Long requesterId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "book_from", nullable = false)
    public Timestamp bookFrom;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "book_to")
    public Timestamp bookTo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Timestamp createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    public Timestamp modifiedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id", updatable = false, insertable = false, nullable = false)
    private Rack rack;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requester_id", updatable = false, insertable = false, nullable = false)
    private TeamMember teamMember;
}
