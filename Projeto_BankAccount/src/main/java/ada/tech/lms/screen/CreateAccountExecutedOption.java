package ada.tech.lms.screen;

import ada.tech.lms.domain.SimpleAccount;
import ada.tech.lms.domain.User;
import ada.tech.lms.service.BankService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.util.Scanner;

public class CreateAccountExecutedOption implements ExecutedOption {
	private BankService bankService;
	private Scanner scanner;

	public CreateAccountExecutedOption(BankService bankService, Scanner scanner) {
		this.bankService = bankService;
		this.scanner = scanner;
	}

	@Override
	public void execute() throws IOException {
		System.out.println("Informe o CPF");
		var cpf = scanner.next();
		System.out.println("Informe o Nome do usuário");
		var name = scanner.next();
		var generatedAccountNumber = generateAccountNumber();
		bankService.addAccount(new SimpleAccount(generatedAccountNumber, new User(cpf,name), 0.0));
		Path path = Path.of(".\\src\\transactions_statements\\"+cpf+".txt");
		Files.writeString(path,"", StandardCharsets.UTF_8, StandardOpenOption.CREATE,StandardOpenOption.APPEND);
		System.out.println("Conta criada com sucesso");
	}

	private String generateAccountNumber() {
		var random = new Random();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			stringBuilder.append(random.nextInt(0,9));
		}
		return stringBuilder.toString();
	}
}
