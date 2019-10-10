package com.emp.loc.model;

import com.emploc.model.CodeMessage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusResponseTest {

    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void statusResponseCreate() throws IOException {
        final List<CodeMessage> created = Arrays.asList(
                new CodeMessage().code(404).message("Resource was not found"),
                new CodeMessage().code(500).message("Internal server error")
        );

        final List<CodeMessage> read = objectMapper.readValue(
                new InputStreamReader(getClass().getResourceAsStream("/dummyjson/statusResponse.json")), new TypeReference<List<CodeMessage>>() {
                }
        );
        assertThat(created).isEqualTo(read);
        assertThat(created.get(0)).isEqualTo(read.get(0));
        assertThat(created.get(1)).isEqualTo(read.get(1));

    }
}
