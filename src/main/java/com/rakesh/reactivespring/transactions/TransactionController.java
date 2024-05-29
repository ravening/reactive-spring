package com.rakesh.reactivespring.transactions;

import com.rakesh.reactivespring.transactions.api.TransactionsApi;
import com.rakesh.reactivespring.transactions.model.AuthorizeTransactionResponse;
import com.rakesh.reactivespring.transactions.model.OptionalPendingTransaction;
import com.rakesh.reactivespring.transactions.model.PendingTransaction;
import com.rakesh.reactivespring.transactions.model.PendingTransactions;
import com.rakesh.reactivespring.transactions.model.TransactionAuthorizationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class TransactionController implements TransactionsApi {


    @Override
    public Mono<ResponseEntity<AuthorizeTransactionResponse>> authorizeTransaction(String transactionId, String smSession, Mono<TransactionAuthorizationRequest> transactionAuthorizationRequest, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<OptionalPendingTransaction>> getLatestTransaction(String smSession, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<PendingTransaction>> getTransactionById(String transactionId, String smSession, ServerWebExchange exchange) {
        return null;
    }

    @Override
    public Mono<ResponseEntity<PendingTransactions>> getTransactions(String smSession, ServerWebExchange exchange) {
        return null;
    }
}
