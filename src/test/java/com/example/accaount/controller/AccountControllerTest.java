package com.example.accaount.controller;

import com.example.accaount.dto.CreateAccountRequest;
import com.example.accaount.dto.CustomerAccountDtoConverter;
import com.example.accaount.model.Customer;
import com.example.accaount.repository.AccaountRepository;
import com.example.accaount.repository.CustomerRepository;
import com.example.accaount.service.AccountService;
import com.example.accaount.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Clock;
import java.util.UUID;
import java.util.function.Supplier;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
        "server-port=0",
        "command.line.runner.enabled=false"
})
@RunWith(SpringRunner.class)
@DirtiesContext
class AccountControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Clock clock;
    @MockBean
    private Supplier<UUID> uuidSupplier;

    @Autowired
    private AccaountRepository accaountRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerAccountDtoConverter converter;
    @Autowired
    private CustomerRepository customerRepository;

    private AccountService service = new AccountService(accaountRepository,customerService,converter);
    private ObjectMapper mapper = new ObjectMapper();

    private static final UUID uuid = UUID.randomUUID();

    @BeforeEach
    public void setup(){
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    }
    @Test
    public void testCreateAccount_whenCustomerIdExits_shouldCreateAccountAndReturnAccountDta(){
        Customer customer = customerRepository.save(new Customer("Umut","Dikbasan"));
        CreateAccountRequest request = new CreateAccountRequest(customer.getId(), new BigDecimal(100));

        this.mockMvc.perform(post("/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writer().withDefaultPrettyPrinter().writeValuesAsString(request)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id",notNullValuable()))
                .andExpect(jsonPath("$.balance", is(100)))
                .andExpect(jsonPath("$.customer.id", is(customer.getId()))
                .andExpect(jsonPath("$.customer.name", is("Umut"))
                .andExpect(jsonPath("$.customer.surname", is("Dikbasan"))
                .andExpect(jsonPath("$.transactions", hashSize(1)));


    }
}