package kr.infonation.dto.users;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.users.Role;
import kr.infonation.domain.users.Users;
import lombok.Data;

public class LoginDto {

    @Data
    @ApiModel("LoginRequest")
    public static class Request{

        private String email;
        private String password;

    }

    @Data
    @ApiModel("LoginResponse")
    public static class Response{
        private Long id;
        private String name;
        private String email;
        private String phoneNo;
        private String birthDate;
        private Role role;

        public Response(Users users) {
            this.id = users.getId();
            this.email = users.getEmail();
            this.name = users.getName();
            this.phoneNo = users.getPhoneNo();
            this.birthDate = users.getBirthDate();
            this.role = users.getRole();
        }
    }
}
