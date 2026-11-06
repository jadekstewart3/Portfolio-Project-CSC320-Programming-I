import java.util.Scanner;

public class Home {
  private int squareFeet;
  private String address;
  private String city;
  private String state;
  private int zipCode;
  private String modelName;
  private String saleStatus;

  //constructor
  public Home(int squareFeet, String address, String city, String state, int zipCode, String modelName, String saleStatus){
    try{
      this.squareFeet = squareFeet;
      this.address = address;
      this.city = city;
      this.state = state;
      this.zipCode = zipCode;
      this.modelName = modelName;
      this.saleStatus = saleStatus;
    } catch (Exception e){
      System.out.print("Error: could not create home");
    }
  }
  //getters 

  public String getAddress(){
    return address;
  }

  public int getZipCode(){
    return zipCode;
  }

  //update sale status

  public String setSaleStatus(String newStatus){
    try{
      if("sold".equals(newStatus) || "available".equals(newStatus) || "undercontract".equals(newStatus)){
          saleStatus = newStatus;
          System.out.print("Sale status updated to " + newStatus + ".");
          return saleStatus;
      } else {
        System.out.print("Error: status must be sold, available, or under contract.");
        return saleStatus;
      }
    } catch(Exception e){
      System.out.print("Error: could not update sale status.");
      return saleStatus;
    }
  }
}