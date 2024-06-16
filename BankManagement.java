import java.util.*;
import java.util.regex.*;

class User
{
  private String ssnNo;
  private String name;
  private String email;
  private String address;
  private String phoneNo;
  private String addharNo;
  private String panNo;
  private String accountNo;
  private int depositInitialAmount;

  public User (String ssnNo, String name, String email, String address,
			   String phoneNo, String addharNo, String panNo,
			   String accountNo, int depositInitialAmount)
  {
	this.ssnNo = ssnNo;
	this.name = name;
	this.email = email;
	this.address = address;
	this.phoneNo = phoneNo;
	this.addharNo = addharNo;
	this.panNo = panNo;
	this.accountNo = accountNo;
	this.depositInitialAmount = depositInitialAmount;
  }

  // Getters
  public String getSsnNo ()
  {
	return ssnNo;
  }

  public String getName ()
  {
	return name;
  }

  public String getEmail ()
  {
	return email;
  }

  public String getAddress ()
  {
	return address;
  }

  public String getPhoneNo ()
  {
	return phoneNo;
  }

  public String getAddharNo ()
  {
	return addharNo;
  }

  public String getPanNo ()
  {
	return panNo;
  }

  public String getAccountNo ()
  {
	return accountNo;
  }

  public int getDepositInitialAmount ()
  {
	return depositInitialAmount;
  }

  // Setters
  public void setSsnNo (String ssnNo)
  {
	this.ssnNo = ssnNo;
  }

  public void setName (String name)
  {
	this.name = name;
  }

  public void setEmail (String email)
  {
	this.email = email;
  }

  public void setAddress (String address)
  {
	this.address = address;
  }

  public void setPhoneNo (String phoneNo)
  {
	this.phoneNo = phoneNo;
  }

  public void setAddharNo (String addharNo)
  {
	this.addharNo = addharNo;
  }

  public void setPanNo (String panNo)
  {
	this.panNo = panNo;
  }

  public void setAccountNo (String accountNo)
  {
	this.accountNo = accountNo;
  }

  public void setDepositInitialAmount (int depositInitialAmount)
  {
	this.depositInitialAmount = depositInitialAmount;
  }
  
  public void deposit(int amount){
      this.depositInitialAmount += amount;
  }
  
  public boolean withdraw(int amount){
      int rem = this.depositInitialAmount - amount;
      if(amount > this.depositInitialAmount || rem < 500){
          return false;
      }
      this.depositInitialAmount -= amount;
      return true;
  }
}

public class BankManagement
{
  private static List<User> users = new ArrayList<>();
    
  public static void main (String args[])
  {
	chooseOperation ();
  }

  public static void chooseOperation ()
  {
	Scanner sc = new Scanner (System.in);
	
   char typeOfOperation;

   do{
	System.out.println("\n***********DSA BANK***********");
	System.out.println ("a. Create account");
	System.out.println ("b. Deposit amount");
	System.out.println ("c. Withdraw money");
	System.out.println ("d. Balance checking");
	System.out.println ("e. Exit");
	
	System.out.println ("Select your choice:");
	
	typeOfOperation = sc.next().charAt(0);
	
	switch(typeOfOperation)
	  {
	  case 'a':
		createAccount ();
		break;
	  case 'b':
		depositAmount ();
		break;
	  case 'c':
		withdrawMoney ();
		break;
	  case 'd':
		balanceChecking ();
		break;
	  case 'e':
	      System.out.println("Exiting the bank. Goodbye!");
		break;
	  default:
		System.out.println ("Invalid choice. Please try again.");
// 		chooseOperation ();
	  }
   } while (typeOfOperation != 'e');
	sc.close (); // Close the Scanner
  }

