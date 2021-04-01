package business;

import java.time.LocalDate;

public class CheckoutRecordEntry {

	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy book;
	
	
	public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy book) {
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.book = book;
	}


	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}


	public BookCopy getBook() {
		return book;
	}
	
	
	
	
}
