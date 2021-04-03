package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

final public class LibraryMember extends Person implements Serializable {
	private String memberId;
	private CheckoutRecord record;
	
	public LibraryMember(String memberId, String fname, String lname, String tel,Address add) {
		super(fname,lname, tel, add);
		this.memberId = memberId;		
		record = new CheckoutRecord(this);
	}
	
	
	public String getMemberId() {
		return memberId;
	}

	public void checkout(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
		copy.changeAvailability();
		CheckoutRecordEntry entry = new CheckoutRecordEntry(checkoutDate, dueDate, copy, record );
		record.addEntry(entry);
	}
	
	public CheckoutRecord getRecord() {
		return record;
	}
	
	@Override
	public String toString() {
		return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() + 
				", " + getTelephone() + " " + getAddress();
	}

	private static final long serialVersionUID = -2226197306790714013L;
}
