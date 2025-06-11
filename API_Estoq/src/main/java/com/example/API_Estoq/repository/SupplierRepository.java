package com.example.API_Estoq.repository;

import com.example.API_Estoq.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    // Verificar se existe fornecedor por nome
    boolean existsByName(String name);

    // Verificar se existe fornecedor por nome, excluindo um ID específico
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END FROM Supplier s WHERE s.name = :name AND s.id != :id")
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);

    // Buscar por nome (contendo, ignorando maiúsculas/minúsculas)
    List<Supplier> findByNameContainingIgnoreCase(String name);

    // Buscar por email
    Optional<Supplier> findByEmail(String email);

    // Buscar por pessoa de contato (contendo, ignorando maiúsculas/minúsculas)
    List<Supplier> findByContactPersonContainingIgnoreCase(String contactPerson);

    // Buscar por telefone
    Optional<Supplier> findByPhone(String phone);

    // Buscar por período de registro
    List<Supplier> findByRegistrationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Verificar se existe por email
    boolean existsByEmail(String email);

    // Verificar se existe por telefone
    boolean existsByPhone(String phone);
}

