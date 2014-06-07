package me.fwilson.jirc;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Utils {
	private static SecureRandom random = new SecureRandom();
	public static String randomString(int l) {
		return ("j" + (new BigInteger(255, random)).toString().substring(2, l));
	}
}
