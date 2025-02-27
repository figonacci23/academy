package com.ctw.workstation.service;

import com.ctw.workstation.config.CtwAcademyResource;
import com.ctw.workstation.entity.Team;
import com.ctw.workstation.entity.TeamRequestDTO;
import com.ctw.workstation.entity.TeamResponseDTO;
import com.ctw.workstation.repository.TeamRepository;
import com.ctw.workstation.resource.TeamResource;
import io.quarkus.test.InjectMock;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.wildfly.common.Assert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@QuarkusTestResource(CtwAcademyResource.class)
class TeamServiceTest {

    @Inject
    TeamService teamService;

    @InjectSpy
    TeamRepository teamRepository;

    @Test
    void create_team_denied() {
        TeamRepository mockTeamRepository = Mockito.mock(TeamRepository.class);
        QuarkusMock.installMockForType(mockTeamRepository, TeamRepository.class);

        Mockito.doThrow(new IllegalArgumentException("PODE NAO"))
                .when(mockTeamRepository)
                .persist(Mockito.any(Team.class));

        //given
        TeamRequestDTO teamrequestdto = new TeamRequestDTO("test1", "test2", "test3");
        //then
        assertThatThrownBy(()-> teamService.create(teamrequestdto))
                .isInstanceOf((IllegalArgumentException.class))
                .hasMessage("PODE NAO");
    }

    @Test
    void create_team() {
        //given
        TeamRequestDTO teamrequestdto = new TeamRequestDTO("test1", "test2", "test3");
        //when
        TeamResponseDTO teamResponseDTO = teamService.create(teamrequestdto);
        //then
        assertThat(teamResponseDTO)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .as("DTO was returned with the data providad")
                .isEqualTo(teamrequestdto);
    }
}