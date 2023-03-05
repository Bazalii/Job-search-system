package frameworks.controllers

import frameworks.extensions.toFrameworkResponse
import frameworks.models.FrameworkCreationRequest
import frameworks.models.FrameworkResponse
import frameworks.services.IFrameworkService
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import java.util.*
import javax.enterprise.context.RequestScoped
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path

@RequestScoped
@Path("frameworks")
class FrameworkController(
    private var _frameworkService: IFrameworkService,
) {

    @APIResponses(
        APIResponse(responseCode = "200", description = "Framework is created"),
        APIResponse(responseCode = "400", description = "Invalid name")
    )
    @POST
    fun add(frameworkCreationRequest: FrameworkCreationRequest): FrameworkResponse {
        val creationModel = frameworkCreationRequest.toCreationModel()

        return _frameworkService.add(creationModel).toFrameworkResponse()
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation")
    )
    @GET
    fun getAll(): List<FrameworkResponse> {
        return _frameworkService.getAll().map { it.toFrameworkResponse() }
    }

    @APIResponses(
        APIResponse(responseCode = "200", description = "Successful operation"),
        APIResponse(responseCode = "404", description = "Framework with sent id does not exist")
    )
    @DELETE
    @Path("/{id}")
    fun removeById(id: UUID): FrameworkResponse {
        return _frameworkService.removeById(id).toFrameworkResponse()
    }
}