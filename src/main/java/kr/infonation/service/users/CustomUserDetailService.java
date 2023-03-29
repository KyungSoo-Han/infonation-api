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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component("userDetailsService")
public class CustomUserDetailService  implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String email) {

        User getUser = usersRepository.findByEmail(email)
                .map(user -> makeUserObj(user))
                .orElseThrow(() -> new UsernameNotFoundException(email + " -> 데이터베이스에서 찾을 수 없습니다."));
        System.out.println("getUser = " + getUser);

        return getUser;
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
