package com.roman.insure_manage.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.roman.insure_manage.common.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
class ClientControllerTest {

    @Mock
    ClientService clientService;

    @InjectMocks
    private ClientController clientController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;
    private ClientDto clientDto;
    private UUID uuid;

    @BeforeEach
    void setUp () {
        mockMvc =
                MockMvcBuilders.standaloneSetup(clientController)
                        .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        uuid = UUID.randomUUID();
        clientDto = ClientDto.builder()
                .id(uuid)
                .address("address")
                .city("city")
                .email("email@example.com")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("+421950123456")
                .zipCode("zipCode")
                .country("country")
                .dateOfBirth(LocalDate.now().minusYears(20))
                .build();
    }

    @Test
    void createClient () throws Exception {

        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientDto)))
                .andExpect(status().isCreated());

        ArgumentCaptor<ClientDto> captor = ArgumentCaptor.forClass(ClientDto.class);

        verify(clientService).createClient(captor.capture());
    }

    @Test
    void getAllClients () throws Exception {
        List<ClientDto> clientDtoList = Arrays.asList(new ClientDto(),
                new ClientDto());

        when(clientService.getAllClients()).thenReturn(clientDtoList);

        mockMvc.perform(get("/client"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));

        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void getClientById () throws Exception{
        when(clientService.getClientById(uuid)).thenReturn(clientDto);

        mockMvc.perform(get("/client/{id}", uuid))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());

        verify(clientService, times(1)).getClientById(uuid);
    }

    @Test
    void updateClient ()  throws  Exception{
        UUID clientId = UUID.randomUUID();
        ClientUpdateDto claimUpdateDto = ClientUpdateDto
                .builder()
                .address("address")
                .city("city")
                .email("email@example.com")
                .firstName("firstName")
                .lastName("lastName")
                .phoneNumber("+421950123456")
                .zipCode("zipCode")
                .country("country")
                .dateOfBirth(LocalDateTime.now().minusYears(20))
                .build();

        mockMvc.perform(patch("/client/{id}", clientId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(claimUpdateDto)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<ClientUpdateDto> captor =
                ArgumentCaptor.forClass(ClientUpdateDto.class);

        verify(clientService, times(1)).updateClient(eq(clientId), captor.capture());
    }

    @Test
    void deleteClient () throws Exception{
        UUID clientId = UUID.randomUUID();

        doNothing().when(clientService).deleteClient(clientId);

        mockMvc.perform(delete("/client/{clientId}", clientId))
                .andExpect(status().isNoContent());

        verify(clientService, times(1)).deleteClient(clientId);
    }

    @Test
    void getAllClientsPaginated () throws Exception{
        PageResponse<ClientDto> pageResponse = new PageResponse<>();

        when(clientService.getAllClientsPaginated(0, 10, null)).thenReturn(pageResponse);

        mockMvc.perform(get("/client/paginated")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(clientService, times(1)).getAllClientsPaginated(0, 10, null);
    }
}