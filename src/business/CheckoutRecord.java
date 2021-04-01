package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;

public class CheckoutRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3271988687657774362L;
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
