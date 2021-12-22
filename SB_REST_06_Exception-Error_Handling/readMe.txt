1) To create Bank Account  
MODE: POST
URL: http://localhost:port/SB_REST_06_Exception-Error_Handling/Accounts/newAccount
JSON Body: 
{
    "name": "Tushar K",
    "balance":0.0,
    "openingDt": "17-11-2021",
    "isActive": "Y"
}

2) TO get Account Details
MODE: GET
URL: http://localhost:7894/SB_REST_06_Exception-Error_Handling/Accounts/find/342

3) To Transfer Balance
MODE: PATCH
URL: http://localhost:7894/SB_REST_06_Exception-Error_Handling/Accounts/transferBalance/342/341/10

4) TO close account 
MODE: PUT
URL : http://localhost:7894/SB_REST_06_Exception-Error_Handling/Accounts/close/341