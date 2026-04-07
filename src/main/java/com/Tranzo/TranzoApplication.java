package com.Tranzo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TranzoApplication {
/*
admin password and login
"email": "gautam@gmail.com",
"password": "Gautam@123"
*/
	//403 happened when invalid json like filed don't match like newpass & newPass when filed ohk newpass and send newPass than show 403
	public static void main(String[] args) {
		SpringApplication.run(TranzoApplication.class, args);
		System.out.println("server running on port : 8080");
	}

}
