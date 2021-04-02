package business;

import java.time.LocalDate;
import java.util.List;

import business.Book;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
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

	public void checkout(Book book, LibraryMember member) throws LibrarySystemException;
	public LibraryMember getMemberById(String id);
	
	public void saveNewMember(LibraryMember member) ;
	public List<User> allUsers();
	
	public void saveUser(User user) ;
	
	

	
}
