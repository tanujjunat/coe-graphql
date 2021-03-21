package com.graphql.coe.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.coe.entities.Link;
import com.graphql.coe.entities.User;
import com.graphql.coe.repository.UserRepository;

/**
 * Contains Link sub-queries
 */
public class LinkResolver implements GraphQLResolver<Link> {
    
    private final UserRepository userRepository;

    public LinkResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User postedBy(Link link) {
        if (link.getUserId() == null) {
            return null;
        }
        return userRepository.findById(link.getUserId());
    }
}
