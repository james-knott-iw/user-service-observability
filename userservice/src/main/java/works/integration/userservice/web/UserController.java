package works.integration.userservice.web;

import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import works.integration.userservice.entity.User;
import works.integration.userservice.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "User-serviceController", description = "Create, retrieve, update and delete TODO")
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;
    private final Counter requestCounter;

    public UserController(UserService userService, MeterRegistry meterRegistry) {
        this.userService = userService;
        this.requestCounter = Counter.builder("http_request_counter")
                .description("Total number of requests to /user/{id}")
                .register(meterRegistry);
    }

    @Operation(summary = "Retrieve a User by Id", description = "Returns a User based on Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "User doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of User", content = @Content(schema = @Schema(implementation = User.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<User> getMethodName(@PathVariable Long id) {
        requestCounter.increment();
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

}
