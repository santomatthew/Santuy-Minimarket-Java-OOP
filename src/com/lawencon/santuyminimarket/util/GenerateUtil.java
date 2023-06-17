package com.lawencon.santuyminimarket.util;

import java.util.Random;

public class GenerateUtil {

	public static String generateRandom() {
		String randomCode = "";
		final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
		final StringBuilder sb = new StringBuilder();
		final Random random = new Random();
		final int length = 5;

		for (int i = 0; i < length; i++) {
			final int index = random.nextInt(alphabet.length());
			final char randomChar = alphabet.charAt(index);
			sb.append(randomChar);
		}
		randomCode = sb.toString();
		return randomCode;
	}
	
}
