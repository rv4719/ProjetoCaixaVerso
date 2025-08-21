package ada.tech.lms.screen;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.Transaction;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class GetTransactions implements ExecutedOption{

    private BankService bankService;
    private Scanner scanner;
    private User userAccount;

    public GetTransactions(BankService bankService, User userAccount) {
        this.bankService = bankService;
        this.userAccount = userAccount;
    }

    @Override
    public void execute() throws IOException {
        BankAccount account = bankService.findAccountByUser(userAccount);

        System.out.println("Cliente: "+account.getOwner().getName());
        System.out.println("Número da Conta: "+account.getAccountNumber());
        account.listaTransaction.sort(Comparator.comparing(Transaction::getData).reversed());
        account.listaTransaction.forEach(System.out::println);


    }
}

