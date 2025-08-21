package ada.tech.lms.screen;

import ada.tech.lms.domain.BankAccount;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.io.IOException;
import java.util.Scanner;

public class WithdrawExecutedOption implements ExecutedOption {

	private BankService bankService;
	private Scanner scanner;
	private User userAccount;
	public WithdrawExecutedOption(BankService bankService, Scanner scanner, User userAccount) {
		this.bankService = bankService;
		this.userAccount = userAccount;
		this.scanner = scanner;
	}

	@Override
	public void execute() throws IOException {
		System.out.println("Valor informado para o saque?");
		var value = scanner.nextDouble();
		BankAccount account = bankService.findAccountByUser(userAccount);
		account.getBalance();
		account.withdraw(value);

	}
}
