package business;

import java.util.*;
import java.util.stream.Collectors;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class Main {

	public static void main(String[] args) {
		System.out.println(allWhoseZipContains3());
		System.out.println(allHavingAtLeastTwoCopies());
		System.out.println(allHavingMultipleAuthors());

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
