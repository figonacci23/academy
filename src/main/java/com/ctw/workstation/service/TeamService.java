package com.ctw.workstation.service;

import com.ctw.workstation.entity.Team;
import com.ctw.workstation.entity.TeamRequestDTO;
import com.ctw.workstation.entity.TeamResponseDTO;
import com.ctw.workstation.repository.TeamRepository;
import io.quarkus.hibernate.orm.runtime.RequestScopedSessionHolder;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@ApplicationScoped
public class TeamService {

    //metodos do rep , chamar repo
    @Inject
    TeamRepository teamRepository;
    @Inject
    RequestScopedSessionHolder requestScopedSessionHolder;

    @Transactional
    public TeamResponseDTO create(TeamRequestDTO teamRequestDTO) {
        Team team = TeamRequestDTOToTeam(teamRequestDTO);
        teamRepository.persist(team);
        teamRepository.flush();
        return TeamToTeamResponseDTO(team);
    }
    @Transactional
    public TeamResponseDTO updateTeam(Long id, TeamRequestDTO teamRequestDTO) {
        Team team = teamRepository.findById(id);
        team.setName(teamRequestDTO.name);
        team.setProduct(teamRequestDTO.product);
        team.setDefaultLocation(teamRequestDTO.defaultLocation);
        team.setModifiedAt(new Timestamp(new Date().getTime()));
        return TeamToTeamResponseDTO(team);
    }

    @Transactional
    public TeamResponseDTO findTeamByName(String name) {
        return TeamToTeamResponseDTO(teamRepository.findByName(name));
    }

    @Transactional
    public void deleteTeam(Team team) {
        teamRepository.delete(team);
    }
    @Transactional
    public TeamResponseDTO getTeamById(Long id) {
        return TeamToTeamResponseDTO(teamRepository.findById(id));
    }

    @Transactional
    public Long countTeams() {
        return teamRepository.count();
    }

    public Team TeamRequestDTOToTeam(TeamRequestDTO teamRequestDTO) {
        return new Team(teamRequestDTO.product, teamRequestDTO.name, teamRequestDTO.defaultLocation);
    }

    public TeamResponseDTO TeamToTeamResponseDTO(Team team) {
        return new TeamResponseDTO(team.id, team.product, team.name, team.defaultLocation);
    }

    public TeamRequestDTO TeamToTeamRequestDTO(Team team) {
        return new TeamRequestDTO(team.product, team.name, team.defaultLocation);
    }
}
