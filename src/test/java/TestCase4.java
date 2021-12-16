import com.vehical.rental.enums.PaymentMode;
import com.vehical.rental.enums.PaymentStatus;
import com.vehical.rental.enums.VehicleType;
import com.vehical.rental.exception.AlreadyBookedException;
import com.vehical.rental.exception.TemporaryUnavailableException;
import com.vehical.rental.model.Address;
import com.vehical.rental.model.BookingInfo;
import com.vehical.rental.model.Branch;
import com.vehical.rental.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCase4 extends BaseTest{

    @BeforeEach
    void init() {
        initializeAllService(120);
    }

    @Test
    void case4(){
        Branch branch1 = new Branch("branch1", new Address("city1", "state1", "zipcode1"));

        Vehicle vehicle1 = new Vehicle(VehicleType.CAR, "V1",400.00);
        Vehicle vehicle2 = new Vehicle(VehicleType.BIKE, "V2",200.00);
        Vehicle vehicle3 = new Vehicle(VehicleType.BIKE, "V3",500.00);
        Vehicle vehicle4 = new Vehicle(VehicleType.BIKE, "V4",100.00);
        Vehicle vehicle5  = new Vehicle(VehicleType.BIKE, "V5",300.00);

        List<Branch> branches = new ArrayList<>();
        branches.add(branch1);


        List<Vehicle> vehicleList1 = new ArrayList<>();
        vehicleList1.add(vehicle1);
        vehicleList1.add(vehicle2);
        vehicleList1.add(vehicle3);
        vehicleList1.add(vehicle4);
        vehicleList1.add(vehicle5);



        // Add list of vehicle to particular branch.
        adminService.AddVehicle(branch1,vehicleList1);
        System.out.println("Successfully vehicles added..");

        //Display price before 5 trnx
        displayFactory.getDisplayMethod(branch1).displayAllAvailableVehicles(branch1);
        try {

            //Making 5 successful Payment
            BookingInfo bookingInfo1 = bookedVehicleService.bookParticularVehicle(vehicle1, 400.00);
            paymentService.makeSuccessfulPayment(bookingInfo1, PaymentMode.UPI, PaymentStatus.SUCCESS);

            BookingInfo bookingInfo2 = bookedVehicleService.bookParticularVehicle(vehicle2, 200.00);
            paymentService.makeSuccessfulPayment(bookingInfo2, PaymentMode.UPI, PaymentStatus.SUCCESS);

            BookingInfo bookingInfo3 = bookedVehicleService.bookParticularVehicle(vehicle3, 500.00);
            paymentService.makeSuccessfulPayment(bookingInfo3, PaymentMode.UPI, PaymentStatus.SUCCESS);

            BookingInfo bookingInfo4 = bookedVehicleService.bookParticularVehicle(vehicle4, 100.00);
            paymentService.makeSuccessfulPayment(bookingInfo4, PaymentMode.UPI, PaymentStatus.SUCCESS);


            System.out.println("Price after 4th transaction");
            displayFactory.getDisplayMethod(branch1).displayAllAvailableVehicles(branch1);

            BookingInfo bookingInfo5 = bookedVehicleService.bookParticularVehicle(vehicle5, 300.00);
            paymentService.makeSuccessfulPayment(bookingInfo5, PaymentMode.UPI, PaymentStatus.SUCCESS);

        } catch (TemporaryUnavailableException ex) {
            System.out.println("User1 ,Please try for different vehicle..");
        }  catch (AlreadyBookedException ex){
            System.out.println("Already booked");
        }
    }
}
