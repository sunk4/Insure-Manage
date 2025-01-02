package com.roman.insure_manage.claim;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.roman.insure_manage.common.PageResponse;
import com.roman.insure_manage.common.StatusEnum;
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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(SpringExtension.class)
class ClaimControllerTest {

    @Mock
    ClaimService claimService;

    @InjectMocks
    private ClaimController claimController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc =
                MockMvcBuilders.standaloneSetup(claimController)
                        .build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void createClaim() throws Exception {
        ClaimDto claimDto =ClaimDto.builder()
                .claimAmount(10.0)
                .dateOfClaim(LocalDate.now())
                .policyId(UUID.randomUUID())
                .status(StatusEnum.Active)
                .description("description")
                .build();


        mockMvc.perform(post("/claims")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(claimDto)))
                .andExpect(status().isCreated());
        ArgumentCaptor<ClaimDto> captor = ArgumentCaptor.forClass(ClaimDto.class);

        verify(claimService, times(1)).createClaim(captor.capture());

    }

    @Test
    void updateClaim() throws Exception {
        UUID claimId = UUID.randomUUID();
        ClaimUpdateDto claimUpdateDto = ClaimUpdateDto
                .builder()
                        .id(claimId)
                        .claimAmount(10.0)
                        .dateOfClaim(LocalDate.now())
                        .policyId(UUID.randomUUID())
                        .status(StatusEnum.Active)
                        .description("description")
                        .build();

        mockMvc.perform(patch("/claims/{id}", claimId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(claimUpdateDto)))
                .andExpect(status().isNoContent());

        ArgumentCaptor<ClaimUpdateDto> captor = ArgumentCaptor.forClass(ClaimUpdateDto.class);

        verify(claimService, times(1)).updateClaim(eq(claimId), captor.capture());
    }

    @Test
    void getClaimById() throws Exception {
        UUID claimId = UUID.randomUUID();
        ClaimDto claimDto =ClaimDto.builder()
                .id(claimId)
                .claimAmount(10.0)
                .dateOfClaim(LocalDate.now())
                .policyId(UUID.randomUUID())
                .status(StatusEnum.Active)
                .description("description")
                .build();

        when(claimService.getClaimById(claimId)).thenReturn(claimDto);

        mockMvc.perform(get("/claims/{id}", claimId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());

        verify(claimService, times(1)).getClaimById(claimId);
    }

    @Test
    void getAllClaims() throws Exception {
        List<ClaimDto> claimDtos = Arrays.asList(new ClaimDto(), new ClaimDto());
        when(claimService.getAllClaims()).thenReturn(claimDtos);

        mockMvc.perform(get("/claims"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));

        verify(claimService, times(1)).getAllClaims();
    }

    @Test
    void getAllClaimsPaginated() throws Exception {
        PageResponse<ClaimDto> pageResponse = new PageResponse<>();
        when(claimService.getAllClaims(0, 10)).thenReturn(pageResponse);

        mockMvc.perform(get("/claims/paginated")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(claimService, times(1)).getAllClaims(0, 10);
    }
}