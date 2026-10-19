package com.lastma.data.repositories;

import com.lastma.data.models.Officer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OfficerRepositoryImplTest {

        private OfficerRepositoryImpl officerRepository;
        private Officer officer;

        @BeforeEach
        public void setUp() {
            officerRepository = new OfficerRepositoryImpl();
            officer = new Officer();
            officerRepository.deleteAll();

        }


        @Test
        public void testThatICreateAndSaveAnOfficer_CountIncreases() {
            officerRepository.save(officer);
            assertEquals(1, officerRepository.count());

        }

        @Test
        public void testThatICreateAndSave2Officers_CountIncreases() {
            officerRepository.save(officer);
            Officer officer2 = new Officer();
            officerRepository.save(officer2);
            assertEquals(2, officerRepository.count());

        }

        @Test
        public void testToFindAnOfficerById() {
            officerRepository.save(officer);
            Officer officer2 = new Officer();
            officerRepository.save(officer2);
            Officer officer3 = new Officer();
            officerRepository.save(officer3);

            Officer foundOfficer = officerRepository.findById(2);
            assertEquals(3, officerRepository.count());
            assertEquals(2, foundOfficer.getId());
        }

        @Test
        public void testToFindNonExistentOfficers() {
            officerRepository.save(officer);
            Officer officer2 = new Officer();
            officerRepository.save(officer2);
            Officer foundOfficer = officerRepository.findById(3);
            assertEquals(2, officerRepository.count());
            assertNull(foundOfficer);
        }

        @Test
        public void testToFindAllOfficers() {
            officerRepository.save(officer);
            Officer officer2 = new Officer();
            officerRepository.save(officer2);
            Officer officer3 = new Officer();
            officerRepository.save(officer3);

            List<Officer> officers = officerRepository.findAll();
            assertEquals(3, officers.size());

        }

        @Test
        public void testToDeleteOfficerById() {
            officerRepository.save(officer);
            Officer officer2 = new Officer();
            officerRepository.save(officer2);
            Officer officer3 = new Officer();
            officerRepository.save(officer3);

            officerRepository.deleteById(2);
            assertEquals(2, officerRepository.count());
        }

        @Test
        public void testToDeleteAllOfficers() {
            officerRepository.save(officer);
            Officer officer2 = new Officer();
            officerRepository.save(officer2);
            Officer officer3 = new Officer();
            officerRepository.save(officer3);

            officerRepository.deleteAll();
            assertEquals(0, officerRepository.count());
        }

        @Test
        public void testThatAnOfficerExistsById() {
            officerRepository.save(officer);
            Officer driver2 = new Officer();
            officerRepository.save(driver2);
            Officer driver3 = new Officer();
            officerRepository.save(driver3);

            boolean foundOfficer = officerRepository.existsById(2);
            assertTrue(foundOfficer);


        }


}