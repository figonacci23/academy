package com.ctw.workstation.service;

import com.ctw.workstation.entity.Rack;
import com.ctw.workstation.entity.RackRequestDTO;
import com.ctw.workstation.entity.RackResponseDTO;
import com.ctw.workstation.repository.RackRepository;
import io.quarkus.hibernate.orm.runtime.RequestScopedSessionHolder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;

import java.sql.Timestamp;
import java.util.Date;

@ApplicationScoped
public class RackService {

    //metodos do rep , chamar repo
    @Inject
    RackRepository RackRepository;
    @Inject
    RequestScopedSessionHolder requestScopedSessionHolder;

    @Transactional
    public RackResponseDTO create(RackRequestDTO rackRequestDTO) {
        Rack rack = RackRequestDTOToRack(rackRequestDTO);
        RackRepository.persist(rack);
        RackRepository.flush();
        return RackToRackResponseDTO(rack);
    }
    @Transactional
    public RackResponseDTO updateRack(Long id, RackRequestDTO rackRequestDTO) {
        Rack rack = RackRepository.findById(id);
        rack.setSerialNumber(rackRequestDTO.serialNumber);
        rack.setDefaultLocation(rackRequestDTO.defaultLocation);
        rack.setRackStatus(rackRequestDTO.rackStatus);
        rack.setModifiedAt(new Timestamp(new Date().getTime()));
        return RackToRackResponseDTO(rack);
    }

    @Transactional
    public RackResponseDTO findRackBySerialNumber(String name) {
        return RackToRackResponseDTO(RackRepository.findBySerialNumber(name));
    }

    @Transactional
    public void deleteRack(Rack Rack) {
        RackRepository.delete(Rack);
    }
    @Transactional
    public RackResponseDTO getRackById(Long id) {
        return RackToRackResponseDTO(RackRepository.findById(id));
    }

    @Transactional
    public Long countRacks() {
        return RackRepository.count();
    }

    public Rack RackRequestDTOToRack(RackRequestDTO rackRequestDTO) {
        return new Rack(rackRequestDTO.serialNumber, rackRequestDTO.assembledAt, rackRequestDTO.defaultLocation, rackRequestDTO.rackStatus);
    }

    public RackResponseDTO RackToRackResponseDTO(Rack rack) {
        return new RackResponseDTO(rack.id, rack.serialNumber, rack.assembledAt, rack.defaultLocation, rack.rackStatus);
    }

    public RackRequestDTO RackToRackRequestDTO(Rack rack) {
        return new RackRequestDTO(rack.serialNumber, rack.assembledAt, rack.defaultLocation, rack.rackStatus);
    }
}
