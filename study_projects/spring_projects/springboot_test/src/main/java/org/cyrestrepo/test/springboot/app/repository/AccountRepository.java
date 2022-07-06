package org.cyrestrepo.test.springboot.app.repository;

import org.cyrestrepo.test.springboot.app.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByPersonName(String personName);
    //List<Account> findAll();
    //Optional<Account> findById(Long id);
    //void update(Account account);
}
