package com.github.rabidfish.webflow.ex2;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;

import com.github.rabidfish.HasId;

public class ContactComplex implements Serializable, HasId {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String firstName;
	
	@NotNull
	@Size(min = 1, max = 100)
	private String lastName;
	
	private PhoneComplex phone;
	
	public ContactComplex() {
		super();
	}
	
	/*
	 * This right here is why you should NOT have complex objects directly mapped to
	 * forms for Spring Webflow. It doesn't want to descend past the top level class
	 * for validation. You should implement validation separate from the model if
	 * you've got a complex structure.
	 * 
	 * A short bit on how spring webflow thinks about validation:
	 * http://stackoverflow.com/questions/7685235/how-to-pass-parameter-to-a-method-of-validating-the-spring-webflow
	 */
	public void validateContactComplexCreateEdit(ValidationContext context) {
		
		System.out.println("validateContactComplexCreateEdit");
		
        MessageContext messages = context.getMessageContext();
        
        if (phone == null) {
            messages.addMessage(
            		new MessageBuilder()
            			.error()
            			.source("phone")
            			.defaultText("Phone must not be null")
            			.build());
            
        }
    }
	
	@AssertTrue(message = "Missing phone number")
	public boolean isPhoneNotNull() {
		
		System.out.println("isPhoneNotNull");
		
		return phone != null;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public PhoneComplex getPhone() {
		return phone;
	}
	public void setPhone(PhoneComplex phone) {
		this.phone = phone;
	}
}
