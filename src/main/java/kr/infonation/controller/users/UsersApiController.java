package kr.infonation.controller.users;

import kr.infonation.common.dto.ResponseDto;
import kr.infonation.config.jwt.JwtFilter;
import kr.infonation.config.jwt.TokenProvider;
import kr.infonation.domain.users.Users;
import kr.infonation.dto.users.LoginDto;
import kr.infonation.dto.users.UserDto;
import kr.infonation.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UsersApiController {

    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UsersService usersService;

    @PostMapping
    public ResponseDto<UserDto.CreateResponse> createUsers(@RequestBody UserDto.CreateRequest createRequest){
        Users users = usersService.createUser(createRequest);

        UserDto.CreateResponse response = new UserDto.CreateResponse(users);

        return ResponseDto.SuccessResponse(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseDto<Map> Login(@RequestBody LoginDto.Request request){

        System.out.println("request = " + request);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        System.out.println("authenticationToken = " + authenticationToken);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("authentication = " + authentication);

        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        Map<String, Object> map = new HashMap<>();
        map.put("token", jwt);

        return ResponseDto.SuccessResponse(map, HttpStatus.OK);
    }
}
