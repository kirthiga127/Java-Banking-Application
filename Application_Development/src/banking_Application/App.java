package banking_Application;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class App {
	static Scanner sc;
	static Customer[] customers = new Customer[100000];
	static Transactions[] transactions = new Transactions[100000];
	static BankDB[] db=new BankDB[100000];
	static Customer customer;
    static int global_id=0;
	static int id = 4;
	static int t_id = 0;
	static long amount = 10000;
	static long cust_id;
	static long account_num;
	static String encrypted_pwd = "";
	static ArrayList<Long> customer_ids = new ArrayList<>();
	static ArrayList<Long> acc_nos = new ArrayList<>();

	public static void main(String[] args) {
		customers[0] = new Customer("Kumar", "Dlkd787", 11011, 11, 10000);
		customers[1] = new Customer("Madhu", "Alkd787", 22022, 22, 20000);
		customers[2] = new Customer("Rahul", "Los787", 33033, 33, 30000);
		customers[3] = new Customer("Robin", "Dsf787", 44044, 44, 40000);
		sc = new Scanner(System.in);
		while (true) {
			System.out.println("Online Banking Application");
			System.out.println("1. SignUp");
			System.out.println("2. LogIn");
			System.out.println("3. Exit");
			System.out.println("Please enter your choice");
			int user_input = sc.nextInt();
			switch (user_input) {
			case 1:
				String status = addCustomer();
				if (status.equals("added successfully")) {
					System.out.println("Signed-Up successfully!!!");
				} else {
					System.out.println("The passwords you entered didn't match, please try again.");
				}
				break;
			case 2:
				String status1 = login();
				if (status1.equals("Login successfully")) {
				label : while (true) {
						System.out.println("Please select any one of the options below");
						System.out.println("1.Withdraw");
						System.out.println("2.Deposit");
						System.out.println("3.Transfer");
						System.out.println("4.Transaction history");
						System.out.println("5.Exit");
						
						int option = sc.nextInt();
						switch (option) {

						case 1:
							withdraw();
							break;
						case 2:
							deposit();
							break;
						case 3:
							transfer();
							break;
						case 4:
							transaction_history();
							break;
						case 5:
							break label;
						case 6:
							displayDB();
							break;
						default:
							System.out.println("Invalid input, please provide a valid input");
						}
					}
				} else {
					System.out.println(
							"The credentials which you entered was incorrect,Please enter correct login credentials");
				}
				break;
			case 3:
				return;
			default:
				System.out.println("Invalid input, please provide a valid input");
			}
		}

	}

	private static void displayDB() {
		for(int i=0;i<global_id;i++) {
			System.out.println(db[i].toString());
		}
		
	}

	private static void transaction_history() {
		System.out.println("Account Statement");
		System.out.println("Name - " + customer.getName());
		System.out.println("Account number - " + customer.getAccount_number());
		System.out.println("Customer Id - " + customer.getCustomer_id());
		System.out.println("TransID   TransType  Amount  Balance");
		int j=1;
		for (int i = 0; i < global_id; i++) {
			if(db[i].getCustomer_id()==customer.getCustomer_id()) {
			System.out.println(j + "  " + db[i].getTransaction_type() + "  " + db[i].getAmount()
					+ "  " + db[i].getBalance());
			j++;
			}
			
		}
		j=1;

	}

	private static String login() {
		System.out.println("Enter your Customer Id");
		long cust_id = sc.nextLong();
		System.out.println("Enter your password");
		String password = sc.next();
		String encrypted_pwd = getEncryptedPwd(password);
		for (int i = 0; i < id; i++) {
			if (customers[i].getCustomer_id() == cust_id) {
				if (customers[i].getEncrypted_password().equals(encrypted_pwd)) {
					customer = customers[i];
					return "Login successfully";
				}
			}
		}
		return "incorrect credentials";
	}

	private static void transfer() {
		Customer transfer_to=null;
		int flag = 0;
		System.out.println("Enter the account number which you want to transfer");
		long acc_num = sc.nextLong();
		System.out.println("Enter the amount you want to transfer");
		long amnt=sc.nextLong();
		long total=customer.getBalance()-amnt;
		for (int i = 0; i < id; i++) {
			if (acc_num == customers[i].getAccount_number()) {
				transfer_to = customers[i];
				flag = 1;
				break;
			}
		}
		if (flag == 1) {
               transfer_to.setBalance(transfer_to.getBalance()+amnt);
               transactions[t_id] = new Transactions("TransferTo:"+acc_num,amnt , total);
               db[global_id]=new BankDB(customer,transactions[t_id]);
               global_id++;
       		   t_id++;
       		   transactions[t_id]=new Transactions("TransactionFrom:"+customer.getAccount_number(),amnt,transfer_to.getBalance());
       		   db[global_id]=new BankDB(transfer_to,transactions[t_id]);
       		   global_id++;
    		   t_id++;
       		   customer.setBalance(total);
		}else {
			System.out.println("Incorrect details, please enter valid details");
		}

	}

	private static void deposit() {
		System.out.println("Please enter the amount to deposit");
		long deposit_amnt = sc.nextLong();
		long balance = customer.getBalance();
		long total_amount = balance + deposit_amnt;
		customer.setBalance(total_amount);
		transactions[t_id] = new Transactions("Cash Deposit", deposit_amnt, total_amount);
		 db[global_id]=new BankDB(customer,transactions[t_id]);
         global_id++;
		t_id++;

	}

	private static void withdraw() {

		System.out.println("Please enter the amount to withdraw");
		long withdraw_amnt = sc.nextLong();
		long balance = customer.getBalance();
		long total_amount = balance - withdraw_amnt;
		if (total_amount < 1000) {
			System.out.println("Minimum balance is 1000, you will be get charged for not maintaining low balance");
			System.out.println("If you want to continue this transaction, please reply with yes or no");
			String user_reply = sc.next();
			switch (user_reply.toLowerCase()) {
			case "yes":
				customer.setBalance(total_amount);
				transactions[t_id] = new Transactions("ATM Withdrawal", withdraw_amnt, total_amount);
				 db[global_id]=new BankDB(customer,transactions[t_id]);
	               global_id++;
				t_id++;
				break;
			case "no":
				return;
			default:
				System.out.println("Invalid input, please enter yes or no");
			}
		} else {
			customer.setBalance(total_amount);
			transactions[t_id] = new Transactions("ATM Withdrawal", withdraw_amnt, total_amount);
			 db[global_id]=new BankDB(customer,transactions[t_id]);
             global_id++;
			t_id++;
		}

	}

	private static String addCustomer() {
		System.out.println("Enter your name");
		String name = sc.next();
		System.out.println("Enter your password");
		String password = sc.next();
		System.out.println("Please Re-type your password");
		String confirm_password = sc.next();
		if (password.equals(confirm_password)) {
			customers[id] = new Customer(name, getEncryptedPwd(password), acc_num_generator(), customer_id_generator(),
					amount);
			id++;
			transactions[t_id] = new Transactions("Opening", amount, amount);
			db[global_id]=new BankDB(customers[id-1],transactions[t_id]);
            global_id++;
			t_id++;
			System.out.println("Your customer id is " + customers[id - 1].getCustomer_id());
			System.out.println("Your account number is " + customers[id - 1].getAccount_number());
			return "added successfully";
		} else {
			return "user is not added";
		}

	}

	private static long customer_id_generator() {

		Random random = new Random();
		cust_id = random.nextInt(100000);

		for (Long l : customer_ids) {
			if (cust_id == l) {
				customer_id_generator();
				break;
			} else {
				customer_ids.add(cust_id);
				break;
			}
		}

		return cust_id;

	}

	private static long acc_num_generator() {

		long min = 1000000;
		long max = 9999999;
		account_num = (long) Math.floor(Math.random() * (max - min + 1) + min);

		for (Long l : acc_nos) {
			if (account_num == l) {
				acc_num_generator();
				break;
			} else {
				acc_nos.add(account_num);
				break;
			}
		}
		return account_num;
	}

	public static String getEncryptedPwd(String user_pwd) {
		encrypted_pwd = "";
		for (int i = 0; i < user_pwd.length(); i++) {
			int a = (int) user_pwd.charAt(i);
			a++;
			if (a == 91 || a == 123 || a == 58) {
				if (a == 58)
					a = 48;
				else if (a == 123)
					a = 97;
				else
					a = 65;
			}
			char b = (char) a;
			encrypted_pwd = encrypted_pwd + b;
		}
		return encrypted_pwd;
	}

}
