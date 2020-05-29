package com.KnockKnock.Controllers;


import com.KnockKnock.Entities.BankAccount;
import com.KnockKnock.Entities.Customer;
import com.KnockKnock.Entities.Professional;
import com.KnockKnock.Repositories.ProfessionalRepository;
import com.KnockKnock.Services.BankAccountService;
import com.KnockKnock.Services.CustomerService;
import com.KnockKnock.Services.ProfessionalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class BankController {
    public static final Logger logger = LogManager.getLogger(BankController.class);

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

                logger.info("bank details for this customer "+cus.getCustomerName());

                cus.setBankAccount(b);
                customerService.save(cus);
                logger.info("bank details saved for the customer "+cus.getCustomerName());

            }

                if(rid==2)
                {
                    Professional professional=professionalService.findById(id);

                    professional.setBankAccount(b);
                    professionalService.save(professional);
                    logger.info("bank details saved for the professional "+professional.getProfessionalName());
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

                Customer cus = customerService.findById(id);
                logger.info("fetching bank details for the customer "+cus.getCustomerName());
                 b = cus.getBankAccount();

            }

            else
                if(rid==2) {

                    Professional professional = professionalService.findById(id);
                    logger.info("fetching bank details for the professional "+professional.getProfessionalName());
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

