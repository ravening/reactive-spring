package com.rakesh.reactivespring.transactions;

import com.rakesh.reactivespring.transactions.api.TransactionsApi;
import com.rakesh.reactivespring.transactions.model.AuthorizeTransactionResponse;
import com.rakesh.reactivespring.transactions.model.OptionalPendingTransaction;
import com.rakesh.reactivespring.transactions.model.PendingTransaction;
import com.rakesh.reactivespring.transactions.model.PendingTransactions;
import com.rakesh.reactivespring.transactions.model.TransactionAuthorizationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController implements TransactionsApi {

    @Override
    public ResponseEntity<AuthorizeTransactionResponse> authorizeTransaction(String transactionId, String smSession, TransactionAuthorizationRequest transactionAuthorizationRequest) {
        return null;
    }

    @Override
    public ResponseEntity<OptionalPendingTransaction> getLatestTransaction(String smSession) {
        return null;
    }

    @Override
    public ResponseEntity<PendingTransaction> getTransactionById(String transactionId, String smSession) {
        return null;
    }

    @Override
    public ResponseEntity<PendingTransactions> getTransactions(String smSession) {
        return null;
    }
}
