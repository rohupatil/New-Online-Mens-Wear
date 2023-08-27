package com.shopx.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopx.dto.CustomerResponseDTO;
import com.shopx.dto.EditCustomerDTO;
import com.shopx.dto.EditPassDTO;
import com.shopx.dto.EditPasswordDTO;
import com.shopx.dto.ForgetPassOtpDTO;
import com.shopx.dto.LoginDTO;
import com.shopx.dto.OTPVerificationDTO;
import com.shopx.dto.SignUpDTO;
import com.shopx.entities.User;
//import com.shopx.service.CustomerService;
import com.shopx.service.CustomerService;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/register")
	public ResponseEntity<?> newCustomerRegistration(@RequestBody @Valid SignUpDTO newCustomer)
	{
		User customer = customerService.newCustomerRegistration(newCustomer);
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}
	
	@GetMapping
	public ResponseEntity<?> getAllCustomerDetails()
	{
		return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllCustomerDetails());
	}
	
	@GetMapping("/{custId}")
	public CustomerResponseDTO getCustomerById(@PathVariable Long custId)
	{
		System.out.println("customer id from front end "+custId);
		User user = customerService.getCustomerById(custId);
		System.out.println(user);
		return mapper.map(user, CustomerResponseDTO.class);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> loginValidationForm(@RequestBody @Valid LoginDTO login)
	{
		return ResponseEntity.status(HttpStatus.OK).body(customerService.loginValidationForm(login));
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> editCustomerProfil(@RequestBody EditCustomerDTO customer)
	{
		return ResponseEntity.status(HttpStatus.OK).body(customerService.editCustomerProfil(customer));
	}
	
	@PutMapping("/password")
	public ResponseEntity<?> changePassword(@RequestBody EditPasswordDTO password)
	{
		return ResponseEntity.status(HttpStatus.OK).body(customerService.editCustomerPassword(password));
	}

	
	@PostMapping("/address/{userId}")
	public User addAddress(@PathVariable Long userId, @RequestBody String address)
	{
		return customerService.addAddress(userId, address);
	}
	
	 @PostMapping("/getotpforforgotpass")
	 public ResponseEntity<String> getOtpForForgotPass(@RequestBody ForgetPassOtpDTO emailId) {
	      String email=emailId.getEmail();
	       customerService.getOtpForForgotPass(email);
	        return ResponseEntity.ok("OTP sent for verification.");
	    }
	
	
	    @PostMapping("/verifyotpforforgot")
	    public ResponseEntity<?> verifyOTPForForgotPass(@RequestBody OTPVerificationDTO otpVerificationDTO) {
	    	
	        boolean isVerified = customerService.verifyOTP(otpVerificationDTO);
 	    	
	    	try {

		        if (isVerified) {
			         
		            return ResponseEntity.ok("OTP Verification is Successful");
		        } else {
		            return ResponseEntity.badRequest().body("OTP verification failed.");
		        }
				
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error occured during OTP verififcation");
				// TODO: handle exception
			}
//	        isVerified1=isVerified;
	    
	      
	    }
	    
	       boolean isVerified1;

	 
	    @PostMapping("/storenewpass")
	    public ResponseEntity<?> storeNewPass(@RequestBody EditPassDTO editpassword) {
	    	 boolean isPasswordChanged = customerService.forgotchangePassword(editpassword);

	         if (isPasswordChanged) {
	             return ResponseEntity.ok("Password changed successfully");
	         } else {
	             return ResponseEntity.badRequest().body("Password change failed");
	         }
	     }
	

}
