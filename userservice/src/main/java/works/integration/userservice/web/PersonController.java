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
import lombok.AllArgsConstructor;
import works.integration.userservice.entity.Person;
import works.integration.userservice.service.PersonService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "User-serviceController", description = "Create, retrieve, update and delete TODO")
@RestController
@RequestMapping("/person")
public class PersonController {

    PersonService personService;
    private final Counter requestCounter;

    public PersonController(PersonService personService, MeterRegistry meterRegistry) {
        this.personService = personService;
        this.requestCounter = Counter.builder("http_request_counter")
                .description("Total number of requests to /person/{id}")
                .register(meterRegistry);
    }

    @Operation(summary = "Retrieve a Person by Id", description = "Returns a Person based on Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Person doesn't exist", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "200", description = "Successful retrieval of Person", content = @Content(schema = @Schema(implementation = Person.class))),
    })
    @GetMapping("/{id}")
    public ResponseEntity<Person> getMethodName(@PathVariable Long id) {
        requestCounter.increment();
        return new ResponseEntity<>(personService.getPerson(id), HttpStatus.OK);
    }

}
