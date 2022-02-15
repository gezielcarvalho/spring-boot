package info.gezielcarvalho.demo.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	
	private final UUID id;
	
	@NotBlank @NotNull
	private final String name;
	
	public Person(@JsonProperty("id") UUID id, 
				  @JsonProperty("name") String name) {
		this.id = id;
		this.name = name;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}