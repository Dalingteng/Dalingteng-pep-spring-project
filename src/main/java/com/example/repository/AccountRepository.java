package com.example.repository;
import com.example.entity.Account;

import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;


public interface AccountRepository extends JpaRepository<Account, Integer>
{
    Account findByUsername(String username);
}
