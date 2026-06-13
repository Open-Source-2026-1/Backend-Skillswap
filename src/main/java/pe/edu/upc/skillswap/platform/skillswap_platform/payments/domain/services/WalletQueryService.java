package pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services;

import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.aggregates.Wallet;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface WalletQueryService {
    List<Wallet> handle(GetAllWalletsQuery query);
    Optional<Wallet> handle(GetWalletByIdQuery query);
    Optional<Wallet> handle(GetWalletByTutorIdQuery query);
}