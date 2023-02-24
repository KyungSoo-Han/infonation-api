package kr.infonation.dto.users;

import io.swagger.annotations.ApiModel;
import kr.infonation.domain.users.Role;
import kr.infonation.domain.users.Users;
import lombok.Data;

public class UserDto {

    @Data
    @ApiModel("UserCreateRequest")
    public static class CreateRequest {
        private String email;
        private String name;
        private String password;
        private String phoneNo;
        private String birthDate;
        private Role role;

        public Users toEntity(){
            return Users.builder()
                    .email(email)
                    .birthDate(birthDate)
                    .name(name)
                    .phoneNo(phoneNo)
                    .password(password)
                    .role(role)
                    .build();
        }
    }


    @Data
    @ApiModel("UserCreateResponse")
    public static class CreateResponse {
        private Long id;
        private String email;
        private String name;
        private String phoneNo;
        private String birthDate;
        private Role role;

        public CreateResponse(Users users) {
            this.id = users.getId();
            this.email = users.getEmail();
            this.name = users.getName();
            this.phoneNo = users.getPhoneNo();
            this.birthDate = users.getBirthDate();
            this.role = users.getRole();
        }
    }
}
