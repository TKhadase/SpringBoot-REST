package com.tushar.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tushar.model.Accounts_res;
import com.tushar.model.T_Bank_Accounts;

@Controller
public class BankOpsController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper mapper;

	private String bankAccountOpsServiceUrl = "http://localhost:7894/SB_REST_07_SwaggerAPI_Doc-Testing/Accounts";

	@GetMapping({ "/", "/home" })
	public String showHome(Map<String, Object> map) {
		System.out.println("BankOpsController.showHome()");
		ResponseEntity<String> resData = null;
		try {
			resData = restTemplate.exchange(bankAccountOpsServiceUrl + "/findAll", HttpMethod.GET, null, String.class);

			System.out.println("BankOpsController.showHome():getBody: " + resData.getBody());
			System.out.println("BankOpsController.showHome():getStatusCode: " + resData.getStatusCode());
			System.out.println("BankOpsController.showHome():getStatusCodeValue: " + resData.getStatusCodeValue());

			try {
				Accounts_res accountsRes = mapper.readValue(resData.getBody(), Accounts_res.class);
				map.put("accList", accountsRes.getAccounts());
			} catch (Exception e) {
				map.put("resultMsg", "Failed to convert Bank data");
				e.printStackTrace();
			}

		} catch (Exception e) {
			map.put("resultMsg", "Failed to connect Bank Server");
			e.printStackTrace();
		}
		return "home";
	}// showHome

	@GetMapping("/newAccount")
	public String createNewAccount(@ModelAttribute("account") T_Bank_Accounts account) {
		System.out.println("BankOpsController.createNewAccount()");
		return "createAccount";
	}// createNewAccount

	@PostMapping("/newAccount")
	public String createNewAccountUsingFormData(@ModelAttribute("account") T_Bank_Accounts account, Map<String, Object> map,
			RedirectAttributes attrs) {
		System.out.println("BankOpsController.createNewAccountUsingFormData()");

		ResponseEntity<String> resData = null;
		Accounts_res accountsRes = null;
		String accountJsonData ="";
		try {
			account.setIsActive("Y");
			account.setOpeningDt(LocalDate.now());
			accountJsonData = mapper.writeValueAsString(account);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> httpEntity = new HttpEntity<String>(accountJsonData, headers);
			resData = restTemplate.exchange(bankAccountOpsServiceUrl + "/newAccount", HttpMethod.POST, httpEntity, String.class);

			System.out.println("BankOpsController.createNewAccountUsingFormData():getBody: " + resData.getBody());
			System.out.println("BankOpsController.createNewAccountUsingFormData():getStatusCode: " + resData.getStatusCode());
			System.out.println("BankOpsController.createNewAccountUsingFormData():getStatusCodeValue: " + resData.getStatusCodeValue());

			try {
				accountsRes = mapper.readValue(resData.getBody(), Accounts_res.class);
				attrs.addFlashAttribute("resultMsg", "" + accountsRes.getMsg());
			} catch (Exception e) {
				attrs.addFlashAttribute("resultMsg", "" + accountsRes.getMsg());
				e.printStackTrace();
			}

		} catch (Exception e) {
			attrs.addFlashAttribute("resultMsg", "" + accountsRes.getMsg());
			e.printStackTrace();
		}
		return "redirect:home";
	}//createNewAccountUsingFormData
	
	@GetMapping("/edit")
	public String showAccountDetails(@RequestParam("accid") int accId,  RedirectAttributes attrs, @ModelAttribute ("account") T_Bank_Accounts account) {
		System.out.println("BankOpsController.showAccountDetails()");
		ResponseEntity<String> resData = null;
		Accounts_res accountsRes = null;
		try {
			resData = restTemplate.exchange(bankAccountOpsServiceUrl + "/find/{accId}", HttpMethod.GET, null, String.class, accId);

			System.out.println("BankOpsController.showAccountDetails():getBody: " + resData.getBody());
			System.out.println("BankOpsController.showAccountDetails():getStatusCode: " + resData.getStatusCode());
			System.out.println("BankOpsController.showAccountDetails():getStatusCodeValue: " + resData.getStatusCodeValue());
			
			try {
				 accountsRes = mapper.readValue(resData.getBody(), Accounts_res.class);
				 T_Bank_Accounts userAccount = accountsRes.getAccounts().get(0);
				 System.out.println("BankOpsController.showAccountDetails() account:"+userAccount);
				 BeanUtils.copyProperties(userAccount, account);
			} catch (Exception e) {
				attrs.addFlashAttribute("resultMsg", "Failed to convert Bank data");
				e.printStackTrace();
				return "redirect:home";
			}
		
		} catch (Exception e) {
		attrs.addFlashAttribute("resultMsg", "Failed to connect Bank Server" );
		e.printStackTrace();
		return "redirect:home";
	}
		return "MyAccount";
	}//showAccountDetails
	
	@PostMapping("/edit")
	public String updateAccountDetails(RedirectAttributes attrs, @ModelAttribute ("account") T_Bank_Accounts account) {
		System.out.println("BankOpsController.updateAccountDetails()");
		ResponseEntity<String> resData = null;
		Accounts_res accountsRes = null;
		String accountJsonData ="";
		try {
			account.setOpeningDt(LocalDate.now());
			accountJsonData = mapper.writeValueAsString(account);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> httpEntity = new HttpEntity<String>(accountJsonData, headers);
			resData = restTemplate.exchange(bankAccountOpsServiceUrl + "/update", HttpMethod.PUT, httpEntity, String.class);

			System.out.println("BankOpsController.updateAccountDetails():getBody: " + resData.getBody());
			System.out.println("BankOpsController.updateAccountDetails():getStatusCode: " + resData.getStatusCode());
			System.out.println("BankOpsController.updateAccountDetails():getStatusCodeValue: " + resData.getStatusCodeValue());
			
			try {
				 accountsRes = mapper.readValue(resData.getBody(), Accounts_res.class);
				 
				 attrs.addFlashAttribute("resultMsg", ""+accountsRes.getMsg());
			} catch (Exception e) {
				attrs.addFlashAttribute("resultMsg", "Failed to convert Bank data");
				e.printStackTrace();
				return "redirect:home";
			}
		
		} catch (Exception e) {
		attrs.addFlashAttribute("resultMsg", "Failed to connect Bank Server" );
		e.printStackTrace();
		}
		return "redirect:home";
	}//updateAccountDetails

}// BankOpsController
