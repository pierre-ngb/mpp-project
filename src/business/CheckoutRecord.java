package business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CheckoutRecord implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3271988687657774362L;
	private LibraryMember member;
	private List<CheckoutRecordEntry> records;
	
	public CheckoutRecord(LibraryMember member) {
		this.member = member;
		records = new ArrayList<CheckoutRecordEntry>();
	}
	
	public void addEntry(CheckoutRecordEntry rec) {
		records.add(rec);
	}

	public LibraryMember getMember() {
		return member;
	}

	public List<CheckoutRecordEntry> getRecords() {
		return records;
	}
	
	
	
	
}
