package kr.infonation.repository.cust;

import kr.infonation.domain.cust.Customer;
import kr.infonation.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("select c from Customer c where c.biz.id = :bizId ")
    List<Customer> findCustomer(@Param("bizId") Long bizId);
}
