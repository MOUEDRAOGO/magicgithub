package com.openclassrooms.magicgithub.repository;

import com.openclassrooms.magicgithub.api.ApiService;
import com.openclassrooms.magicgithub.model.User;

import java.util.List;

import static com.openclassrooms.magicgithub.api.FakeApiServiceGenerator.FAKE_USERS;

public class UserRepository {

    private final ApiService apiService; // TODO: A utiliser //objet apiservice

    public UserRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public List<User> getUsers() { // liste d'obj user ; fonction return ; trouver la method getUsers()
        // TODO: A modifier
        //return null;
        return apiService.getUsers();
    }

    public void generateRandomUser() { //void fonction renvoi rien
        // TODO: A modifier
        apiService.generateRandomUser();
    }

    public void deleteUser(User user) { //void fonction renvoi rien
        // TODO: A modifier
        apiService.deleteUser(user); //user qu'on a creer en random en parametre
    }
}
