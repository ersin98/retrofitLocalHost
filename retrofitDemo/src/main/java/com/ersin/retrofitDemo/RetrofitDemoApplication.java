package com.ersin.retrofitDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RetrofitDemoApplication {
//lombok kurulacak,doğru spring eklemeleri kullnaılacak,pgadmin kurulacak,application.properties yazılacak
	// etitie kısmındaki import lar persistence üzerinden olacak
	// veritabanını otomatik oluşturmaz kendin aç sonra otomatik içi dolsun
	public static void main(String[] args) {
		SpringApplication.run(RetrofitDemoApplication.class, args);
	}

}
