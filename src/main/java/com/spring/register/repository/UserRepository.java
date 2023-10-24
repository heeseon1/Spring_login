package com.spring.register.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.register.domain.Users;


public interface UserRepository extends JpaRepository<Users, Long>{
	// email로 사용자 정보 가져옴 (findByEmail을 사용했기 때문) :이부분에 id를 사용하면 id로 사용자 정보 가졍
	Optional<Users> findByEmail(String email);
}
