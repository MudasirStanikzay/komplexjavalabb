package se.iths.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<ResourceNotFoundException> {
    @Override
    public Response toResponse(ResourceNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorMessage(e.getMessage(), 404))
                .build();
    }
}
