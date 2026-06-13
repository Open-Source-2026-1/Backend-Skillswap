package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.repositories;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Wallet;

import java.util.List;
import java.util.Optional;

public interface WalletRepository {
    Wallet save(Wallet wallet);
    Optional<Wallet> findById(Long id);
    List<Wallet> findAll();
    Optional<Wallet> findByTutorId(Long tutorId);
    void deleteById(Long id);
}