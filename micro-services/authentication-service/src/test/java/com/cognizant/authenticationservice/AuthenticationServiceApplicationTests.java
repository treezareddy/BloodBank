package com.cognizant.authenticationservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cognizant.authenticationservice.controller.UserController;
import com.cognizant.authenticationservice.exception.UserAlreadyExistsException;
import com.cognizant.authenticationservice.model.User;
import com.cognizant.authenticationservice.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder
@AutoConfigureMockMvc
public class AuthenticationServiceApplicationTests {

       private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationServiceApplicationTests.class);

       @Autowired
       UserRepository userRepository;

       @Autowired
       UserController userController;

       @Test
       public void contextLoads() {
       }

       @Test
       public void findByUsernameTestTrue() {
              LOGGER.info("Start");
              User user = userRepository.findByUsername("user");
              String expected = "$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK";
              assertEquals(expected, user.getPassword());
              LOGGER.info("End");

       }

       @Test
       public void findByUsernameTestFalse() {
              LOGGER.info("Start");
              User user = userRepository.findByUsername("user");
              String expected = "$2a$10$R/lZJuT9skteNmAku9Y7aeutxbOKstD5xE5bHOf74M2PHZipyt3yK";
              assertNotEquals(expected, user.getPassword());
              LOGGER.info("End");

       }

       @Test
       public void signupTestTrue() throws UserAlreadyExistsException {

              User user = new User();
              user.setUsername("treeza");
              user.setFirstname("bala");
              user.setLastname("treeza");
              user.setPassword("pwd");
              user.setGender("Female");
              user.setContactNo("7680864171");
              user.setBloodgroup("B+");
              user.setEmail("treeza@gmail.com");
              user.setAge(21);
              user.setArea("guntur");
              user.setState("Andhra pradesh");
              user.setPincode(522529);
              user.setWeight(65);
              userController.signup(user);
              User userDetails = userRepository.findByUsername("treeza");
              assertNotNull(userDetails);

       }

       @Test
       public void signupTestFalse() throws UserAlreadyExistsException {

              User user = new User();
              user.setUsername("treeza");
              user.setFirstname("bala");
              user.setLastname("treeza");
              user.setPassword("pwd");
              user.setGender("Female");
              user.setContactNo("7680864171");
              user.setBloodgroup("B+");
              user.setEmail("treeza@gmail.com");
              user.setAge(21);
              user.setArea("guntur");
              user.setState("Andhra Pradesh");
              user.setPincode(522529);
              user.setWeight(65);
              userController.signup(user);
              User userDetails = userRepository.findByUsername("treeza");
              assertNull(userDetails);

       }

}
