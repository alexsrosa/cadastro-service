package com.desafio.concrete.solutions.cadastroservice.entrypoints;

import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.controllers.CadastroController;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserDtoToUserConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.converters.UserToUserResumeDtoConverter;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.PhoneDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.entrypoints.dtos.UserResumeDto;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.services.PhoneService;
import com.desafio.concrete.solutions.cadastroservice.infrastructure.services.UserService;
import com.desafio.concrete.solutions.cadastroservice.usecases.CreateUserUsecase;
import com.desafio.concrete.solutions.cadastroservice.usecases.GetUserProfileUsecase;
import com.desafio.concrete.solutions.cadastroservice.usecases.LoginUsecase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CadastroController.class, secure = false)
public class CadastroControllerTest {

    private UserDto userDto = new UserDto();
    private LocalDateTime now = LocalDateTime.now();
    private UserResumeDto userResumeDto = new UserResumeDto();

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateUserUsecase createUserUsecase;

    @MockBean
    private GetUserProfileUsecase getUserProfileUsecase;

    @MockBean
    private UserService userService;

    @MockBean
    private PhoneService phoneService;

    @MockBean
    private UserDtoToUserConverter userDtoToUserConverter;

    @MockBean
    private UserToUserResumeDtoConverter userToUserResumeDtoConverter;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private LoginUsecase loginUsecase;

    @Before
    public void init() {
        userDto.setName("João da Silva");
        userDto.setEmail("joao@silva.org");
        userDto.setPassword("hunter2");

        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setNumber("987654321");
        phoneDto.setDdd("21");

        Set<PhoneDto> phoneDtos = new HashSet<>();
        phoneDtos.add(phoneDto);
        userDto.setPhones(phoneDtos);

        userResumeDto.setId(UUID.randomUUID());
        userResumeDto.setName("João da Silva");
        userResumeDto.setEmail("joao@silva.org");
        userResumeDto.setCreated(now);
        userResumeDto.setModified(now);
        userResumeDto.setLastLogin(now);
        userResumeDto.setPhones(phoneDtos);
    }

    @Test
    public void givenUserDto_whenCreateNewUser_thenReturnJsonUserResumeDto_isBadRequest()
            throws Exception {

        mvc.perform(post("/api/cadastro")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}