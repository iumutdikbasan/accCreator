package com.example.accaount.repository;

import com.example.accaount.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccaountRepository extends JpaRepository<Account, String> {
}
