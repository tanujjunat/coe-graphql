package com.graphql.coe.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.graphql.coe.entities.Link;
import com.graphql.coe.entities.User;
import com.graphql.coe.entities.Vote;
import com.graphql.coe.repository.LinkRepository;
import com.graphql.coe.repository.UserRepository;

/**
 * Contains vote sub-queries
 */
public class VoteResolver implements GraphQLResolver<Vote> {
    
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public VoteResolver(LinkRepository linkRepository, UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

    public User user(Vote vote) {
        return userRepository.findById(vote.getUserId());
    }
    
    public Link link(Vote vote) {
        return linkRepository.findById(vote.getLinkId());
    }
}
