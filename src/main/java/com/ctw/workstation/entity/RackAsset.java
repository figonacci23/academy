package com.ctw.workstation.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "T_RACK_ASSET")
public class RackAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackassetidgenerator")
    @SequenceGenerator(name="rackassetidgenerator", sequenceName = "SEQ_RACK_ASSET_ID", allocationSize = 1)
    public Long id;
    @Column(name = "rack_id")
    public Long rackId;
    @Column(name = "asset_tag", length = 10, unique = true)
    public String assetTag;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rack_id", updatable = false, insertable = false, nullable = false)
    private Rack rack;
}
