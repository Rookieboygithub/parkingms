package com.parkingms.util;

import java.util.HashSet;
import java.util.Set;

public class ShiroCharacter {
	
	public static Set<String> character(int character){
		
		Set<String> roles = new HashSet<>();
		roles.add("user");
		 if (character == 0) {
			roles.add("rftogether");
			roles.add("super");
			roles.add("manager");
			roles.add("rentout");
			roles.add("forrent");
			roles.add("company");
		} else if (character == 1) {
			roles.add("rftogether");
			roles.add("manager");
			roles.add("rentout");
			roles.add("forrent");
			roles.add("company");
		} else if (character == 2) {
			roles.add("rftogether");
			roles.add("rentout");
		}

		else if (character == 3) {
			roles.add("forrent");
			roles.add("rftogether");
		} else if (character == 4) {
			roles.add("company");
		}
		
		return roles;
		
		
	}
	
	
}
