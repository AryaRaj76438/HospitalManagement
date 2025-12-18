package com.quantumsyntax.repository;

import com.quantumsyntax.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}
