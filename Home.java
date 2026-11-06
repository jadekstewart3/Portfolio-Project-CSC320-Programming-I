/*
 * Name: Jade Stewart
 * Course: CSC320
 * Assignment: Module 8 Portfolio Project, Option #2
 * Date: November 6, 2026
 *
 * Description: Represents a single home in a national builder's inventory.
 * Stores the home's square footage, address, city, state, zip code, model
 * name, and sale status, and provides methods to update the sale status
 * and list the home's information.
 */

public class Home {
  private int squareFeet;
  private String address;
  private String city;
  private String state;
  private int zipCode;
  private String modelName;
  private String saleStatus;

  // Parameterized constructor
  public Home(int squareFeet, String address, String city, String state, int zipCode, String modelName,
      String saleStatus) {
    try {
      this.squareFeet = squareFeet;
      this.address = address;
      this.city = city;
      this.state = state;
      this.zipCode = zipCode;
      this.modelName = modelName;
      this.saleStatus = saleStatus;
    } catch (Exception e) {
      System.out.println("Error: could not create home.");
    }
  }

  // Getters
  public String getAddress() {
    try {
      return address;
    } catch (Exception e) {
      return "";
    }
  }

  public int getZipCode() {
    try {
      return zipCode;
    } catch (Exception e) {
      return 0;
    }
  }

  // Checks whether a sale status is one of the three allowed values
  public static boolean isValidStatus(String status) {
    try {
      return "sold".equals(status) || "available".equals(status) || "under contract".equals(status);
    } catch (Exception e) {
      return false;
    }
  }

  // Update square feet, returns a success or failure message
  public String setSquareFeet(int newSquareFeet) {
    try {
      if (newSquareFeet <= 0) {
        return "Error: square feet must be greater than 0.";
      }
      squareFeet = newSquareFeet;
      return "Square feet updated to " + newSquareFeet + ".";
    } catch (Exception e) {
      return "Error: could not update square feet.";
    }
  }

  // Update address, returns a success or failure message
  public String setAddress(String newAddress) {
    try {
      if (newAddress == null || newAddress.trim().isEmpty()) {
        return "Error: address cannot be empty.";
      }
      address = newAddress.trim();
      return "Address updated to " + address + ".";
    } catch (Exception e) {
      return "Error: could not update address.";
    }
  }

  // Update city, returns a success or failure message
  public String setCity(String newCity) {
    try {
      if (newCity == null || newCity.trim().isEmpty()) {
        return "Error: city cannot be empty.";
      }
      city = newCity.trim();
      return "City updated to " + city + ".";
    } catch (Exception e) {
      return "Error: could not update city.";
    }
  }

  // Update state, returns a success or failure message
  public String setState(String newState) {
    try {
      if (newState == null || newState.trim().isEmpty()) {
        return "Error: state cannot be empty.";
      }
      state = newState.trim();
      return "State updated to " + state + ".";
    } catch (Exception e) {
      return "Error: could not update state.";
    }
  }

  // Update zip code, returns a success or failure message
  public String setZipCode(int newZipCode) {
    try {
      if (newZipCode <= 0 || newZipCode > 99999) {
        return "Error: zip code must be a number up to 5 digits.";
      }
      zipCode = newZipCode;
      return "Zip code updated to " + newZipCode + ".";
    } catch (Exception e) {
      return "Error: could not update zip code.";
    }
  }

  // Update model name, returns a success or failure message
  public String setModelName(String newModelName) {
    try {
      if (newModelName == null || newModelName.trim().isEmpty()) {
        return "Error: model name cannot be empty.";
      }
      modelName = newModelName.trim();
      return "Model name updated to " + modelName + ".";
    } catch (Exception e) {
      return "Error: could not update model name.";
    }
  }

  // Update sale status, returns a success or failure message
  public String setSaleStatus(String newStatus) {
    try {
      String status = newStatus.trim().toLowerCase();
      if (isValidStatus(status)) {
        saleStatus = status;
        return "Sale status updated to " + status + ".";
      } else {
        return "Error: status must be sold, available, or under contract.";
      }
    } catch (Exception e) {
      return "Error: could not update sale status.";
    }
  }

  // List this home's information
  public String[] listHome() {
    try {
      String[] details = new String[7];
      details[0] = "Square Feet: " + squareFeet;
      details[1] = "Address: " + address;
      details[2] = "City: " + city;
      details[3] = "State: " + state;
      details[4] = "Zip Code: " + zipCode;
      details[5] = "Model: " + modelName;
      details[6] = "Sale Status: " + saleStatus;
      return details;
    } catch (Exception e) {
      String[] error = new String[1];
      error[0] = "Error: could not list home information.";
      return error;
    }
  }
}