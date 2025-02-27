package com.ctw.workstation.repository;

import com.ctw.workstation.entity.Team;
import com.ctw.workstation.entity.TeamResponseDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TeamRepository implements PanacheRepository<Team> {
    public Team findByName(String name) {
        return find("name", name).firstResult();
    }
}