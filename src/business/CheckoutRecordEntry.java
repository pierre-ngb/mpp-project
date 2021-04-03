package business;

import java.io.Serializable;
import java.time.LocalDate;

public class CheckoutRecordEntry implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6624809119914418866L;
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private BookCopy book;
	private CheckoutRecord record;
	
	
	
	CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy book,
			CheckoutRecord record) {
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.book = book;
		this.record = record;
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
	
	
	@Override
	public String toString() {
		return "CheckoutRecordEntry [checkoutDate=" + checkoutDate + ", dueDate=" + dueDate + ", book=" + 
				book.toString()+ "]";
	}


	public CheckoutRecord getRecord() {
		return record;
	}
	
	
	
}
