package kr.infonation.service.users;

import kr.infonation.domain.users.Users;
import kr.infonation.dto.users.LoginDto;
import kr.infonation.repository.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailService  implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        return usersRepository.findByEmail(email)
                //.map(user -> makeUserObj(user))
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 이메일입니다."));
    }

    private org.springframework.security.core.userdetails.User makeUserObj(Users users) {

        String Role = users.getRole().toString();

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(Role));

        User user = new User(users.getEmail(), users.getPassword(), authorities);
        System.out.println("makeUserObj user = " + user);

        return user;
    }
}
