/*
 * Name: Jade Stewart
 * Course: CSC320
 * Assignment: Module 8 Portfolio Project, Option #2
 * Date: November 6, 2026
 *
 * Description: Manages a national builder's inventory of homes using an
 * ArrayList of Home objects. The main method opens an interactive menu
 * that lets the user add, remove, update any attribute of, list, and save
 * homes to a file.
 */

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class HomeInventory {
  private ArrayList<Home> homes = new ArrayList<Home>();

  // Add a new home
  public String addNewHome(int squareFeet, String address, String city, String state, int zipCode, String modelName,
      String saleStatus) {
    try {
      String status = saleStatus.trim().toLowerCase();
      if (!Home.isValidStatus(status)) {
        return "Error: status must be sold, available, or under contract.";
      }
      Home newHome = new Home(squareFeet, address, city, state, zipCode, modelName, status);
      homes.add(newHome);
      return "Home at " + address + ", " + city + ", " + state + " added successfully!";
    } catch (Exception e) {
      return "Error: could not add home.";
    }
  }

  // Remove a home
  public String removeHome(String address, int zipCode) {
    try {
      for (Home home : homes) {
        if (home.getAddress().equals(address) && home.getZipCode() == zipCode) {
          homes.remove(home);
          return "Home at " + address + " removed successfully.";
        }
      }
      return "Home not found. No matching address and zip code.";
    } catch (Exception e) {
      return "Error: could not remove home.";
    }
  }

  // Update a home's sale status
  public String updateHome(String address, int zipCode, String newSaleStatus) {
    try {
      for (Home home : homes) {
        if (home.getAddress().equals(address) && home.getZipCode() == zipCode) {
          return home.setSaleStatus(newSaleStatus);
        }
      }
      return "Home not found. No matching address and zip code.";
    } catch (Exception e) {
      return "Error: could not update home.";
    }
  }

  // Update any attribute of a home, returns a success or failure message
  // attributeChoice: 1 = square feet, 2 = address, 3 = city, 4 = state,
  // 5 = zip code, 6 = model name, 7 = sale status
  public String updateHomeAttribute(String address, int zipCode, String attributeChoice, String newValue) {
    try {
      for (Home home : homes) {
        if (home.getAddress().equals(address) && home.getZipCode() == zipCode) {
          switch (attributeChoice) {
            case "1":
              return home.setSquareFeet(Integer.parseInt(newValue.trim()));
            case "2":
              return home.setAddress(newValue);
            case "3":
              return home.setCity(newValue);
            case "4":
              return home.setState(newValue);
            case "5":
              return home.setZipCode(Integer.parseInt(newValue.trim()));
            case "6":
              return home.setModelName(newValue);
            case "7":
              return home.setSaleStatus(newValue);
            default:
              return "Error: invalid attribute choice. Please choose 1 through 7.";
          }
        }
      }
      return "Home not found. No matching address and zip code.";
    } catch (NumberFormatException e) {
      return "Error: square feet and zip code must be whole numbers.";
    } catch (Exception e) {
      return "Error: could not update home.";
    }
  }

  // List all homes
  public String listHomes() {
    try {
      if (homes.isEmpty()) {
        return "No homes in inventory.";
      }
      for (Home home : homes) {
        String[] details = home.listHome();
        for (String line : details) {
          System.out.println(line);
        }
        System.out.println("--------------------");
      }
      return "Listed " + homes.size() + " home(s).";
    } catch (Exception e) {
      return "Error: could not list homes.";
    }
  }

  // Save homes to a file
  public String saveToFile() {
    String filePath = "/tmp/Home.txt";
    try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
      for (Home home : homes) {
        String[] details = home.listHome();
        for (String line : details) {
          writer.println(line);
        }
        writer.println("--------------------");
      }
      return "Inventory saved to " + filePath + ".";
    } catch (IOException e) {
      return "Error: could not write to file.";
    }
  }

  // Ask whether to print the inventory to a file
  public void promptSaveToFile(Scanner scnr) {
    try {
      System.out.println("Would you like to print the information to a file? (Y or N)");
      String saveChoice = scnr.nextLine().trim();
      if (saveChoice.equalsIgnoreCase("Y")) {
        System.out.println(saveToFile());
      } else {
        System.out.println("A file will not be printed.");
      }
    } catch (Exception e) {
      System.out.println("Error: could not read your response.");
    }
  }

  // Interactive menu
  public void menu(Scanner scnr) {
    String userInput = "";
    int squareFeet = 0;
    String address = "";
    String city = "";
    String state = "";
    int zipCode = 0;
    String modelName = "";
    String saleStatus = "";

    while (!"6".equals(userInput)) {
      System.out.println();
      System.out.println("1) Add home");
      System.out.println("2) Remove home");
      System.out.println("3) Update home");
      System.out.println("4) List homes");
      System.out.println("5) Save to file");
      System.out.println("6) Quit");
      System.out.println("Enter an option: ");

      userInput = scnr.nextLine().trim();
      try {
        if ("1".equals(userInput)) {
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
          saleStatus = scnr.nextLine().trim().toLowerCase();

          System.out.println(addNewHome(squareFeet, address, city, state, zipCode, modelName, saleStatus));
        } else if ("2".equals(userInput)) {
          System.out.println("Enter address of home to remove: ");
          address = scnr.nextLine().trim();

          System.out.println("Enter zip code: ");
          zipCode = Integer.parseInt(scnr.nextLine().trim());

          System.out.println(removeHome(address, zipCode));
        } else if ("3".equals(userInput)) {
          System.out.println("Enter address of home to update: ");
          address = scnr.nextLine().trim();

          System.out.println("Enter zip code: ");
          zipCode = Integer.parseInt(scnr.nextLine().trim());

          System.out.println("Which attribute would you like to update?");
          System.out.println("1) Square feet");
          System.out.println("2) Address");
          System.out.println("3) City");
          System.out.println("4) State");
          System.out.println("5) Zip code");
          System.out.println("6) Model name");
          System.out.println("7) Sale status (sold, available, under contract)");
          String attributeChoice = scnr.nextLine().trim();

          System.out.println("Enter the new value: ");
          String newValue = scnr.nextLine().trim();

          System.out.println(updateHomeAttribute(address, zipCode, attributeChoice, newValue));
        } else if ("4".equals(userInput)) {
          System.out.println(listHomes());
        } else if ("5".equals(userInput)) {
          promptSaveToFile(scnr);
        } else if ("6".equals(userInput)) {
          System.out.println("Exiting Home Inventory. Goodbye!");
        } else {
          System.out.println("Invalid option. Please choose 1 through 6.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter whole numbers for square feet and zip code.");
      }
    }
  }

  // Program starting
  public static void main(String[] args) {
    try {
      HomeInventory inventory = new HomeInventory();
      Scanner scnr = new Scanner(System.in);
      inventory.menu(scnr);
      scnr.close();
    } catch (Exception e) {
      System.out.println("An unexpected error occurred.");
    }
  }
}


