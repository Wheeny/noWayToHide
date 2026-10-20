package com.lastma.services;

import com.lastma.data.models.Officer;
import com.lastma.data.repositories.OfficerRepository;
import com.lastma.data.repositories.OfficerRepositoryImpl;
import com.lastma.dtos.requests.FindOfficerRequest;
import com.lastma.dtos.requests.OfficerLoginRequest;
import com.lastma.dtos.requests.RegisterOfficerRequest;
import com.lastma.dtos.responses.FindOfficerResponse;
import com.lastma.dtos.responses.OfficerLoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfficerServiceImplTest {

    private OfficerRepository officerRepository =  new OfficerRepositoryImpl();
    private OfficerService officerService;
    private RegisterOfficerRequest request;
    private FindOfficerRequest findOfficerRequest;
    private OfficerLoginRequest  loginRequest;
    private Officer officer;

    @BeforeEach
    public void setUp() {
        officerService = new OfficerServiceImpl();
        officerRepository.deleteAll();
        request = new RegisterOfficerRequest();
        findOfficerRequest = new FindOfficerRequest();
        loginRequest = new OfficerLoginRequest();
        officer = new Officer();

    }


    @Test
    public void testThatAnOfficerHasBeenRegistered(){
        assertEquals(0,  officerRepository.count());
        request.setFullName("Test");
        request.setEmail("email");
        request.setPassword("password");
        officerService.register(request);
        assertEquals(1,  officerRepository.count());

    }

    @Test
    public void testThatTwoOfficersHaveBeenRegistered(){
        assertEquals(0,  officerRepository.count());
        request.setFullName("Winifred Nnaji");
        request.setEmail("winiefrednnaji@gmail.com");
        request.setPassword("1234");
        officerService.register(request);

        RegisterOfficerRequest request2 = new RegisterOfficerRequest();
        request2.setFullName("Chibuzor Ekejiuba");
        request2.setEmail("cekejiuba@semicolon.com");
        request2.setPassword("5678");

        officerService.register(request2);
        assertEquals(2,  officerRepository.count());

    }

    @Test
    public void testThatTwoOfficersHaveBeenRegisteredAndLoggedIn(){
        assertEquals(0,  officerRepository.count());
        request.setFullName("Winifred Nnaji");
        request.setEmail("winiefrednnaji@gmail.com");
        request.setPassword("1234");
        officerService.register(request);
        assertEquals(1,  officerRepository.count());

        loginRequest.setEmail("winiefrednnaji@gmail.com");
        loginRequest.setPassword("1234");
        OfficerLoginResponse foundOfficer = officerService.login(loginRequest);
        assertEquals("winiefrednnaji@gmail.com", foundOfficer.getEmail());
        assertEquals("1234", loginRequest.getPassword());
    }

    @Test
    public void testThatAnOfficerTriedToLogInWithoutFirstBeingRegistered(){
        assertEquals(0,  officerRepository.count());
        loginRequest.setEmail("winiefrednnaji@gmail.com");
        loginRequest.setPassword("1234");
        assertThrows(IllegalArgumentException.class, () -> officerService.login(loginRequest));
    }

    @Test
    public void testThatAnOfficerCanBeFoundById() {
        request.setFullName("Winifred Nnaji");
        request.setEmail("winiefrednnaji@gmail.com");
        request.setPassword("1234");
        officerService.register(request);
        findOfficerRequest.setId(1);
        FindOfficerResponse foundOfficer = officerService.findOfficerById(findOfficerRequest);

        assertNotNull(foundOfficer);
        assertEquals("Winifred Nnaji", foundOfficer.getFullName());
    }

    @Test
    public void testThatFindingNonExistentOfficerByIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            findOfficerRequest.setId(123);
            officerService.findOfficerById(findOfficerRequest);
        });
    }

}