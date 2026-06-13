package pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.AddFundsToWalletCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.commands.WithdrawFundsFromWalletCommand;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.model.queries.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.WalletCommandService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.domain.services.WalletQueryService;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.resources.*;
import pe.edu.upc.skillswap.platform.skillswap_platform.payments.interfaces.rest.transform.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT})
@RestController
@RequestMapping(value = "/api/v1/wallets", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Wallets", description = "Wallets Management Endpoints")
public class WalletsController {

    private final WalletCommandService walletCommandService;
    private final WalletQueryService walletQueryService;

    public WalletsController(WalletCommandService walletCommandService,
                             WalletQueryService walletQueryService) {
        this.walletCommandService = walletCommandService;
        this.walletQueryService = walletQueryService;
    }

    @PostMapping
    public ResponseEntity<WalletResource> createWallet(@RequestBody CreateWalletResource resource) {
        var command = CreateWalletCommandFromResourceAssembler.toCommandFromResource(resource);
        var wallet = walletCommandService.handle(command);
        if (wallet.isEmpty()) return ResponseEntity.badRequest().build();
        var walletResource = WalletResourceFromEntityAssembler.toResourceFromEntity(wallet.get());
        return new ResponseEntity<>(walletResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<WalletResource>> getAllWallets() {
        var query = new GetAllWalletsQuery();
        var wallets = walletQueryService.handle(query);
        var resources = wallets.stream()
                .map(WalletResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResource> getWalletById(@PathVariable Long walletId) {
        var query = new GetWalletByIdQuery(walletId);
        var wallet = walletQueryService.handle(query);
        if (wallet.isEmpty()) return ResponseEntity.notFound().build();
        var resource = WalletResourceFromEntityAssembler.toResourceFromEntity(wallet.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<WalletResource> getWalletByTutorId(@PathVariable Long tutorId) {
        var query = new GetWalletByTutorIdQuery(tutorId);
        var wallet = walletQueryService.handle(query);
        if (wallet.isEmpty()) return ResponseEntity.notFound().build();
        var resource = WalletResourceFromEntityAssembler.toResourceFromEntity(wallet.get());
        return ResponseEntity.ok(resource);
    }

    @PutMapping("/{walletId}/add-funds")
    public ResponseEntity<WalletResource> addFunds(@PathVariable Long walletId,
                                                   @RequestBody WalletFundsResource resource) {
        var command = new AddFundsToWalletCommand(walletId, resource.amount());
        var wallet = walletCommandService.handle(command);
        if (wallet.isEmpty()) return ResponseEntity.notFound().build();
        var walletResource = WalletResourceFromEntityAssembler.toResourceFromEntity(wallet.get());
        return ResponseEntity.ok(walletResource);
    }

    @PutMapping("/{walletId}/withdraw-funds")
    public ResponseEntity<WalletResource> withdrawFunds(@PathVariable Long walletId,
                                                        @RequestBody WalletFundsResource resource) {
        var command = new WithdrawFundsFromWalletCommand(walletId, resource.amount());
        var wallet = walletCommandService.handle(command);
        if (wallet.isEmpty()) return ResponseEntity.notFound().build();
        var walletResource = WalletResourceFromEntityAssembler.toResourceFromEntity(wallet.get());
        return ResponseEntity.ok(walletResource);
    }
}