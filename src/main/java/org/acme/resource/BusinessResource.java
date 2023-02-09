package org.acme.resource;

import org.acme.entity.Business;
import org.acme.repo.BusinessRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

// Annotation to define the resource URI path
@Path("/business")
// Annotation to specify the media type consumed by the resource
@Produces(MediaType.APPLICATION_JSON)
// Annotation to specify the media type produced by the resource
@Consumes(MediaType.APPLICATION_JSON)
//@CrossOrigin(origins = "http://localhost:4200")
public class BusinessResource {
    @Inject
    BusinessRepository businessRepository;

    // Get all businesses in the database
    @GET
    @Path("/all")
    public Response getAll() {
        List<Business> business = businessRepository.listAll();
        return Response.ok(business).build();
    }

    // Add a new business to the database
    @POST
    @Path("/add")
    @Transactional
    public Response addBusiness(Business business) {
        businessRepository.persist(business);
        return Response.created(URI.create("/business/" + business.getId())).build();
    }


    // Get a business by its ID
    @GET
    @Path("/find/id/{id}")

    public Response getBusinessById(@PathParam("id") Long id) {
        return businessRepository.findByIdOptional(id)
                .map(business -> Response.ok(business).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }


// Get a business by its name
    @GET
    @Path("/find/name/{name}")

    public Response getBusinessByName(@PathParam("name") String name) {
        List<Business> businesses = businessRepository.find("name", name).list();
        if (businesses.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(businesses).build();
        }
    }

// Get a business by its legal name
    @GET
    @Path("find/legalname/{legalName}")
    public Response getBusinessByLName(@PathParam("legalName") String legalName) {
        List<Business> businesses = businessRepository.find("legalName", legalName).list();
        if (businesses.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(businesses).build();
        }
    }

    // Get a business by its ABN
    @GET
    @Path("find/abn/{ABN}")
    public Response getBusinessByAbn(@PathParam("ABN") String ABN) {
        List<Business> businesses = businessRepository.find("ABN", ABN).list();
        if (businesses.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(businesses).build();
        }
    }

    // Get a business by its ACN
    @GET
    @Path("find/acn/{ACN}")
    public Response getBusinessByAcn(@PathParam("ACN") String ACN) {
        List<Business> businesses = businessRepository.find("ACN", ACN).list();
        if (businesses.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(businesses).build();
        }
    }

    // Update a business
    @PUT
    @Path("/update/{id}")
    @Transactional
    public Response updateBusiness(@PathParam("id") Long id, Business updatedBusiness) {
        return businessRepository.findByIdOptional(id)
                .map(business -> {
                    business.setName(updatedBusiness.getName());
                    business.setLegalName(updatedBusiness.getLegalName());
                    business.setABN(updatedBusiness.getABN());
                    business.setACN(updatedBusiness.getACN());
                    businessRepository.persist(business);
                    return Response.ok().build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    // Delete a business
    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public Response deleteBusiness(@PathParam("id") Long id) {
        return businessRepository.findByIdOptional(id)
                .map(business -> {
                    businessRepository.delete(business);
                    return Response.ok().build();
                })
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

}
