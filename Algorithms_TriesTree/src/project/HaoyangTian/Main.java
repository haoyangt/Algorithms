package project.HaoyangTian;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

import com.csvreader.CsvReader;

public class Main {

	public static void main(String[] args) {
		Data data;
		Tree tree=new Tree();
		Scanner sc=new Scanner(System.in);
		
		System.out.println("==============@author Haoyang Tian G47995931==============");
		System.out.println("Welcome to Addressbook. ");
		System.out.println("-----Please choose a document:-----\n--1. SmallAddressBook.csv\n--2. MediumAddressBook.csv\n--3. LargeAddressBook.csv\n--4. HugeAddressBook.csv\n--------------------------------");
		String document="";
		while(!document.equals("1") && !document.equals("2") && !document.equals("3") && !document.equals("4")){
			document=sc.next();
			if(!document.equals("1") && !document.equals("2") && !document.equals("3") && !document.equals("4")){
				System.err.println("PLEASE ENTER 1, 2, 3 OR 4.");
			}
		}
		
		long before=System.currentTimeMillis();
		try {
			InputStream is=null;
			switch (document.charAt(0)) {
			case '1':
				System.out.println("Initializing \"SmallAddressBook.csv\" ...Please wait.........");
				is=Tree.class.getResourceAsStream("/SmallAddressBook.csv");
				break;
			case '2':
				System.out.println("Initializing \"MediumAddressBook.csv\" ...Please wait.........");
				is=Tree.class.getResourceAsStream("/MediumAddressBook.csv");
				break;
			case '3':
				System.out.println("Initializing \"LargeAddressBook.csv\" ...Please wait.........");
				is=Tree.class.getResourceAsStream("/LargeAddressBook.csv");
				break;
			case '4':
				System.out.println("Initializing \"HugeAddressBook.csv\" ...Please wait.........");
				is=Tree.class.getResourceAsStream("/HugeAddressBook.csv");
				break;
			}
			BufferedInputStream bis=new BufferedInputStream(is);
			CsvReader reader = new CsvReader(bis,Charset.defaultCharset());
			reader.readHeaders();
			while (reader.readRecord())
			{
				data=new Data();
				data.setFirstName(reader.get("FirstName"));
				data.setLastName(reader.get("LastName"));
				data.setPhoneNumber(reader.get("PhoneNumber"));
				data.setCompanyName(reader.get("CompanyName"));
				tree.data.add(data);
				tree.insert(data.getFirstName(), tree.rootF);
				tree.insert(data.getLastName(), tree.rootL);
				tree.insert(data.getPhoneNumber(), tree.rootP);
				tree.insert(data.getCompanyName(), tree.rootC);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		long after=System.currentTimeMillis();
		System.out.println("Initialized successfully. Consumed "+(after-before)+"ms.");
		boolean toContinue=true;
		boolean searched=false;
		String inputKeyword;
		String inputDisplayType;
		while(toContinue){
			while(!searched){
				System.out.println("What would you like to search on?(F, L, P, C)");
				String category=sc.next();
				if(category.length()==1){
					switch (category.charAt(0)) {
					case 'F':
					case 'f':System.out.println("Enter partial FIRST NAME: ");
								 inputKeyword=sc.next();
								 System.out.println("Do you what to display all data(Press Y), or just one data with more accurate time calculation which is only consumed by algorithm(Press any other letters)?");
								 inputDisplayType=sc.next();
								 before=System.currentTimeMillis();
								 tree.search(inputKeyword, tree.rootF, inputDisplayType);
								 after=System.currentTimeMillis();
								 tree.printTimeMsg(after-before);
								 searched=true;
								 break;
					case 'L':
					case 'l':System.out.println("Enter partial LAST NAME: ");
								 inputKeyword=sc.next();
								 System.out.println("Do you what to display all data(Press Y), or just one data with more accurate time calculation which is only consumed by algorithm(Press any other letters)?");
								 inputDisplayType=sc.next();
								 before=System.currentTimeMillis();
								 tree.search(inputKeyword, tree.rootL, inputDisplayType);
								 after=System.currentTimeMillis();
								 tree.printTimeMsg(after-before);
								 searched=true;
								 break;
					case 'P':
					case 'p':System.out.println("Enter partial PHONE NUMBER: ");
								 inputKeyword=sc.next();
								 System.out.println("Do you what to display all data(Press Y), or just one data with more accurate time calculation which is only consumed by algorithm(Press any other letters)?");
								 inputDisplayType=sc.next();
								 before=System.currentTimeMillis();
								 tree.search(inputKeyword, tree.rootP, inputDisplayType);
								 after=System.currentTimeMillis();
								 tree.printTimeMsg(after-before);
								 searched=true;
								 break;
					case 'C':
					case 'c':System.out.println("Enter partial COMPANY NAME: ");
					  			 inputKeyword=sc.next();
					  			 System.out.println("Do you what to display all data(Press Y), or just one data with more accurate time calculation which is only consumed by algorithm(Press any other letters)?");
					  			 inputDisplayType=sc.next();
					  			 before=System.currentTimeMillis();
					  			 tree.search(inputKeyword, tree.rootC, inputDisplayType);
					  			 after=System.currentTimeMillis();
					  			 tree.printTimeMsg(after-before);
								 searched=true;
								 break;
					default:System.out.println("Please enter the SPECIFIED letter... ");
								searched=false;
					}
				}else {
					System.out.println("Please enter the SPECIFIED letter... ");
					searched=false;
				}
			}
			searched=false;
			System.out.println("Another Search? (Y/N)");
			while(true){
				String choice=sc.next();
				if(choice.equalsIgnoreCase("Y")){
					toContinue=true;
					break;
				}else if (choice.equalsIgnoreCase("N")){
					toContinue=false;
					break;
				}else
					System.out.println("Please enter Y/N...");
			}
		}
		System.out.println("Bye~");
		sc.close();
	}

}
