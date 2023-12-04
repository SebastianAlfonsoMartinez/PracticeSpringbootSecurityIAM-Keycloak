package com.example.msusers.repository;

import com.example.msusers.domain.Bill;
import com.example.msusers.domain.User;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor

public class UserRepository implements IUserRepository {

    private final Keycloak keycloakClient;
    private final BillRepositoryFeign billRepositoryFeign;


    @Value("${dh.keycloak.realm}")
    private String realm;


    @Override
    public Optional<User> findById(String userId) {
        UserRepresentation userRepresentation = keycloakClient.realm(realm).users().get(userId).toRepresentation();
        User user = toUser(userRepresentation);
        billRepositoryFeign.assignUserToBill(user.getUserId());

        List<Bill> billUser = billRepositoryFeign.findByUser(user.getUserId());
        user.setBills(billUser);
        return Optional.of(user);
    }


    private User toUser (UserRepresentation userRepresentation){

        return User.builder()
                .userId(userRepresentation.getId())
                .userName(userRepresentation.getUsername())
                .email(userRepresentation.getEmail())
                .fistName(userRepresentation.getFirstName())
                .build();

    }

}
