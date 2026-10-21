package com.lastma.services;

import com.lastma.data.repositories.DriverRepository;
import com.lastma.data.repositories.DriverRepositoryImpl;
import com.lastma.dtos.requests.FindDriverRequest;
import com.lastma.dtos.requests.RegisterDriverRequest;
import com.lastma.dtos.responses.FindDriverResponse;
import com.lastma.dtos.responses.RegisterDriverResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

        class DriverServiceImplTest {

            private DriverRepository driverRepository = new DriverRepositoryImpl();
            private DriverService driverService;

            @BeforeEach
            public void setUp() {
                driverService = new DriverServiceImpl();
                driverRepository.deleteAll();
            }

            @Test
            public void testThatDriverCanBeRegistered() {
                assertEquals(0, driverRepository.count());

                RegisterDriverRequest request = new RegisterDriverRequest();
                request.setFullName("Winifred Nnaji");
                request.setPhoneNumber("08012345678");
                request.setEmail("winifred@gmail.com");

                RegisterDriverResponse response = driverService.registerDriver(request);

                assertEquals(1, driverRepository.count());
                assertNotNull(response);
                assertEquals("Winifred Nnaji", response.getFullName());
                assertEquals("winifred@gmail.com", response.getEmail());
            }

            @Test
            public void testThatDuplicateEmailThrowsException() {
                RegisterDriverRequest request1 = new RegisterDriverRequest();
                request1.setFullName("Winifred Nnaji");
                request1.setPhoneNumber("08012345678");
                request1.setEmail("winifred@gmail.com");

                driverService.registerDriver(request1);

                RegisterDriverRequest request2 = new RegisterDriverRequest();
                request2.setFullName("Another Driver");
                request2.setPhoneNumber("08087654321");
                request2.setEmail("winifred@gmail.com");

                assertThrows(IllegalArgumentException.class, () -> {
                    driverService.registerDriver(request2);
                });
            }

            @Test
            public void testThatFindingNonExistentDriverThrowsException() {
                FindDriverRequest request = new FindDriverRequest();
                request.setId(99);

                assertThrows(IllegalArgumentException.class, () -> {
                    driverService.findDriverById(request);
                });
            }

            @Test
            public void testThatDriverCanBeFoundById() {
                RegisterDriverRequest registerRequest = new RegisterDriverRequest();
                registerRequest.setFullName("Winifred Nnaji");
                registerRequest.setPhoneNumber("08012345678");
                registerRequest.setEmail("winifred@gmail.com");

                RegisterDriverResponse registerResponse = driverService.registerDriver(registerRequest);

                FindDriverRequest findRequest = new FindDriverRequest();
                findRequest.setId(registerResponse.getId());

                FindDriverResponse findResponse = driverService.findDriverById(findRequest);

                assertNotNull(findResponse);
                assertEquals(registerResponse.getId(), findResponse.getId());
                assertEquals("Winifred Nnaji", findResponse.getFullName());
            }
        }



    

