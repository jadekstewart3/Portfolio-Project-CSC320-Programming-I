import java.util.ArrayList;
import java.util.InputMismatchException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class HomeInventory{
  private ArrayList<Home> homes = new ArrayList<Home>();

  //add a new home

  public String addNewHome(int squareFeet, String address, String city, String state, int zipCode, String modelName, String saleStatus){
    try{
      Home newHome = new Home(squareFeet, address, city, state, zipCode, modelName, saleStatus);
      homes.add(newHome);
      return "Home at " + address + ", " + city + ", " + state + " added successfully!"; 
    } catch(Exception e){
       return "Error: could not add home";
    }
  }
  //remove home

  public String removeHome(String address, int zipCode){
    try{
      for(Home home : homes){
        if(home.getAddress().equals(address) && home.getZipCode() == zipCode){
          homes.remove(home);
          return "Home at " + address + " removed successfully";
        }
      }
      return "Home not found. No matching address and zip code.";
    } catch(Exception e){
      return "Error: could not remove home";
    }
  }

  //update a homes sale status
  public String updateHome(String address, int zipCode, String newSaleStatus){
    try{
      for(Home home : homes){
        if(home.getAddress().equals(address) && home.getZipCode() == zipCode){
          return home.setSaleStatus(newSaleStatus);
        }
      }
      return "Home not found. No Matching address and zip code.";
    } catch(Exception e ){
      return "Error: could not update home.";
    }
  }

  //list all homes

  public String listHomes(){
    try{
      if(homes.isEmpty()){
        return "No homes in inventory";
      }
      for(Home home : homes){
        String[] details = home.listHome();
        for(String line : details){
          System.out.println(line);
        }
        System.out.println("--------------------");
      }
      return "Listed " + homes.size() + " home(s).";
    }catch(Exception e){
      return "Error: could not list homes.";
    }
  }
  
  //save homes to a file

  public String saveToFile(){
    String filePath = "/tmp/Home.txt";
    try(PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
      for(Home home : homes){
        String[] details = home.listHome();
        for(String line : details) {
          writer.println(line);
        }
         writer.println("--------------------");
      }
      return "Inventory saved to " + filePath + " .";
    } catch(IOException e){
      return "Error: could not write to file";
    }
  }
  //menu

  public void menu(){
    String userInput = "";
    int squareFeet = 0;
    String address = "";
    String city = "";
    String state = "";
    int zipCode = 0;
    String modelName = "";
    String saleStatus = "";
    String saveChoice = "";
    Scanner scnr = new Scanner(System.in);

    while(!"6".equals(userInput)){
      System.out.println("1) Add home");
      System.out.println("2) Remove home");
      System.out.println("3) Update status");
      System.out.println("4) List homes");
      System.out.println("5) Save to file");
      System.out.println("6) Quit");
      System.out.println("Enter an option: ");

      userInput = scnr.nextLine().trim();
      try{
        if("1".equals(userInput)){
          System.out.println("Enter square feet: ");
          squareFeet = Integer.parseInt(scnr.nextLine().trim());

          System.out.println("Enter address: ");
          address = scnr.nextLine().trim();

          System.out.println("Enter city: ");
          city = scnr.nextLine().trim();

          System.out.println("Enter state: ");
          state = scnr.nextLine().trim();

          System.out.println("Enter zip code: ");
          zipCode = Integer.parseInt(scnr.nextLine().trim());

          System.out.println("Enter model name: ");
          modelName = scnr.nextLine().trim();

          System.out.println("Enter sale status (sold, available, under contract): ");
          saleStatus = scnr.nextLine().trim();

          System.out.println(addNewHome(squareFeet, address, city, state, zipCode, modelName, saleStatus));
        } else if("2".equals(userInput)){
          System.out.println("Enter address of home to remove: ");
          address = scnr.nextLine().trim();

          System.out.println("Enter zip code: ");
          zipCode = Integer.parseInt(scnr.nextLine().trim());

          System.out.println(removeHome(address, zipCode));
        } else if("3".equals(userInput)){
          System.out.println("Enter address of home to update: ");
          address = scnr.nextLine().trim();

          System.out.println("Enter zip code: ");
          zipCode = Integer.parseInt(scnr.nextLine().trim());

          System.out.println("Enter new sale status (sold, available, under contract): ");
          saleStatus = scnr.nextLine().trim();

          System.out.println(updateHome(address, zipCode, saleStatus));
        } else if("4".equals(userInput)){
          System.out.println(listHomes());
        } else if("5".equals(userInput)){
          System.out.println("Would you like to print the information to a file? (Y or N)");
          saveChoice = scnr.nextLine().trim();
          if("Y".equals(saveChoice)){
            System.out.println(saveToFile());
          } else {
            System.out.println("A file will not be printed");
          }
        } else if("6".equals(userInput)){
          System.out.println("Exiting Home Inventory. Goodbye!");
        } else {
          System.out.println("Invalid option. Please choose 1 through 6.");
        }
      } catch(InputMismatchException e){
        System.out.println("Invalid input. Please enter numbers for square feet and zip code.");
      }
    }
    scnr.close();
  }
  //program starting

  public static void main(String[] args){
    try{
      HomeInventory inventory = new HomeInventory();
      inventory.menu();
    }catch(Exception e ){
      System.out.println("An unexpected error occured.");
    }
  }
}