package business;

import java.util.*;
import java.util.stream.Collectors;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import utils.Utils;

public class Main {

	public static void main(String[] args) {
		
		
		ControllerInterface ci = new SystemController();
		
//		try {
//			ci.checkout("48-56882", "1003");
//			System.out.println("Sucess");
//		} catch (LibrarySystemException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		try {
			ci.overdueBooks("48-56882").forEach(t -> {
				System.out.println(t.getBook().getBook().getIsbn()+ " " +t.getBook().getBook().getTitle()+
			" "+t.getBook().getCopyNum()+" "+t.getRecord().getMember().getMemberId()
						+" "+Utils.format(t.getDueDate())
				);
			});
		} catch (LibrarySystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
//		System.out.println(allWhoseZipContains3());
//		System.out.println(allHavingAtLeastTwoCopies());
//		System.out.println(allHavingMultipleAuthors());
//		ControllerInterface ci = new SystemController();
//		try {
//			Book book = ci.getBookByIsbn("99-22223");
//			LibraryMember member = ci.getMemberById("1002");
//			
//			ci.checkout(book, member);
//			ci.allMembers().stream()
//			.filter(x -> x.getRecord().getRecords().size()>0)
//			.collect(Collectors.toList())
//			.forEach(m -> {
//	    		System.out.println(m);
//	    			System.out.println("Checkout records");
//	    			m.getRecord().getRecords().forEach(System.out::println);
//	    		
//	    	});
//		} catch (LibrarySystemException e) {
//			System.out.println(e.getMessage());
//		}
		
		
		
		
//		ci.allBooks().forEach( x -> {
//			System.out.println(x.getTitle().toString() + ", "+x);
//			for(BookCopy c: x.getCopies()) {
//				System.out.println(c.getCopyNum());
//				c.changeAvailability();
//			}
//			
//			System.out.println(x.getTitle().toString() + ", "+x);
//		});

	}
	//Returns a list of all ids of LibraryMembers whose zipcode contains the digit 3
	public static List<String> allWhoseZipContains3() {
		DataAccess da = new DataAccessFacade();
		Collection<LibraryMember> members = da.readMemberMap().values();
		List<LibraryMember> mems = new ArrayList<>();
		mems.addAll(members);
		//implement
		return mems.stream()
				.filter(member -> member.getAddress().getZip().indexOf("3") >= 0)
				.map(m -> m.getMemberId())
				.collect(Collectors.toList());
		
	}
	
	//Returns a list of all isbns of books having at least two copies
		public static List<String> allHavingAtLeastTwoCopies() {
			DataAccess da = new DataAccessFacade();
			Collection<Book> books = da.readBooksMap().values();
			List<Book> bs = new ArrayList<>();
			bs.addAll(books);
			// implement
			return bs.stream()
					.filter(b -> b.getCopies().length > 1)
					.map(book -> book.getIsbn())
					.collect(Collectors.toList());
		}
		
	//Returns a list of all ids of  LibraryMembers that have an overdue book
//	public static List<String> allHavingOverdueBook() {
//		DataAccess da = new DataAccessFacade();
//		Collection<LibraryMember> members = da.readMemberMap().values();
//		List<LibraryMember> mems = new ArrayList<>();
//		mems.addAll(members);
//		//implement
//		return null;
//		
//	}
	
	//Returns a list of all isbns of  Books that have multiple authors
	public static List<String> allHavingMultipleAuthors() {
		DataAccess da = new DataAccessFacade();
		Collection<Book> books = da.readBooksMap().values();
		List<Book> bs = new ArrayList<>();
		bs.addAll(books);
		//implement
		return bs.stream()
				 .filter(book -> book.getAuthors().size() > 1)
				 .map(bk -> bk.getIsbn())
				 .collect(Collectors.toList());
		
		}

}
