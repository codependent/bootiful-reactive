package com.codependent.reactive;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.Response;

@Component
public class PersonHandler {

	private final PersonRepository personRepository;
	
	public PersonHandler(PersonRepository personRepository){
		this.personRepository = personRepository;
	}
	
	public Response<Flux<Person>> all(Request request){
		Flux<Person> flux = Flux.fromStream(personRepository.all());
		return Response.ok().body(BodyInserters.fromPublisher(flux, Person.class));
	}
	
	public Response<Mono<Person>> byId(Request request){
		Optional<String> optional = request.pathVariable("id");
		return optional.map( id -> personRepository.findById(id))
		        .map( person -> Mono.fromFuture(person))
		        .map( mono -> Response.ok().body(BodyInserters.fromPublisher(mono, Person.class)))
		        .orElseThrow( () -> new IllegalStateException("Oops!"));
		
	}
}
