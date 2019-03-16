package com.inz.demo.util.model.auth;

import com.inz.demo.domain.User;
import com.inz.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepo;
    private final AccountStatusUserDetailsChecker detailsChecker = new AccountStatusUserDetailsChecker();

    @Autowired
    public UserAuthService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public final TokenUser loadUserByUsername(String username) {

        final User user = userRepo.findByUserLogin(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        TokenUser currentUser;
        currentUser = new TokenUser(user);

        detailsChecker.check(currentUser);
        return currentUser;
    }
}
