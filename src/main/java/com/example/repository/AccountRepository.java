package com.example.repository;
import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for performing CRUD operations, extending JpaRepository for custom query methods.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>
{
    /**
     * Retrieves an account based on username.
     * 
     * @param username The username of the account to retrieve.
     * @return The account associated with the given username, or null if not found.
     */
    Account findByUsername(String username);
}
