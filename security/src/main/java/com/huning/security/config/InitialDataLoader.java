package com.huning.security.config;

import com.huning.security.accounts.domain.AccountDomain;
import com.huning.security.accounts.dto.AccountDTO;
import com.huning.security.accounttransactions.domain.AccountTransactionDomain;
import com.huning.security.accounttransactions.dto.AccountTransactionDTO;
import com.huning.security.cards.domain.CardDomain;
import com.huning.security.cards.dto.CardDTO;
import com.huning.security.customer.domain.CustomerDomain;
import com.huning.security.customer.dto.CustomerDTO;
import com.huning.security.entities.AccountEntity;
import com.huning.security.entities.AccountTransactionEntity;
import com.huning.security.entities.CardEntity;
import com.huning.security.entities.CustomerEntity;
import com.huning.security.entities.LoanEntity;
import com.huning.security.entities.NoticeDetailEntity;
import com.huning.security.loans.domain.LoanDomain;
import com.huning.security.loans.dto.LoanDTO;
import com.huning.security.noticedetails.domain.NoticeDetailDomain;
import com.huning.security.noticedetails.dto.NoticeDetailDTO;
import com.huning.security.repositories.AccountRepository;
import com.huning.security.repositories.AccountTransactionRepository;
import com.huning.security.repositories.AuthorityRepository;
import com.huning.security.repositories.CardRepository;
import com.huning.security.repositories.ContactMessageRepository;
import com.huning.security.repositories.CustomerRepository;
import com.huning.security.repositories.LoanRepository;
import com.huning.security.repositories.NoticeDetailRepository;
import jakarta.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
  @Transactional
  public void init() {

    AccountDTO accountDTO1 = AccountDTO.builder()
        .accountNumber(12345678L)
        .accountType("Savings")
        .branchAddress("123 Main street, New York")
        .createDt(LocalDateTime.now())
        .build();

    AccountEntity accountEntity = AccountDomain.of(accountDTO1).toCreateEntity();

    // 초기 데이터 생성 및 저장
    CustomerDTO customerDTO1 = CustomerDTO.builder()
      .name("test")
      .email("test@mail.com")
      .mobileNumber("01000000000")
      .pwd(passwordEncoder.encode("test"))
      .role("admin")
      .createDt(LocalDateTime.now())
      .build();


    CustomerEntity customerEntity = CustomerDomain.of(customerDTO1).toCreateEntity();
    customerEntity.setAccount(accountEntity);
    customerRepository.save(customerEntity);

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

    customerEntity.addAccountTransaction(accountTransaction1);
    accountEntity.addAccountTransaction(accountTransaction1);
    customerEntity.addAccountTransaction(accountTransaction2);
    accountEntity.addAccountTransaction(accountTransaction2);
    customerEntity.addAccountTransaction(accountTransaction3);
    accountEntity.addAccountTransaction(accountTransaction3);
    customerEntity.addAccountTransaction(accountTransaction4);
    accountEntity.addAccountTransaction(accountTransaction4);
    customerEntity.addAccountTransaction(accountTransaction5);
    accountEntity.addAccountTransaction(accountTransaction5);
    customerEntity.addAccountTransaction(accountTransaction6);
    accountEntity.addAccountTransaction(accountTransaction6);

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

    LoanDTO loanDTO2 = LoanDTO.builder()
        .startDt(LocalDateTime.of(2020, 6, 6, 0, 0))
        .loanType("Vehicle")
        .totalLoan(40000)
        .amountPaid(10000)
        .outstandingAmount(30000)
        .createDt(LocalDateTime.of(2020, 6, 6, 0, 0))
        .build();

    LoanDTO loanDTO3 = LoanDTO.builder()
        .startDt(LocalDateTime.of(2018, 2, 14, 0, 0))
        .loanType("Home")
        .totalLoan(50000)
        .amountPaid(10000)
        .outstandingAmount(40000)
        .createDt(LocalDateTime.of(2018, 2, 14, 0, 0))
        .build();

    LoanDTO loanDTO4 = LoanDTO.builder()
        .startDt(LocalDateTime.of(2018, 2, 14, 0, 0))
        .loanType("Personal")
        .totalLoan(10000)
        .amountPaid(3500)
        .outstandingAmount(6500)
        .createDt(LocalDateTime.of(2018, 2, 14, 0, 0))
        .build();

    LoanEntity loanEntity1 = LoanDomain.of(loanDTO1).toCreateEntity();
    LoanEntity loanEntity2 = LoanDomain.of(loanDTO2).toCreateEntity();
    LoanEntity loanEntity3 = LoanDomain.of(loanDTO3).toCreateEntity();
    LoanEntity loanEntity4 = LoanDomain.of(loanDTO4).toCreateEntity();

    loanEntity1.setCustomer(customerEntity);
    loanEntity2.setCustomer(customerEntity);
    loanEntity3.setCustomer(customerEntity);
    loanEntity4.setCustomer(customerEntity);

    loanRepository.save(loanEntity1);
    loanRepository.save(loanEntity2);
    loanRepository.save(loanEntity3);
    loanRepository.save(loanEntity4);

    CardDTO cardDTO1 = CardDTO.builder()
        .cardNumber("4565XXXX4656")
        .cardType("Credit")
        .totalLimit(10000)
        .amountUsed(500)
        .availableAmount(9500)
        .createDt(LocalDateTime.now())
        .build();

    CardDTO cardDTO2 = CardDTO.builder()
        .cardNumber("3455XXXX8673")
        .cardType("Credit")
        .totalLimit(7500)
        .amountUsed(600)
        .availableAmount(6900)
        .createDt(LocalDateTime.now())
        .build();

    CardDTO cardDTO3 = CardDTO.builder()
        .cardNumber("2359XXXX9346")
        .cardType("Credit")
        .totalLimit(20000)
        .amountUsed(4000)
        .availableAmount(16000)
        .createDt(LocalDateTime.now())
        .build();

    CardEntity cardEntity1 = CardDomain.of(cardDTO1).toCreateEntity();
    CardEntity cardEntity2 = CardDomain.of(cardDTO2).toCreateEntity();
    CardEntity cardEntity3 = CardDomain.of(cardDTO3).toCreateEntity();

    customerEntity.addCard(cardEntity1);
    customerEntity.addCard(cardEntity2);
    customerEntity.addCard(cardEntity3);

    cardRepository.save(cardEntity1);
    cardRepository.save(cardEntity2);
    cardRepository.save(cardEntity3);

    NoticeDetailDTO noticeDetailDTO1 = NoticeDetailDTO.builder()
        .noticeSummary("Home Loan Interest rates reduced")
        .noticeDetails(
            "Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately")
        .noticeBegDt(LocalDateTime.now().minusDays(30))
        .noticeEndDt(LocalDateTime.now().minusDays(30))
        .createDt(LocalDateTime.now())
        .updateDt(null)
        .build();

    NoticeDetailDTO noticeDetailDTO2 = NoticeDetailDTO.builder()
        .noticeSummary("Net Banking Offers")
        .noticeDetails(
            "Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher")
        .noticeBegDt(LocalDateTime.now().minusDays(30))
        .noticeEndDt(LocalDateTime.now().minusDays(30))
        .createDt(LocalDateTime.now())
        .updateDt(null)
        .build();

    NoticeDetailDTO noticeDetailDTO3 = NoticeDetailDTO.builder()
        .noticeSummary("Mobile App Downtime")
        .noticeDetails(
            "The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities")
        .noticeBegDt(LocalDateTime.now().minusDays(30))
        .noticeEndDt(LocalDateTime.now().minusDays(30))
        .createDt(LocalDateTime.now())
        .updateDt(null)
        .build();

    NoticeDetailDTO noticeDetailDTO4 = NoticeDetailDTO.builder()
        .noticeSummary("E Auction notice")
        .noticeDetails(
            "There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction")
        .noticeBegDt(LocalDateTime.now().minusDays(30))
        .noticeEndDt(LocalDateTime.now().minusDays(30))
        .createDt(LocalDateTime.now())
        .updateDt(null)
        .build();

    NoticeDetailDTO noticeDetailDTO5 = NoticeDetailDTO.builder()
        .noticeSummary("Launch of Millennia Cards")
        .noticeDetails(
            "Millennia Credit Cards are launched for the premium customers of EazyBank. With these cards, you will get 5% cashback for each purchase")
        .noticeBegDt(LocalDateTime.now().minusDays(30))
        .noticeEndDt(LocalDateTime.now().minusDays(30))
        .createDt(LocalDateTime.now())
        .updateDt(null)
        .build();

    NoticeDetailDTO noticeDetailDTO6 = NoticeDetailDTO.builder()
        .noticeSummary("COVID-19 Insurance")
        .noticeDetails(
            "EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details")
        .noticeBegDt(LocalDateTime.now().minusDays(30))
        .noticeEndDt(LocalDateTime.now().minusDays(30))
        .createDt(LocalDateTime.now())
        .updateDt(null)
        .build();

    noticeDetailRepository.save(NoticeDetailDomain.of(noticeDetailDTO1).toCreateEntity());
    noticeDetailRepository.save(NoticeDetailDomain.of(noticeDetailDTO2).toCreateEntity());
    noticeDetailRepository.save(NoticeDetailDomain.of(noticeDetailDTO3).toCreateEntity());
    noticeDetailRepository.save(NoticeDetailDomain.of(noticeDetailDTO4).toCreateEntity());
    noticeDetailRepository.save(NoticeDetailDomain.of(noticeDetailDTO5).toCreateEntity());
    noticeDetailRepository.save(NoticeDetailDomain.of(noticeDetailDTO6).toCreateEntity());
  }
}
