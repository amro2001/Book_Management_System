package com.example.book_management_system;


public class VerifyByISBN {

	public boolean isValid(String isbn) {
	    if (isbn == null) {
	        return false;
	    }

	    // Remove any hyphens or spaces and trim whitespace
	    isbn = isbn.replaceAll("-", "").replaceAll(" ", "").trim();

	    // Check if the ISBN is either 10 or 13 digits
	    if (isbn.length() != 10 && isbn.length() != 13) {
	        return false;
	    }

	    // Validate ISBN-10
	    if (isbn.length() == 10) {
	        int sum = 0;
	        for (int i = 0; i < 9; i++) {
	            char c = isbn.charAt(i);
	            int digit = Character.digit(c, 10);
	            if (digit < 0) {
	                return false;
	            }
	            sum += (10 - i) * digit;
	        }
	        char lastChar = isbn.charAt(9);
	        if (lastChar != 'X' && !Character.isDigit(lastChar)) {
	            return false;
	        }
	        sum += (lastChar == 'X') ? 10 : Character.digit(lastChar, 10);
	        return (sum % 11 == 0);
	    }

	    // Validate ISBN-13
	    if (isbn.length() == 13) {
	        int sum = 0;
	        for (int i = 0; i < 12; i++) {
	            char c = isbn.charAt(i);
	            int digit = Character.digit(c, 10);
	            if (digit < 0) {
	                return false;
	            }
	            int factor = (i % 2 == 0) ? 1 : 3;
	            sum += factor * digit;
	        }
	        int checkDigit = Character.digit(isbn.charAt(12), 10);
	        int remainder = sum % 10;
	        int result = (10 - remainder) % 10;
	        return (checkDigit == result);
	    }

	    return false;
	}


}
