package com.tushar.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Accounts_res  implements Serializable{

	@NonNull
	private String status;
	
	@NonNull
	private String msg;
	
	@NonNull
	private LocalDateTime time;

	private T_Bank_Accounts account;
	
}
