package business;

import java.util.List;

import dataaccess.User;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;

	public List<String> allMemberIds();

	public List<String> allBookIds();

	public List<Book> allBooks();

	public List<LibraryMember> allMembers();

	public void saveBook(Book book);

	public void deleteBook(Book book);

	public Book getBookByIsbn(String isbn);

	public void deleteMember(LibraryMember member);

	public void checkout(String isbn, String memberId) throws LibrarySystemException;

	public void addBookCopy(String isbn) throws LibrarySystemException;

	public LibraryMember getMemberById(String id);

	public void saveNewMember(LibraryMember member);

	public List<User> allUsers();

	public void saveUser(User user);
	
	public List<CheckoutRecordEntry> overdueBooks(String isbn) throws LibrarySystemException;;

}
