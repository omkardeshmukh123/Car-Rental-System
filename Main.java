import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {

    private int CarID;
    private String CarModel;
    private String CarBrand;
    private double BasePrice;
    public boolean isAvailable;

    Car(int CarID, String CarModel,String CarBrand,double BasePrice, boolean isAvailable){
        this.CarID = CarID;
        this.CarModel = CarModel;
        this.CarBrand = CarBrand;
        this.BasePrice = BasePrice;
        this.isAvailable = true; 
    }

    //getter methods
    public int getCarID() {
        return CarID;
    }

    public String getBrand() {
        return CarBrand;
    }

    public double getBasePrice() {
        return BasePrice;
    }
    
    public boolean getAvailability() {
        return isAvailable;
    }

    public void rent() {
        isAvailable = false;
    }

    public void returnCar() {
        isAvailable = true;
    }

    public double calculatePrice(int rentalDays){
        return BasePrice * rentalDays;
    }



}

class Customer{
    private String CustomerID;
    private String CustomerName;

    Customer(String CustomerID, String CustomerName){
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
    }

    //getter methods
    public String getCustomerID() {
        return CustomerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

}

class Rental {

    private Car car; //yaha pe car type ka variable banaya hai 
    private Customer customer;
    private int rentalDays;

    public Rental(Car car, Customer customer, int rentalDays) {
        this.car = car;
        this.customer = customer;
        this.rentalDays = rentalDays;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getRentalDays() {
        return rentalDays;
    }
}

class CarRentalSystem{
    //using arraylist to store cars, customers and rentals and yaha humne sirf declare ki hai 
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    //ab hum 3 array list banayenge jo data lene ke liye tayyar rahengi
    CarRentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    //car type ka object ayega aur usin mein list mein add kar denge
    public void addCar(Car car){
        cars.add(car);
    }

    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car, Customer customer, int rentalDays){
        if(car.isAvailable()){
            car.rent();
            rentals.add(new Rental(car, customer, rentalDays)); //car rent ho gayi hai toh saare data ko rentals mein add kr dunga
        } else {
            System.out.println("Car is not available for rent.");
        }
    }


    public void returnCar(Car car){
        car.returnCar();
        Rental rentalToRemove = null;
        for(Rental rental : rentals){
            if(rental.getCar() == car){
                rentalToRemove = rental;
                break;
            }
        }

        if(rentalToRemove != null){
            rentals.remove(rentalToRemove);
            System.out.println("Car returned successfully.");
        } else {
            System.out.println("No rental record found for this car.");
        }
    }

    public void menu(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("1. Add Car");
            System.out.println("2. Add Customer");
            System.out.println("3. Rent Car");
            System.out.println("4. Return Car");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch(choice){
                case 1:
                    System.out.print("Enter Car ID: ");
                    int carID = scanner.nextInt();
                    scanner.nextLine(); // consume newline
                    System.out.print("Enter Car Model: ");
                    String carModel = scanner.nextLine();
                    System.out.print("Enter Car Brand: ");
                    String carBrand = scanner.nextLine();
                    System.out.print("Enter Base Price: ");
                    double basePrice = scanner.nextDouble();
                    Car car = new Car(carID, carModel, carBrand, basePrice, true);
                    addCar(car);
                    System.out.println("Car added successfully.");
                    break;
                case 2:
                    System.out.print("Enter Customer ID: ");
                    String customerID = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    Customer customer = new Customer(customerID, customerName);
                    addCustomer(customer);
                    System.out.println("Customer added successfully.");
                    break;
                case 3:
                    // Rent Car logic here
                    break;
                case 4:
                    // Return Car logic here
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public static void main(String[] args) {
        CarRentalSystem crs = new CarRentalSystem();
        crs.menu();
        
    }
}