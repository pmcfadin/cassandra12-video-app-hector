package com.killrvideo.test;

import static org.junit.Assert.*;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import com.killrvideo.User;

public class UserTest {

	@Test
	public void testConstructorPassword() {
		String password = "Random Text";
		User user = new User("pmcfadin", password, "Patrick", "McFadin");
		
		assertEquals(DigestUtils.md5Hex(password), user.getPassword());

	}
	
	@Test
	public void testSetPassword() {
		String password = "Random Text";
		User user = new User();
		
		user.setPassword(password);
		
		assertEquals(DigestUtils.md5Hex(password), user.getPassword());

	}


}
