package com.learningJwt.dao;

import com.learningJwt.model.User;
import com.learningJwt.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Response registerUser(User user) {
        Response response = new Response();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            entityManager.persist(user);
            response.setMessage("Registration successful");
            response.setOperation(true);
            response.setStatusCode(201);
        } catch (Exception e) {
            response.setMessage("Registration failed");
            response.setOperation(false);
            response.setStatusCode(500);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public Response loginUser(User user) {
        Response response = new Response();
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
            query.setParameter("email", user.getEmail());
            List<User> result = query.getResultList();

            if (result.isEmpty()) {
                response.setMessage("User not found");
                response.setOperation(false);
                response.setStatusCode(404);
            } else {
                User userFromDB = result.get(0);
                if (passwordEncoder.matches(user.getPassword(), userFromDB.getPassword())) {
                    response.setMessage("Login successful");
                    response.setOperation(true);
                    response.setStatusCode(200);
                    // Here you could set a token if needed, or handle any additional logic
                } else {
                    response.setMessage("Incorrect password");
                    response.setOperation(false);
                    response.setStatusCode(401);
                }
            }
        } catch (Exception e) {
            response.setMessage("Login failed");
            response.setOperation(false);
            response.setStatusCode(500);
            e.printStackTrace();
        }
        return response;
    }

    @Override
    public User findByUsername(String username) {
        try {
            Query query = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email");
            query.setParameter("email", username);
            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}