package com.example.API_Estoq.service;

import com.example.API_Estoq.model.Supplier;
import com.example.API_Estoq.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    // CREATE - Criar novo fornecedor
    public Supplier createSupplier(Supplier supplier) {
        // Validação básica
        if (supplier.getName() == null || supplier.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do fornecedor é obrigatório");
        }

        // Verificar se já existe fornecedor com o mesmo nome
        if (supplierRepository.existsByName(supplier.getName())) {
            throw new IllegalArgumentException("Já existe um fornecedor com este nome");
        }

        // Definir data de registro se não foi fornecida
        if (supplier.getRegistrationDate() == null) {
            supplier.setRegistrationDate(LocalDateTime.now());
        }

        return supplierRepository.save(supplier);
    }

    // READ - Buscar todos os fornecedores
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    // READ - Buscar fornecedor por ID
    public Optional<Supplier> getSupplierById(Long id) {
        return supplierRepository.findById(id);
    }

    // UPDATE - Atualizar fornecedor
    public Supplier updateSupplier(Long id, Supplier supplierDetails) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            Supplier supplier = optionalSupplier.get();

            // Validações e atualizações
            if (supplierDetails.getName() != null && !supplierDetails.getName().trim().isEmpty()) {
                // Verificar se o novo nome já existe em outro fornecedor
                if (supplierRepository.existsByNameAndIdNot(supplierDetails.getName(), id)) {
                    throw new IllegalArgumentException("Já existe outro fornecedor com este nome");
                }
                supplier.setName(supplierDetails.getName());
            }

            if (supplierDetails.getContactPerson() != null) {
                supplier.setContactPerson(supplierDetails.getContactPerson());
            }

            if (supplierDetails.getPhone() != null) {
                supplier.setPhone(supplierDetails.getPhone());
            }

            if (supplierDetails.getEmail() != null) {
                supplier.setEmail(supplierDetails.getEmail());
            }

            if (supplierDetails.getAddress() != null) {
                supplier.setAddress(supplierDetails.getAddress());
            }

            return supplierRepository.save(supplier);
        } else {
            throw new RuntimeException("Fornecedor não encontrado com id: " + id);
        }
    }

    // DELETE - Deletar fornecedor
    public void deleteSupplier(Long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
        } else {
            throw new RuntimeException("Fornecedor não encontrado com id: " + id);
        }
    }

    // MÉTODOS DE BUSCA ESPECÍFICOS

    // Buscar fornecedores por nome
    public List<Supplier> getSuppliersByName(String name) {
        return supplierRepository.findByNameContainingIgnoreCase(name);
    }

    // Buscar fornecedor por email
    public Optional<Supplier> getSupplierByEmail(String email) {
        return supplierRepository.findByEmail(email);
    }

    // Buscar fornecedores por pessoa de contato
    public List<Supplier> getSuppliersByContactPerson(String contactPerson) {
        return supplierRepository.findByContactPersonContainingIgnoreCase(contactPerson);
    }

    // Buscar fornecedores por telefone
    public Optional<Supplier> getSupplierByPhone(String phone) {
        return supplierRepository.findByPhone(phone);
    }

    // Buscar fornecedores registrados em um período
    public List<Supplier> getSuppliersByRegistrationDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return supplierRepository.findByRegistrationDateBetween(startDate, endDate);
    }

    // Contar total de fornecedores
    public Long getTotalSuppliers() {
        return supplierRepository.count();
    }

    // Verificar se email já existe
    public boolean existsByEmail(String email) {
        return supplierRepository.existsByEmail(email);
    }

    // Verificar se telefone já existe
    public boolean existsByPhone(String phone) {
        return supplierRepository.existsByPhone(phone);
    }
}