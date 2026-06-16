package pe.edu.upc.skillswap.platform.skillswap_platform.payments.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Wallet;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.WalletQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.infrastructure.persistence.jpa.repositories.WalletRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WalletQueryServiceImpl implements WalletQueryService {

    private final WalletRepository walletRepository;

    public WalletQueryServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public List<Wallet> handle(GetAllWalletsQuery query) {
        return this.walletRepository.findAll();
    }

    @Override
    public Optional<Wallet> handle(GetWalletByIdQuery query) {
        return this.walletRepository.findById(query.walletId());
    }

    @Override
    public Optional<Wallet> handle(GetWalletByTutorIdQuery query) {
        return this.walletRepository.findByTutorId(query.tutorId());
    }
}