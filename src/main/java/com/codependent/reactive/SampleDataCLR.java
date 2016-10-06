package com.codependent.reactive;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SampleDataCLR implements CommandLineRunner{
	
	private final PersonRepository personRepository;
	
	public SampleDataCLR(PersonRepository personRepository){
		this.personRepository = personRepository;
	}
	
	@Override
	public void run(String... strings) throws Exception{
		Stream.of("Jose", "Ana", "Víctor", "Juan")
			.forEach( name -> personRepository.save(new Person(name, new Random().nextInt(100))));
		personRepository.findAll().forEach(System.out::println);
	}
	
}