  public static void createAccount ()
  {
	Scanner sc = new Scanner (System.in);

	System.out.println ("Enter your SSN No it should be a 7 digit number:");
	String ssnNo = sc.nextLine ();
	String validSsnNo = validateSsnNo(ssnNo); // check method

	System.out.println ("\nEnter your name it should be min 50 characters:");
	String name = sc.nextLine ();
	String validName = validateName(name); // check method

	System.out.println ("\nEnter your email:");
	String email = sc.nextLine ();
	String validEmail = validateEmail(email); // check method

	System.out.println ("\nEnter your address:");
	String address = sc.nextLine (); 
	String validAddress = validateAddress(address); // check method

	System.out.println ("\nEnter your Phone number:");
	String number = sc.nextLine (); 
	String validNumber = validateNumber(number); // check method	

	System.out.println ("\nEnter your Aadhar number:");
	String aadharNumber = sc.nextLine ();
	String validAadharNumber = validateAadharNumber(aadharNumber); // check method

	System.out.println ("\nEnter your Pan number:");
	String panNumber = sc.nextLine ();
	String validPanNumber = validatePanNumber(panNumber); // check method

	System.out.println ("\nEnter your Account number:");
	String accNumber = sc.nextLine ();
	String validAccNumber = validateAccNumber(accNumber); // check method

	System.out.println ("\nDeposit amount minimum ₹500 or more:");
	int depositInitialAmount = sc.nextInt ();
	int validAmount = validateDepositInitialAmount(depositInitialAmount); // check method

	User user = new User(validSsnNo, validName, validEmail, validAddress, validNumber, validAadharNumber, validPanNumber, validAccNumber, validAmount);
	users.add(user); //add to arraylist
	
	System.out.println("\nAccount created successfully!");

	System.out.println ("\nSSN No: " + user.getSsnNo ());
	System.out.println ("Name: " + user.getName ());
	System.out.println ("Email: " + user.getEmail ());
	System.out.println ("Address: " + user.getAddress ());
	System.out.println ("Phone No: " + user.getPhoneNo ());
	System.out.println ("Aadhar No: " + user.getAddharNo ());
	System.out.println ("Pan No: " + user.getPanNo ());
	System.out.println ("Account No: " + user.getAccountNo ());
	System.out.println ("Deposit Initial Amount: " +user.getDepositInitialAmount ());
	
	chooseOperation();

	sc.close (); // Close the Scanner
  }

  public static void depositAmount()
  {
    Scanner sc = new Scanner(System.in);
    
    System.out.println("\n--> Enter your account number:");
    String takeAccountNumber = sc.nextLine();
    
    User user = findUserByAccountNumber(takeAccountNumber);
    
    if(user == null){
        System.out.println("\nAccount not found.");
        chooseOperation();
    }
    
    System.out.println("\n --> Enter your amount:");
    int takeDepositAmount = sc.nextInt();
    
     
     if(takeDepositAmount <= 0){
         System.out.println("Deposit amount must be positive.");
         chooseOperation();
     }
     
     user.deposit(takeDepositAmount);
     System.out.println("Deposite successfully! New balance: "+user.getDepositInitialAmount());
     
     chooseOperation();
     
     sc.close();
  }

  public static void withdrawMoney ()
  {
    Scanner sc = new Scanner(System.in);
     
    System.out.println("Enter your Account number:");
    String takeAccountNumber = sc.nextLine();
    User user = findUserByAccountNumber(takeAccountNumber);
    
    if(user == null){
        System.out.println("Account not found.");
        chooseOperation();
    }
    
    System.out.println("Enter amount to withdraw:");
    int amount = sc.nextInt();
    
    
    if(amount <= 0){
        System.out.println("withdrawal amount must be positive.");
        chooseOperation();
    }
    
    boolean success = user.withdraw(amount);
    
    if(success){
       System.out.println("withdrawal successful! new balance: "+user.getDepositInitialAmount());
    }else{
       System.out.println("Insufficient funds or atleast 500 should remain in the wallet before the withdrawal.");
    }
    
    chooseOperation();
    
    sc.close();
  }

  public static void balanceChecking ()
  {
	Scanner sc = new Scanner(System.in);
     
    System.out.println("Enter your Account number:");
    String takeAccountNumber = sc.nextLine();
    User user = findUserByAccountNumber(takeAccountNumber);
    
    if(user == null){
        System.out.println("Account not found.");
        chooseOperation();
    }
    
    System.out.println("Current balance: "+ user.getDepositInitialAmount());
    
    chooseOperation();
    
    sc.close();
  }
  
  public static User findUserByAccountNumber(String takeAccountNumber){
       for(User user : users){ //for each loop
           if(user.getAccountNo().equals(takeAccountNumber)){
               return user;
           }
       }
       return null;
  }

  //validation of user input

