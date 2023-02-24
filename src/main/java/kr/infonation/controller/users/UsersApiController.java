package kr.infonation.controller.users;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.domain.users.Users;
import kr.infonation.dto.users.LoginDto;
import kr.infonation.dto.users.UserDto;
import kr.infonation.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UsersApiController {

    private final UsersService usersService;

    @PostMapping
    public ResponseDto<UserDto.CreateResponse> createUsers(@RequestBody UserDto.CreateRequest createRequest){
        Users users = usersService.createUser(createRequest);

        UserDto.CreateResponse response = new UserDto.CreateResponse(users);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseDto<LoginDto.Response> findLoginUser(@RequestBody LoginDto.Request request){

        Users loginUser = usersService.findLoginUser(request.getEmail(), request.getPassword());

        LoginDto.Response response = new LoginDto.Response(loginUser);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }
}
