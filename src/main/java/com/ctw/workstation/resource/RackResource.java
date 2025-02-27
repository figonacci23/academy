package com.ctw.workstation.resource;

import com.ctw.workstation.entity.Rack;
import com.ctw.workstation.entity.RackRequestDTO;
import com.ctw.workstation.repository.RackRepository;
import com.ctw.workstation.repository.TeamRepository;
import com.ctw.workstation.service.RackService;
import com.ctw.workstation.service.TeamService;
import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/workstation/racks")
public class RackResource {

    @Inject
    RackRepository rackRepository;
    @Inject
    RackService rackService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllRacks() {
        return Response.ok(rackRepository.listAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        return Response.ok(rackService.getRackById(id)).build();
    }

    @GET
    @Path("/{serialNumber}")
    public Response getBySerialNumber(@PathParam("serialNumber") String serialNumber) {
        return Response.ok(rackService.findRackBySerialNumber(serialNumber)).build();
    }

    @POST
    @Transactional
    public Response create(RackRequestDTO rackRequestdto) {
        Log.infov("Creating rack: {0}", rackRequestdto.serialNumber);
        return Response.status(Response.Status.CREATED).entity(rackService.create(rackRequestdto)).build();
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, RackRequestDTO rackRequestdto) {
        Log.infov("Updating rack on id: {0}", id);
        if (rackService.getRackById(id) == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok().entity(rackService.updateRack(id, rackRequestdto)).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Log.infov("Deleting rack with id {0}", id);
        Rack rack = rackRepository.findById(id);
        if(rack == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        rackService.deleteRack(rack);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        Log.infov("Counting racks");
        return Response.status(Response.Status.OK).entity(rackService.countRacks()).build();
    }
}