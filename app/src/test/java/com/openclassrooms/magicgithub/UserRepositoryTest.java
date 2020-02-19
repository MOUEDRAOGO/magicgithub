package com.openclassrooms.magicgithub;

import com.openclassrooms.magicgithub.api.FakeApiServiceGenerator;
import com.openclassrooms.magicgithub.di.Injection;
import com.openclassrooms.magicgithub.model.User;
import com.openclassrooms.magicgithub.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.List;
import java.util.stream.Collectors;

import static com.openclassrooms.magicgithub.api.FakeApiServiceGenerator.FAKE_USERS;
import static com.openclassrooms.magicgithub.api.FakeApiServiceGenerator.FAKE_USERS_RANDOM;
import static org.junit.Assert.*;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;


/**
 * Unit test, which will execute on a JVM.
 * Testing UserRepository.
 */
@RunWith(JUnit4.class)
public class UserRepositoryTest {
    
    private UserRepository userRepository;
    
    @Before
    public void setup() {
        userRepository = Injection.createUserRepository();
    }
    
    @Test //recupere un fake user et l insert ds le tableau
    public void getUsersWithSuccess() {
        List<User> usersActual = userRepository.getUsers();
        List<User> usersExpected = FAKE_USERS; // chemin acces dispo ds les import
        assertThat(usersActual, containsInAnyOrder(usersExpected.toArray()));
    }

    @Test //genere un user
    public void generateRandomUserWithSuccess() {
        userRepository.getUsers().clear();
        userRepository.generateRandomUser();
        User user = userRepository.getUsers().get(0);
        assertEquals(1, userRepository.getUsers().size());
        assertTrue(FAKE_USERS_RANDOM.stream().map(User::getAvatarUrl).collect(Collectors.toList()).contains(user.getAvatarUrl()));
        assertTrue(FAKE_USERS_RANDOM.stream().map(User::getId).collect(Collectors.toList()).contains(user.getId()));
        assertTrue(FAKE_USERS_RANDOM.stream().map(User::getLogin).collect(Collectors.toList()).contains(user.getLogin()));
        assertFalse(FAKE_USERS.stream().map(User::getAvatarUrl).collect(Collectors.toList()).contains(user.getAvatarUrl()));
        assertFalse(FAKE_USERS.stream().map(User::getId).collect(Collectors.toList()).contains(user.getId()));
        assertFalse(FAKE_USERS.stream().map(User::getLogin).collect(Collectors.toList()).contains(user.getLogin()));
    }

    @Test // suppression d un user
    public void deleteUserWithSuccess() {
        User userToDelete = userRepository.getUsers().get(0); // recuperation user index 0
        userRepository.deleteUser(userToDelete); // delete user
        assertFalse(userRepository.getUsers().contains(userToDelete)); //verifie si user a bien ete delete
    }
}