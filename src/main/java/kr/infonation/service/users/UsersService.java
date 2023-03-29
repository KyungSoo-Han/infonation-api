package kr.infonation.service.users;

import kr.infonation.domain.users.Users;
import kr.infonation.dto.users.LoginDto;
import kr.infonation.dto.users.UserDto;
import kr.infonation.repository.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public Users createUser(UserDto.CreateRequest createRequest){
        String encodingPassword = passwordEncoder.encode(createRequest.getPassword());
        return usersRepository.save(createRequest.toEntity(encodingPassword));
    }

    public Users getReferenceById(Long userId){
        return usersRepository.getReferenceById(userId);
    }

}
