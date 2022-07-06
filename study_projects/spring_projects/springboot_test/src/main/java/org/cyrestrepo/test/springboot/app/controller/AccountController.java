package org.cyrestrepo.test.springboot.app.controller;

import org.cyrestrepo.test.springboot.app.model.Account;
import org.cyrestrepo.test.springboot.app.model.TransferDTO;
import org.cyrestrepo.test.springboot.app.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping("/all")
    public List<Account> findAllAccount(){
        return accountService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account save(@RequestBody Account account){
        return accountService.save(account);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account getAccountById(@PathVariable(name = "id") Long id){
        return accountService.findById(id);
    }

    /**
     * el siguiente metodo recibe los datos de la transferencia a partir de un DTO, lo cual hace que la transaccion sea mas
     * comoda de estructurar, ya que simplemente le mandamos al request el cuerpo con los datos de la transaferencia, en vez de
     * enviar cada uno por separado por medio de @PathVariable o @RequestParam
     * @param dto
     * @return
     */
    @PostMapping("/transfer")
    public ResponseEntity<Map<String, Object>> tranfer(@RequestBody TransferDTO dto){
        accountService.transfer(dto.getAccountIdOrigin(), dto.getAccountIdDestiny(), dto.getAmount(), dto.getBankId());

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("date", LocalDate.now());
        response.put("status", HttpStatus.OK.value());
        response.put("message", "transfer succeed");
        response.put("transaction", dto);

        return ResponseEntity.ok(response);
    }

}
