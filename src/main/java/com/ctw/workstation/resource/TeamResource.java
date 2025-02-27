package com.ctw.workstation.resource;

import com.ctw.workstation.entity.Team;
import com.ctw.workstation.entity.TeamRequestDTO;
import com.ctw.workstation.entity.TeamResponseDTO;
import com.ctw.workstation.repository.TeamRepository;
import com.ctw.workstation.service.TeamService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/workstation/teams")
public class TeamResource {

    @Inject
    TeamRepository teamRepository;
    @Inject
    TeamService teamService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllTeams() {
        return Response.ok(teamRepository.listAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return Response.ok(teamService.getTeamById(id)).build();
    }

    @POST
    @Transactional
    public Response create(TeamRequestDTO teamRequestdto) {
        Log.infov("Creating team: {0}", teamRequestdto.name);
        return Response.status(Response.Status.CREATED).entity(teamService.create(teamRequestdto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, TeamRequestDTO teamRequestdto) {
        Log.infov("Updating team on id: {0}", id);
        if (teamService.getTeamById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(teamService.updateTeam(id, teamRequestdto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Log.infov("Deleting team with id {0}", id);
        Team team = teamRepository.findById(id);
        if(team == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        teamService.deleteTeam(team);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/search/{name}")
    public Response search(@PathParam("name")String name) {
        Log.infov("Searching for team with name {0}", name);
        if (name == null || name.isEmpty()) {
            return Response.status(Response.Status.NOT_ACCEPTABLE).build();
        }
        return Response.ok(teamService.findTeamByName(name)).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        Log.infov("Counting teams");
        return Response.status(Response.Status.OK).entity(teamService.countTeams()).build();
    }
}