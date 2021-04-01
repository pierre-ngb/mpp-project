package business;

import java.time.LocalDate;
import java.util.HashMap;

public class CheckoutRecord {

	private LibraryMember member;
	private HashMap<LocalDate, CheckoutRecordEntry> records;
	
	public CheckoutRecord(LibraryMember member) {
		this.member = member;
	}
	
	public void checkout(CheckoutRecordEntry rec) {
		records.put(rec.getCheckoutDate(), rec);
	}

	public LibraryMember getMember() {
		return member;
	}

	public HashMap<LocalDate, CheckoutRecordEntry> getRecords() {
		return records;
	}
	
	
	
	
}
