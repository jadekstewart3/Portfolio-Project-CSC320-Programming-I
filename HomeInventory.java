import java.util.ArrayList;

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
}