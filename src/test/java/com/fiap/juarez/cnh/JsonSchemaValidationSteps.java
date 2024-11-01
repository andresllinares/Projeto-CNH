package com.fiap.juarez.cnh;

import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jackson.JsonLoader;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JsonSchemaValidationSteps {

    private String jsonPayload;

    @Given("a JSON payload that contains user data")
    public void a_json_payload_that_contains_user_data() {
        // Updated JSON payload to include usuarioId
        jsonPayload = "{ \"usuarioId\": 1, \"nome\": \"Nome do Usuário\", \"email\": \"usuario@gmail.com\", \"senha\": \"senha\" }";
    }

    @When("I validate the JSON payload against the schema")
    public void i_validate_the_json_payload_against_the_schema() throws Exception {
        // Load the JSON schema from resources
        JsonNode schemaNode = loadSchemaAsJsonNode("schemas/usuario-schema.json");

        // Create the JSON schema factory and schema
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema jsonSchema = factory.getJsonSchema(schemaNode);

        // Validate the JSON payload
        ProcessingReport report = jsonSchema.validate(JsonLoader.fromString(jsonPayload));

        // Assert that the validation was successful
        Assertions.assertTrue(report.isSuccess(), "Schema validation failed: " + report);
    }

    @Then("the JSON should be valid according to the schema")
    public void the_json_should_be_valid_according_to_the_schema() {
        // This step can also be used to check that the validation report was successful.
        // Since we already assert in the validation step, this can be empty or used for additional assertions.
    }

    // Utility method to load schema from resources and convert it to JsonNode
    private JsonNode loadSchemaAsJsonNode(String schemaPath) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(schemaPath);
        if (inputStream == null) {
            throw new IOException("Schema not found: " + schemaPath);
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append("\n");
        }
        return JsonLoader.fromString(stringBuilder.toString());
    }
}
