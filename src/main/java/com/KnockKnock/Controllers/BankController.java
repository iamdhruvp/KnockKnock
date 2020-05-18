package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.BankAccount;
import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Services.BankAccountService;
import com.KnockKnock.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class BankController {

    @Autowired
    public BankAccountService bankAccountService;

    @Autowired
   public CustomerService customerService;

    @PostMapping(value="/postbank/{id}")
    public String postBank(@PathVariable("id") Long id , @RequestBody BankAccount b)
    {
        try {
            Customer cus = customerService.findById(id);

            System.out.println(cus.getCustomerName());

                    cus.setBankAccount(b);
                    customerService.save(cus);
                    System.out.println("saved..................");
                }
        catch (Exception e)
        {
            return "{\"status\":false}";
        }


        return "{\"status\":true}";


    }

    @GetMapping(value="/getbank/{id}")
    public ResponseEntity<BankAccount>  getBank(@PathVariable("id") Long id)
    {

        try{
        System.out.println("here i your addresss....");
        Customer cus=customerService.findById(id);
        BankAccount b=cus.getBankAccount();


        return new ResponseEntity<BankAccount>(b, HttpStatus.OK);
    }
        catch (Exception e)
        {
            return new ResponseEntity<BankAccount>(HttpStatus.BAD_REQUEST);
        }
    }
}

