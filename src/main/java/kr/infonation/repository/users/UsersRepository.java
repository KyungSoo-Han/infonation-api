package kr.infonation.repository.users;

import kr.infonation.domain.users.Users;
import kr.infonation.dto.users.LoginDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Users,Long> {


    @Query("select u from Users u where u.email = :email and u.password = :password ")
    Users findLoginUser(@Param("email") String email, @Param("password") String password);
}
