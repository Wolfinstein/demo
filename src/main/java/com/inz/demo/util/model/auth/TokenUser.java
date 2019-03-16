package com.inz.demo.util.model.auth;

import com.inz.demo.domain.User;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class TokenUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public TokenUser(User user) {
        //super(String.valueOf(user.getId()), user.getPasswordHash(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        super(user.getUserLogin(), user.getUserPassword(), true, true, true, true, AuthorityUtils.createAuthorityList(user.getRoles()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getRole() {
        return user.getRoles();
    }

}
