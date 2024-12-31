package com.roman.insure_manage.transaction;

import com.roman.insure_manage.common.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> createTransaction (@Valid @RequestBody TransactionDto transactionDto) {
        transactionService.createTransaction(transactionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateTransaction (
            @PathVariable UUID id,
            TransactionUpdateDto transactionUpdateDto
    ) {
        transactionService.updateTransactionById(id,transactionUpdateDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    public ResponseEntity<List<TransactionDto>> getAllTransactions () {
        List<TransactionDto> transactionDtos =
                transactionService.getAllTransaction();

        return new ResponseEntity<>(transactionDtos, HttpStatus.OK);
    }

    @GetMapping("/paginated")
    public ResponseEntity<PageResponse<TransactionDto>> getAllTransactionPaginated (
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) UUID clientId,
            @RequestParam(required = false) UUID productId

    ) {

        PageResponse<TransactionDto> transactionDtos =
                transactionService.getAllTransactionPaginated(page, size,
                        clientId, productId);

        return ResponseEntity.ok(transactionDtos);

    }
}
