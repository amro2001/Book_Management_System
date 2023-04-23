package com.example.book_management_system;

import java.sql.SQLException;
//might want to throw is change in here somehow?
public class DELETEOp extends ADEController {
	private final Book selectedBook;
	
	public DELETEOp(Book selectedBook) {
		this.selectedBook = selectedBook;
	}
	public boolean deletebook() {
		//need to add verification and boolean in SQLDAO
		
		boolean success = false;
		try {
			success = this.DatabaseAccess.deleteBook(selectedBook.getIsbn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return success;		
	}
}
