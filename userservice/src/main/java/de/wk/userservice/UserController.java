package de.wk.userservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping("/getUser")
	public UserData getUser () {
		UserData ud = new UserData ();
		ud.setFirstName("Hans");
		ud.setLastName("Meiser");
		List<String> numbers = new ArrayList<String>();
		numbers.add("0175671111");
		numbers.add("0225164444");
		ud.setNumbers(numbers);
		return ud;
	}
	
}
