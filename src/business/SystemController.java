package business;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dataaccess.Auth;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;

	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();

		if (!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}

		String passwordFound = map.get(id).getPassword();
		if (!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}

	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}

	@Override
	public List<Book> allBooks() {
		DataAccess da = new DataAccessFacade();
		List<Book> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().values());
		return retval;
	}

	@Override
	public List<LibraryMember> allMembers() {
		DataAccess da = new DataAccessFacade();
		List<LibraryMember> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().values());
		return retval;
	}

	@Override
	public void saveBook(Book book) {
		DataAccess da = new DataAccessFacade();
		da.saveBook(book);

	}

	@Override
	public void saveUser(User user) {
		DataAccess da = new DataAccessFacade();
		da.saveUser(user);

	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		DataAccess da = new DataAccessFacade();
		da.deleteBook(book);

	}

	@Override
	public void checkout(String isbn, String memberId) throws LibrarySystemException {
		Book book = getBookByIsbn(isbn);
		LibraryMember member = getMemberById(memberId);
		if (book == null) {
			throw new LibrarySystemException("The book is not in the system");
		}

		if (member == null) {
			throw new LibrarySystemException("This member is not the system please register");
		}
		if (!book.isAvailable()) {
			throw new LibrarySystemException("The book: " + book.getTitle() + " is not available");
		}
		BookCopy copy = book.getNextAvailableCopy();
		int maxCopy = book.getMaxCheckoutLength();
		member.checkout(copy, LocalDate.now(), LocalDate.now().plusDays(maxCopy));
		DataAccess da = new DataAccessFacade();
		da.saveNewMember(member);
		da.saveBook(book);
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		DataAccess da = new DataAccessFacade();
		Book retval = da.readBooksMap().get(isbn);
		return retval;
	}

	@Override
	public LibraryMember getMemberById(String id) {
		DataAccess da = new DataAccessFacade();
		LibraryMember retval = da.readMemberMap().get(id);
		return retval;
	}

	@Override
	public void saveNewMember(LibraryMember member) {
		DataAccess da = new DataAccessFacade();
		da.saveNewMember(member);
	}

	@Override
	public List<User> allUsers() {
		DataAccess da = new DataAccessFacade();
		List<User> retval = new ArrayList<>();
		retval.addAll(da.readUserMap().values());
		return retval;
	}

	@Override
	public void deleteMember(LibraryMember member) {

	}

	@Override
	public void addBookCopy(String isbn) throws LibrarySystemException {
		Book book = getBookByIsbn(isbn);
		if (book == null) {
			throw new LibrarySystemException("The book is not in the system");
		}
		book.addCopy();
		DataAccess da = new DataAccessFacade();
		da.saveBook(book);
	}

}
