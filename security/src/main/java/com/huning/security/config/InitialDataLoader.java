package com.huning.security.config;

import com.huning.security.accounts.domain.AccountDomain;
import com.huning.security.accounts.dto.AccountDTO;
import com.huning.security.accounttransactions.domain.AccountTransactionDomain;
import com.huning.security.accounttransactions.dto.AccountTransactionDTO;
import com.huning.security.authority.domain.AuthorityDomain;
import com.huning.security.authority.dto.AuthorityDTO;
import com.huning.security.entities.AccountEntity;
import com.huning.security.entities.AccountTransactionEntity;
import com.huning.security.entities.CustomerEntity;
import com.huning.security.loans.dto.LoanDTO;
import com.huning.security.repositories.AccountRepository;
import com.huning.security.repositories.AccountTransactionRepository;
import com.huning.security.repositories.AuthorityRepository;
import com.huning.security.customer.domain.CustomerDomain;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.repositories.CardRepository;
import com.huning.security.repositories.ContactMessageRepository;
import com.huning.security.repositories.CustomerRepository;
import com.huning.security.repositories.LoanRepository;
import com.huning.security.repositories.NoticeDetailRepository;
import com.huning.security.uesr.domain.UserDomain;
import com.huning.security.uesr.dto.UserDTO;
import com.huning.security.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitialDataLoader {

  private final CustomerRepository customerRepository;
  private final AccountRepository accountRepository;
  private final AccountTransactionRepository accountTransactionRepository;
  private final AuthorityRepository authorityRepository;
  private final CardRepository cardRepository;
  private final ContactMessageRepository contactMessageRepository;
  private final LoanRepository loanRepository;
  private final NoticeDetailRepository noticeDetailRepository;
  private final PasswordEncoder passwordEncoder;

  @PostConstruct
  public void init() {
    // 초기 데이터 생성 및 저장
    CustomerDTO customerDTO1 = CustomerDTO.builder()
      .name("test")
      .email("test@mail.com")
      .mobileNumber("01000000000")
      .pwd(passwordEncoder.encode("test"))
      .role("admin")
      .createDt(LocalDateTime.now())
      .build();

    CustomerEntity customerEntity = customerRepository.save(
      CustomerDomain.of(customerDTO1).toCreateEntity());

    AccountDTO accountDTO1 = AccountDTO.builder()
      .accountNumber(12345678L)
      .accountType("Savings")
      .branchAddress("123 Main street, New York")
      .createDt(LocalDateTime.now())
      .build();

    AccountEntity accountEntity = AccountDomain.of(accountDTO1).toCreateEntity();
    accountEntity.setCustomer(customerEntity);
    accountRepository.save(accountEntity);

    AccountTransactionDTO accountTransactionDTO1 = AccountTransactionDTO.builder()
      .transactionId(String.valueOf(UUID.randomUUID()))
      .transactionDt(LocalDateTime.now().minusDays(7))
      .transactionSummary("Coffee Shop")
      .transactionType("Withdrawal")
      .transactionAmt(30)
      .closingBalance(34500)
      .createDt(LocalDateTime.now().minusDays(7))
      .build();

    AccountTransactionDTO accountTransactionDTO2 = AccountTransactionDTO.builder()
      .transactionId(String.valueOf(UUID.randomUUID()))
      .transactionDt(LocalDateTime.now().minusDays(6))
      .transactionSummary("Uber")
      .transactionType("Withdrawal")
      .transactionAmt(100)
      .closingBalance(43300)
      .createDt(LocalDateTime.now().minusDays(6))
      .build();

    AccountTransactionDTO accountTransactionDTO3 = AccountTransactionDTO.builder()
      .transactionId(String.valueOf(UUID.randomUUID()))
      .transactionDt(LocalDateTime.now().minusDays(5))
      .transactionSummary("Self Deposit")
      .transactionType("Deposit")
      .transactionAmt(500)
      .closingBalance(34900)
      .createDt(LocalDateTime.now().minusDays(5))
      .build();

    AccountTransactionDTO accountTransactionDTO4 = AccountTransactionDTO.builder()
      .transactionId(String.valueOf(UUID.randomUUID()))
      .transactionDt(LocalDateTime.now().minusDays(4))
      .transactionSummary("Ebay")
      .transactionType("Withdrawal")
      .transactionAmt(600)
      .closingBalance(34300)
      .createDt(LocalDateTime.now().minusDays(4))
      .build();

    AccountTransactionDTO accountTransactionDTO5 = AccountTransactionDTO.builder()
      .transactionId(String.valueOf(UUID.randomUUID()))
      .transactionDt(LocalDateTime.now().minusDays(2))
      .transactionSummary("OnlineTransfer")
      .transactionType("Deposit")
      .transactionAmt(700)
      .closingBalance(35000)
      .createDt(LocalDateTime.now().minusDays(2))
      .build();

    AccountTransactionDTO accountTransactionDTO6 = AccountTransactionDTO.builder()
      .transactionId(String.valueOf(UUID.randomUUID()))
      .transactionDt(LocalDateTime.now().minusDays(1))
      .transactionSummary("Amazon.com")
      .transactionType("Withdrawal")
      .transactionAmt(100)
      .closingBalance(34900)
      .createDt(LocalDateTime.now().minusDays(1))
      .build();

    AccountTransactionEntity accountTransaction1 = AccountTransactionDomain.of(
      accountTransactionDTO1).toCreateEntity();
    AccountTransactionEntity accountTransaction2 = AccountTransactionDomain.of(
      accountTransactionDTO2).toCreateEntity();
    AccountTransactionEntity accountTransaction3 = AccountTransactionDomain.of(
      accountTransactionDTO3).toCreateEntity();
    AccountTransactionEntity accountTransaction4 = AccountTransactionDomain.of(
      accountTransactionDTO4).toCreateEntity();
    AccountTransactionEntity accountTransaction5 = AccountTransactionDomain.of(
      accountTransactionDTO5).toCreateEntity();
    AccountTransactionEntity accountTransaction6 = AccountTransactionDomain.of(
      accountTransactionDTO6).toCreateEntity();

    accountTransaction1.setAccount(accountEntity);
    accountTransaction1.setCustomer(customerEntity);
    accountTransaction2.setAccount(accountEntity);
    accountTransaction2.setCustomer(customerEntity);
    accountTransaction3.setAccount(accountEntity);
    accountTransaction3.setCustomer(customerEntity);
    accountTransaction4.setAccount(accountEntity);
    accountTransaction4.setCustomer(customerEntity);
    accountTransaction5.setAccount(accountEntity);
    accountTransaction5.setCustomer(customerEntity);
    accountTransaction6.setAccount(accountEntity);
    accountTransaction6.setCustomer(customerEntity);

    accountTransactionRepository.save(accountTransaction1);
    accountTransactionRepository.save(accountTransaction2);
    accountTransactionRepository.save(accountTransaction3);
    accountTransactionRepository.save(accountTransaction4);
    accountTransactionRepository.save(accountTransaction5);
    accountTransactionRepository.save(accountTransaction6);

    LoanDTO loanDTO1 = LoanDTO.builder()
      .startDt(LocalDateTime.of(2020, 10, 13, 0, 0))
      .loanType("Home")
      .totalLoan(200000)
      .amountPaid(50000)
      .outstandingAmount(150000)
      .createDt(LocalDateTime.of(2020, 10, 13, 0, 0))
      .build();

  }
}
