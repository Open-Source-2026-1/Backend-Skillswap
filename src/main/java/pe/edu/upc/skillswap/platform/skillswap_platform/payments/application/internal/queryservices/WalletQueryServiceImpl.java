package pe.edu.upc.skillswap.platform.skillswap_platform.payments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Wallet;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.WalletQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.infrastructure.persistence.jpa.repositories.WalletJpaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WalletQueryServiceImpl implements WalletQueryService {

    private final WalletJpaRepository walletJpaRepository;

    public WalletQueryServiceImpl(WalletJpaRepository walletJpaRepository) {
        this.walletJpaRepository = walletJpaRepository;
    }

    @Override
    public List<Wallet> handle(GetAllWalletsQuery query) {
        return walletJpaRepository.findAll();
    }

    @Override
    public Optional<Wallet> handle(GetWalletByIdQuery query) {
        return walletJpaRepository.findById(query.walletId());
    }

    @Override
    public Optional<Wallet> handle(GetWalletByTutorIdQuery query) {
        return walletJpaRepository.findByTutorId(query.tutorId());
    }
}