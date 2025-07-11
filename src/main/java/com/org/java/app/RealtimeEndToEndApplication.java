package com.org.java.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.org.java.app.designpatteran.EnumSingletonTest;
import com.org.java.app.designpatteran.Singleton;

@SpringBootApplication
@EnableAsync
public class RealtimeEndToEndApplication {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, CloneNotSupportedException {
		SpringApplication.run(RealtimeEndToEndApplication.class, args);
		
		Singleton orginalsingleton=Singleton.getInstance();
		System.out.println("Orginalsingleton is::"+orginalsingleton.hashCode());
		
		
		Singleton Orginalsingleton2=Singleton.getInstance();
		System.out.println("Orginalsingleton2 is::"+Orginalsingleton2.hashCode());
	System.out.println("hello");
		
		//Break the singleton  class 3 ways
		// refection
		//serilization
		//cloneing
		
		//serilization example of break the singleton code
		ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("D://Selization.ser"));
		oos.writeObject(orginalsingleton);
		oos.close();
		ObjectInputStream ois=new ObjectInputStream(new FileInputStream("D://Selization.ser"));
		Singleton brokeSigleton=(Singleton)ois.readObject();
		

		Singleton Orginalsingleton3=Singleton.getInstance();
		System.out.println("Orginalsingleton3 is::"+Orginalsingleton3.hashCode());
		System.out.println("brokeningleton is::"+brokeSigleton.hashCode());
		
		
		//clone example of break the singleton code
		
		//clone example of break the singleton code
		Singleton brokenSingletonClone=orginalsingleton.clone();
		System.out.println("brokeningleton is::"+brokenSingletonClone.hashCode());
		
		//Enum type singleton
		
	Runnable task=	()->{EnumSingletonTest enumSingleton=EnumSingletonTest.INSTANCE;
		enumSingleton.perform();
	};
		Thread t1=new Thread(task,"t1");
		Thread t2=new Thread(task,"t2");
		Thread t3=new Thread(task,"t3");
		Thread t4=new Thread(task,"t4");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		
	}

}
