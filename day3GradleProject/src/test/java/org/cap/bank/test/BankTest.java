package org.cap.bank.test;

import static org.junit.Assert.*;

import org.cap.dao.AccountDao;
import org.cap.dto.Account;
import org.cap.dto.Address;
import org.cap.dto.Customer;
import org.cap.exception.InsufficientBalanceException;
import org.cap.service.AcccountService;
import org.cap.service.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BankTest {
	private AcccountService accService;
	
	@Mock
	private AccountDao accountDao;
	
	@Before
  public void beforeMethod(){
		MockitoAnnotations.initMocks(this);
		accService=new AccountServiceImpl(accountDao);
	}
   
	@Test
	public void test_Withdrawal_Method()throws InsufficientBalanceException {
		Account account=new Account();
		account.setAccountNo(1234);
		account.setAmount(2000);
		Customer customer=new Customer();
		customer.setCustName("ram");
		customer.setCustAddress(new Address());
		account.setCustomer(customer);
		
		//Declaration
		Mockito.when(accountDao.findAccountById(1234)).thenReturn(account);
		
		//business logic
		Account account2=accService.withdraw(1234, 200);
		
		//Verify
		Mockito.verify(accountDao).findAccountById(1234);
		
		assertEquals(1800, account2.getAmount(),0.0);
	}

}
