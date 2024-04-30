/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainreservations;

/**
 *
 * @author reemk
 */
import java.util.*;

// Passenger class
class Passenger {
    private String name;
    private String idNumber;

    public Passenger(String name, String idNumber) {
        this.name = name;
        this.idNumber = idNumber;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getIdNumber() {
        return idNumber;
    }
}

// TrainRoute class
class TrainRoute {
    private String source;
    private String destination;
    private String schedule;
    private List<Seat> availableSeats;

    public TrainRoute(String source, String destination, String schedule) {
        this.source = source;
        this.destination = destination;
        this.schedule = schedule;
        this.availableSeats = generateSeats(50); // Assuming 50 seats
    }

    private List<Seat> generateSeats(int numberOfSeats) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(String.valueOf(i)));
        }
        return seats;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }
}

// TicketBookingSystem class
class TicketBookingSystem {
    public TrainTicket bookTicket(TrainRoute route, Passenger passenger, Seat seat) {
        // Assume booking logic here
        String ticketRoute = route.getSource() + " to " + route.getDestination();
        String currentDate = new Date().toString(); // Get current date
        route.getAvailableSeats().remove(seat); // Reserve the seat
        return new TrainTicket(ticketRoute, currentDate, passenger, seat);
    }
}

// TrainTicket class
class TrainTicket {
    private String route;
    private String date;
    private Passenger passenger;
    private Seat seat;

    public TrainTicket(String route, String date, Passenger passenger, Seat seat) {
        this.route = route;
        this.date = date;
        this.passenger = passenger;
        this.seat = seat;
    }

    // Getters
    public String getRoute() {
        return route;
    }

    public String getDate() {
        return date;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Seat getSeat() {
        return seat;
    }
}

// Seat class
class Seat {
    private String seatNumber;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void reserve() {
        System.out.println("Seat " + seatNumber + " reserved.");
    }
}

// Main class
public class TrainReservations {
    public static void main(String[] args) {
        // Welcome message
        System.out.println("Welcome to the Train Ticket Booking System!");

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            // Display menu
            System.out.println("\nMain Menu:");
            System.out.println("1. Book Ticket");
            System.out.println("2. View Ticket");
            System.out.println("3. Exit");

            // Get user choice
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    bookTicket(scanner);
                    break;
                case 2:
                    // Display ticket
                    System.out.println("Please book a ticket first.");
                    break;
                case 3:
                    System.out.println("Thank you for using the Train Ticket Booking System. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        // Close scanner
        scanner.close();
    }

    private static void bookTicket(Scanner scanner) {
        // Get passenger information
        System.out.print("\nEnter passenger name: ");
        String passengerName = scanner.nextLine();
        System.out.print("Enter passenger ID number: ");
        String passengerIdNumber = scanner.nextLine();
        Passenger passenger = new Passenger(passengerName, passengerIdNumber);

        // Get train route information
        System.out.print("Enter source: ");
        String source = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        String schedule = selectSchedule(scanner); // Select schedule
        TrainRoute route = new TrainRoute(source, destination, schedule);

        // Display available seats
        System.out.println("Available seats:");
        List<Seat> availableSeats = route.getAvailableSeats();
        for (Seat seat : availableSeats) {
            System.out.print(seat.getSeatNumber() + " ");
        }
        System.out.println();

        // Select seat
        System.out.print("Enter seat number to reserve: ");
        String selectedSeatNumber = scanner.nextLine();
        Seat selectedSeat = null;
        for (Seat seat : availableSeats) {
            if (seat.getSeatNumber().equals(selectedSeatNumber)) {
                selectedSeat = seat;
                break;
            }
        }

        // Book Ticket
        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        TrainTicket ticket = bookingSystem.bookTicket(route, passenger, selectedSeat);

        // Display ticket details
        System.out.println("\nTicket booked successfully:");
        System.out.println("Route: " + ticket.getRoute());
        System.out.println("Date: " + ticket.getDate());
        System.out.println("Passenger: " + ticket.getPassenger().getName());
        System.out.println("Seat: " + ticket.getSeat().getSeatNumber());
    }

    private static String selectSchedule(Scanner scanner) {
        // Display schedule options
        System.out.println("Available schedules:");
        System.out.println("1. 08:00 AM");
        System.out.println("2. 12:00 PM");
        System.out.println("3. 04:00 PM");

        // Get user choice
        System.out.print("Enter schedule number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Map choice to schedule
        switch (choice) {
            case 1:
                return "08:00 AM";
            case 2:
                return "12:00 PM";
            case 3:
                return "04:00 PM";
            default:
                System.out.println("Invalid choice. Defaulting to 08:00 AM.");
                return "08:00 AM";
        }
    }
}
