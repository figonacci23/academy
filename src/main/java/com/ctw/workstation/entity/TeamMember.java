package com.ctw.workstation.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
@Table(name = "T_TEAM_MEMBER")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teammemberidgenerator")
    @SequenceGenerator(name = "teammemberidgenerator", sequenceName = "SEQ_TEAM_MEMBER_ID", allocationSize = 1)
    public Long id;
    @Column(name = "team_id")
    public Long teamId;
    @Column(name = "ctw_id", length = 10, nullable = false)
    public String ctwId;
    @Column(name = "name", length = 30)
    public String name;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Timestamp createdAt;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at")
    public Timestamp modifiedAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", updatable = false, insertable = false, nullable = false)
    private Team team;
}
