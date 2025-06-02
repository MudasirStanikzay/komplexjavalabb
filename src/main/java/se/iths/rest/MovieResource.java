package se.iths.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import se.iths.dto.CreateMovieDTO;
import se.iths.dto.MovieDTO;
import se.iths.dto.UpdateMovieDTO;

import java.util.List;

@Path("/movies")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieResource {

    @Inject
    MovieController movieController;

    @POST
    @Transactional
    public Response createMovie(CreateMovieDTO dto) {
        MovieDTO created = movieController.createMovie(dto);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public List<MovieDTO> getAllMovies() {
        return movieController.getAllMovies();
    }

    @GET
    @Path("/{id}")
    public MovieDTO getMovieById(@PathParam("id") Long id) {
        return movieController.getMovieById(id);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public MovieDTO updateMovie(@PathParam("id") Long id, UpdateMovieDTO dto) {
        return movieController.updateMovie(id, dto);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteMovie(@PathParam("id") Long id) {
        movieController.deleteMovie(id);
        return Response.noContent().build();
    }
}
