package br.com.natanferraz.distribution_center_app.config;

import br.com.natanferraz.distribution_center_app.model.UserModel;
import br.com.natanferraz.distribution_center_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found with username"
                + username));
        return new User(userModel.getUsername(), userModel.getPassword(),
                true, true, true, true, userModel
                .getAuthorities());
    }
}