  public static String validateSsnNo (String ssnNo)
  {
	Scanner sc = new Scanner (System.in);

	if (ssnNo.length () > 7 || ssnNo.length () < 7 || ssnNo == null)
	  {
		System.out.println
		  ("##Error: It should be max 7 digit number or value is empty");
		System.out.println ("--> Re-enter your SSN No:");
		ssnNo = sc.nextLine ();
		return validateSsnNo(ssnNo); //recursive function
	  }
	  
	  return ssnNo;
  }

  public static String validateName (String name)
  {
	Scanner sc = new Scanner (System.in);

	if (name.length () > 50 || name == null)
	  {
		System.out.println
		  ("##Error: It should be max 50 characters or value is empty");
		System.out.println ("--> Re-enter your name:");
		name = sc.nextLine ();
		return validateName(name);	//recursive function
	  }
	  
	  return name;
  }

  public static String validateEmail (String email)
  {
	Scanner sc = new Scanner (System.in);

	String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";


	Pattern pattern = Pattern.compile (regex);
	Matcher matcher = pattern.matcher (email);
	boolean matchFound = matcher.matches ();

	if (!matchFound)
	  {
		System.out.println ("##Error: Please check your email");
		System.out.println ("--> Re-enter your email:");
		email = sc.nextLine ();
		return validateEmail(email);	//recursive function
	  }
	  
	  return email;
  }

  public static String validateAddress (String address)
  {
	Scanner sc = new Scanner (System.in);

	if (address.length () > 100)
	  {
		System.out.println ("##Error: It should be max 100 characters");
		System.out.println ("--> Re-enter your address:");
		address = sc.nextLine ();
		return validateAddress (address);	//recursive function
	  }
	  
	  return address;
  }

  public static String validateNumber (String number)
  {
	Scanner sc = new Scanner (System.in);

	String regex = "(0/91)?[7-9][0-9]{9}";

	Pattern pattern = Pattern.compile (regex);
	Matcher matcher = pattern.matcher (number);

	if (!matcher.matches())
	  {
		System.out.println ("##Error: please check your number");
		System.out.println ("--> Re-enter yout number:");

		number = sc.nextLine ();
		
		return validateNumber (number);	//recursive function
	  }
	  
	  return number;
  }

  public static String validateAadharNumber (String aadharNumber)
  {
	Scanner sc = new Scanner (System.in);

	String regex = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$";

	Pattern pattern = Pattern.compile (regex);
	Matcher matcher = pattern.matcher (aadharNumber);

	if (!(matcher.matches ()))
	  {
		System.out.println ("##Error: please check your Aadhar number");
		System.out.println ("--> Re-enter your Aadhar number:");

		aadharNumber = sc.nextLine ();
		return validateAadharNumber (aadharNumber);	//recursive function
	  }
	  
	  return aadharNumber;
  }

  public static String validatePanNumber (String panNumber)
  {
	Scanner sc = new Scanner (System.in);

	String regex = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

	Pattern pattern = Pattern.compile (regex);
	Matcher matcher = pattern.matcher (panNumber);

	if (!(matcher.matches ()))
	  {
		System.out.println ("##Error: please check your Pan number");
		System.out.println ("--> Re-enter your Pan number:");

		panNumber = sc.nextLine ();
		return validatePanNumber (panNumber);	//recursive function
	  }
	  
	  return panNumber;
  }

  public static String validateAccNumber (String accNumber)
  {
	Scanner sc = new Scanner (System.in);

	if (accNumber.length () > 20 || accNumber.length () < 20)
	  {
		System.out.println
		  ("##Error: please check your Account number max 20 characters");
		System.out.println ("--> Re-enter your Account number:");

		accNumber = sc.nextLine ();
		return validateAccNumber(accNumber);	//recursive function
	  }
	  
	  return accNumber;
  }

  public static int validateDepositInitialAmount (int depositInitialAmount)
  {
	Scanner sc = new Scanner (System.in);

	if (depositInitialAmount < 500)
	  {
		System.out.println ("##Error: You should deposit minimum ₹500 or more");
		System.out.println ("--> Re-enter your amount:");

		depositInitialAmount = sc.nextInt ();
		return validateDepositInitialAmount(depositInitialAmount);
	  }
	  
	  return depositInitialAmount;

  }

}
