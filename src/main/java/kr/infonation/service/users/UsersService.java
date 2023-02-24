package kr.infonation.service.users;

import kr.infonation.domain.users.Users;
import kr.infonation.dto.users.LoginDto;
import kr.infonation.dto.users.UserDto;
import kr.infonation.repository.users.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;
    @Transactional
    public Users createUser(UserDto.CreateRequest createRequest){
        return usersRepository.save(createRequest.toEntity());
    }

    public Users getReferenceById(Long userId){
        return usersRepository.getReferenceById(userId);
    }

    public Users findLoginUser(String email, String password){
        return usersRepository.findLoginUser(email, password);
    }
}
