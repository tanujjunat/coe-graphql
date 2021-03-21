package com.graphql.coe.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.coe.entities.SigninPayload;
import com.graphql.coe.entities.User;

/**
 * Contains SigninPayload sub-queries
 */
public class SigninResolver implements GraphQLResolver<SigninPayload> {

    public User user(SigninPayload payload) {
        return payload.getUser();
    }
}
