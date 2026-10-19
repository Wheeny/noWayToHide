package com.lastma.services;

import com.lastma.data.models.Officer;
import com.lastma.data.repositories.OfficerRepository;
import com.lastma.data.repositories.OfficerRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OfficerServiceImplTest {

    private OfficerRepository officerRepository =  new OfficerRepositoryImpl();
    private OfficerService officerService;

    @BeforeEach
    public void setUp() {
        officerService = new OfficerServiceImpl();
        officerRepository.deleteAll();

    }


    @Test
    public void testThatAnOfficerHasBeenRegistered(){
        assertEquals(0,  officerRepository.count());
        officerService.register("Winifred Nnaji", "winiefrednnaji@gmail.com", "1234");
        assertEquals(1,  officerRepository.count());

    }

    @Test
    public void testThatTwoOfficersHaveBeenRegistered(){
        assertEquals(0,  officerRepository.count());
        officerService.register("Winifred Nnaji", "winiefrednnaji@gmail.com", "1234");
        officerService.register("Chibuzor Ekejiuba", "cekejiuba@semicolon.com", "5678");
        assertEquals(2,  officerRepository.count());

    }

    @Test
    public void testThatTwoOfficersHaveBeenRegisteredAndLoggedIn(){
        assertEquals(0,  officerRepository.count());
        officerService.register("Winifred Nnaji", "winiefrednnaji@gmail.com", "1234");
        assertEquals(1,  officerRepository.count());
        Officer foundOfficer = officerService.login("winiefrednnaji@gmail.com", "1234");
        assertTrue(foundOfficer.isLoggedIn());
        assertEquals("winiefrednnaji@gmail.com", foundOfficer.getEmail());
        assertEquals("1234", foundOfficer.getPassword());
    }

    @Test
    public void testThatAnOfficerTriedToLogInWithoutFirstBeingRegistered(){
        assertEquals(0,  officerRepository.count());
        assertThrows(IllegalArgumentException.class, () -> officerService.login("winiefrednnaji@gmail.com", "1234"));
    }

    @Test
    public void testThatAnOfficerCanBeFoundById() {
        officerService.register("Winifred Nnaji", "winiefrednnaji@gmail.com", "1234");
        Officer foundOfficer = officerService.findOfficerById(1);

        assertNotNull(foundOfficer);
        assertEquals("Winifred Nnaji", foundOfficer.getFullName());
        assertEquals("winiefrednnaji@gmail.com", foundOfficer.getEmail());
    }

    @Test
    public void testThatFindingNonExistentOfficerByIdThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            officerService.findOfficerById(123);
        });
    }

}