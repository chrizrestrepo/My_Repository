package org.cyrestrepo.test.springboot.app;

import org.cyrestrepo.test.springboot.app.exception.InsufficientBalanceException;
import org.cyrestrepo.test.springboot.app.model.Account;
import org.cyrestrepo.test.springboot.app.model.Bank;
import org.cyrestrepo.test.springboot.app.repository.AccountRepository;
import org.cyrestrepo.test.springboot.app.repository.BankRepository;
import org.cyrestrepo.test.springboot.app.service.IAccountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class SpringbootTestApplicationTests {

	/**
	 * en spring los Beans se mockean con la anotacion @MockBean y se injectan con la anotacion @Autowired, al igual
	 * que en se injecta cualquier dependencia en Spring, recordar que la clase en donde se injecta debe estar anotada
	 * con cualquier estereotipo de Spring... ademas de que spring nos permite definir en el autowired el tipo generico,
	 * osea la interfaz, sin necesidad de llamar la instancia real del service
	 */
	@MockBean
	AccountRepository accountRepository;

	@MockBean
	BankRepository bankRepository;

	@Autowired
	IAccountService service;

	/**
	 * en el ejemplo vemos que en el metodo anotado con @BeforeEach se estan seteando los valores de los montos
	 * nuevamente en la clase data de las constantes, esto es con el de que si se va a reutilizar el codigo, es necesario que
	 * el valor siempre sea el mismo, ya que al momento de ejecutar cada metodo Test, corremos el riesgo de que estos datos
	 * cambien e impacten los otros test
	 */
	@BeforeEach
	void setUp() {
		//accountRepository = mock(AccountRepository.class);
		//bankRepository = mock(BankRepository.class);
		//service = new AccountServiceImpl(accountRepository, bankRepository);
		Data.ACCOUNT_001.get().setBalance(new BigDecimal("1000"));
		Data.ACCOUNT_002.get().setBalance(new BigDecimal("2000"));
		Data.BANK.get().setTotalTransfer(0);
	}

	/**
	 * es importante recordar que cada que se ejecuta el metodo en el When, el estado de los mocks
	 * deberia cambiar por lo que se hace necesario para el ejemplo volver a asignar los valores despues de
	 * revisar el saldo, ya que para cuando se ejecuta el metodo del service sus valores cambian
	 *
	 * NOTA: el objeto BigDecimal es mutable
	 */
	@Test
	void contextLoads() {
		when(accountRepository.findById(1L)).thenReturn(Data.ACCOUNT_001);
		when(accountRepository.findById(2L)).thenReturn(Data.ACCOUNT_002);
		when(bankRepository.findById(1L)).thenReturn(Data.BANK);

		BigDecimal balanceAccount1 = service.getBalance(1L);
		BigDecimal balanceAccount2 = service.getBalance(2L);

		assertEquals("1000", String.valueOf(balanceAccount1.intValue()));
		assertEquals("2000", String.valueOf(balanceAccount2.intValue()));

		service.transfer(1L, 2L, new BigDecimal("800"), 1L);

		balanceAccount1 = service.getBalance(1L);
		balanceAccount2 = service.getBalance(2L);

		assertEquals("200", String.valueOf(balanceAccount1.intValue()));
		assertEquals("2800", String.valueOf(balanceAccount2.intValue()));

		int totalTransfer = service.getTotalTransfer(1L);
		assertEquals(1, totalTransfer);

		/**
		 * En el método verify se está usando el método Mockito.times() que sirve para indicar el número
		 * de llamados que se le hicieron a un method, en este caso son 3. Ya que en el test, se hace un llamado
		 * en el método getBalance() para traer el saldo inicial, luego este método se ejecuta en el método transfer()
		 * del servicio y por último en el método getBalance() nuevamente, en donde se trae el valor final después de la
		 * transferencia.
		 *
		 * Adicional se ejecuta el método update() 2 veces, ya que se esté se ejecuta una vez por cada cuenta en el service
		 *
		 * por otro lado el método findById() del repositorio de banco se ejecuta dos veces, ya que se hace una consulta del
		 * total de transferencias y en el llamdo al servicio en el metodo transfer()
		 */
		verify(accountRepository, times(3)).findById(1L);
		verify(accountRepository, times(3)).findById(2L);
		verify(accountRepository, times(2)).save(any(Account.class));

		verify(bankRepository, times(2)).findById(1L);
		verify(bankRepository).save(any(Bank.class));

		verify(accountRepository, times(6)).findById(anyLong());
	}

	/**
	 * el metodo mockito.never() es el metodo que se usa para indicarle al verify que un metodo nunca se llama
	 */
	@Test
	void contextLoads2() {
		when(accountRepository.findById(1L)).thenReturn(Data.createAccountMock001());
		when(accountRepository.findById(2L)).thenReturn(Data.createAccountMock002());
		when(bankRepository.findById(1L)).thenReturn(Data.createBankMock());

		BigDecimal balanceAccount1 = service.getBalance(1L);
		BigDecimal balanceAccount2 = service.getBalance(2L);

		assertEquals("1000", String.valueOf(balanceAccount1.intValue()));
		assertEquals("2000", String.valueOf(balanceAccount2.intValue()));

		Exception exception = assertThrows(InsufficientBalanceException.class, () ->
			service.transfer(1L, 2L, new BigDecimal("1200"), 1L));

		assertEquals("the amount beats the allowed value: -200", exception.getMessage());

		balanceAccount1 = service.getBalance(1L);
		balanceAccount2 = service.getBalance(2L);

		assertEquals("1000", String.valueOf(balanceAccount1.intValue()));
		assertEquals("2000", String.valueOf(balanceAccount2.intValue()));

		int totalTransfer = service.getTotalTransfer(1L);
		assertEquals(0, totalTransfer);


		verify(accountRepository, times(3)).findById(1L);
		verify(accountRepository, times(3)).findById(2L);
		verify(accountRepository, never()).save(any(Account.class));
	}

	/**
	 * el metodo assertSame() sirve para verificar si dos objetos son iguales
	 */
	@Test
	void contextLoads3() {
		when(accountRepository.findById(1L)).thenReturn(Data.createAccountMock001());

		Account account1 = service.findById(1L);
		Account account2 = service.findById(1L);

		assertSame(account1, account2);
		verify(accountRepository, times(2)).findById(1L);
		assertTrue(account1.getPersonName().equalsIgnoreCase("Cristian") &&
				account2.getPersonName().equalsIgnoreCase("Cristian"));
	}

}
