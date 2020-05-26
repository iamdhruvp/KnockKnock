package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.BankAccount;
import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Repositories.ProfessionalRepository;
import com.KnockKnock.Services.BankAccountService;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.ProfessionalService;
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

    @Autowired
    public ProfessionalService professionalService;

    @PostMapping(value="/postbank/{id}/{rid}")
    public String postBank(@PathVariable("id") Long id ,@PathVariable("rid") Long rid, @RequestBody BankAccount b)
    {
        try {
            if(rid==1) {
                Customer cus = customerService.findById(id);

                System.out.println(cus.getCustomerName());

                cus.setBankAccount(b);
                customerService.save(cus);
                System.out.println("saved..................");
            }

                if(rid==2)
                {
                    Professional professional=professionalService.findById(id);

                    professional.setBankAccount(b);
                    professionalService.save(professional);
                    System.out.println("saved..................");
                }
                }
        catch (Exception e)
        {
            return "{\"status\":false}";
        }


        return "{\"status\":true}";


    }

    @GetMapping(value="/getbank/{id}/{rid}")
    public ResponseEntity<BankAccount>  getBank(@PathVariable("id") Long id, @PathVariable("rid") Long rid)
    {
BankAccount b=new BankAccount();
        try{

            if(rid==1) {
                System.out.println("here i your addresss....");
                Customer cus = customerService.findById(id);
                 b = cus.getBankAccount();

            }

            else
                if(rid==2) {
                    System.out.println("here i your addresss....");
                    Professional professional = professionalService.findById(id);

                    assert professional != null;
                    b = professional.getBankAccount();

                }
                    return new ResponseEntity<BankAccount>(b, HttpStatus.OK);

    }
        catch (Exception e)
        {
            return new ResponseEntity<BankAccount>(HttpStatus.BAD_REQUEST);
        }
    }
}

