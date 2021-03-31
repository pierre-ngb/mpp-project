package pipeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import quizclasses.Employee;
import quizclasses.EmployeeTestData;
import quizclasses.TraderTransactTestData;
import quizclasses.Transaction;
import quizclasses.Pair;
import quizclasses.Trader;

/** USE STREAM PIPELINES TO SOLVE THE PROBLEMS HERE */
public class Main {
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		System.out.println("Sample Problem - solved");
		List<Employee> sampleData = EmployeeTestData.getList();
		//SAMPLE: Create a stream pipeline that does the following:
		//Print all Employee records for which name has length > 5 and birth year is > 1970
		// Expected output: [<name: Timothy salary: 50000 year of birth: 1996>, <name: Ricardo salary: 50000 year of birth: 1988>]
		List<Employee> result = sampleData.stream()
							        .filter(e -> e.getName().length() > 5)
							        .filter(e -> e.getYearOfBirth() > 1970)
							        .collect(Collectors.toList());
		System.out.println(result);
				
		prob1();
		prob2();
		prob3();
	}
	
	//Create a stream pipeline that obtains a list of
	//all name/salary pairs extracted from the list of Employees
	//for which salary is at least 60000 but less than 130000, in ascending
	//order of name, then descending order of salary.
	//Then, print the list to the console.
	// Expected output: 60k - 130k earners, sorted: [(Andy, 60000.0), (Joe, 100000.0), (Joe, 90000.0)]
	public static void prob1() {
		//use this list 
		List<Employee> list = EmployeeTestData.getList();
		List<Pair> ls = list.stream()
		    .filter(e -> e.getSalary() >= 60000)
		    .filter(e -> e.getSalary() < 130000)
		    .sorted(Comparator.comparing((Employee e) -> e.getName())
		    .thenComparing((Employee p) -> p.getSalary()).reversed())
		    .map(e -> {
		    	quizclasses.Pair p = new Pair(e.getName(), e.getSalary());
		    	return p;
		    })
		    .collect(Collectors.toList());
		    
		System.out.println(ls);
		
	}
	
	//Create a stream pipeline to find all transactions from year 2011 
	//and sort them by value (small to high), and print to console
	//(Note: there is an instance variable "value" in Transaction)
	// Expected output: [{Trader:Brian in Cambridge, year: 2011, value:300}, {Trader:Raoul in Cambridge, year: 2011, value:400}]
	public static void prob2() {
		//use this list	
		List<Transaction> list = TraderTransactTestData.getTransactions();
		List<Transaction> ls = list.stream()
								.filter(e -> e.getYear() == 2011)
								.sorted(Comparator.comparing((Transaction t) -> t.getValue() ))
								.collect(Collectors.toList());
		System.out.println(ls);
	}
	
	// Create a stream pipeline to find all traders from Cambridge, 
	// sort them by name, and print to console
	// Expected output: [Trader:Alan in Cambridge, Trader:Brian in Cambridge, Trader:Raoul in Cambridge]
	public static void prob3() {
		//Use this list
		List<Transaction> list = TraderTransactTestData.getTransactions();  
		List<Trader> ls = list.stream()
							.map(tr -> tr.getTrader())
							.filter(t -> t.getCity().equals("Cambridge"))
							.sorted(Comparator.comparing((Trader a) -> a.getName()))
							.collect(Collectors.toList());
		System.out.println(ls);
	                
	}
}
