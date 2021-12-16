import com.vehical.rental.enums.PaymentMode;
import com.vehical.rental.enums.PaymentStatus;
import com.vehical.rental.enums.VehicleType;
import com.vehical.rental.exception.TemporaryUnavailableException;
import com.vehical.rental.model.Address;
import com.vehical.rental.model.BookingInfo;
import com.vehical.rental.model.Branch;
import com.vehical.rental.model.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCase2  extends BaseTest{

    @BeforeEach
    void init(){
        initializeAllService(120);
    }

    @Test
    void case2(){
        Branch branch1 = new Branch("branch1", new Address("city1", "state1", "zipcode1"));
        Branch branch2 = new Branch("branch2", new Address("city2", "state2", "zipcode2"));

        Vehicle vehicle1 = new Vehicle(VehicleType.CAR, "V1",400.00);
        Vehicle vehicle2 = new Vehicle(VehicleType.BIKE, "V2",200.00);
        Vehicle vehicle3 = new Vehicle(VehicleType.BIKE, "V3",300.00);

        List<Branch> branches = new ArrayList<>();
        branches.add(branch1);
        branches.add(branch2);

        List<Vehicle> vehicleList1 = new ArrayList<>();
        vehicleList1.add(vehicle1);
        vehicleList1.add(vehicle2);

        List<Vehicle> vehicleList2 = new ArrayList<>();
        vehicleList2.add(vehicle3);

        // Add list of vehicle to particular branch.
        adminService.AddVehicle(branch1,vehicleList1);
        System.out.println("Successfully vehicles added..");
        // Add list of vehicle to particular branch.
        adminService.AddVehicle(branch2,vehicleList2);
        System.out.println("Successfully vehicles added..");

        // Display all available vehicle for branch 1
        displayFactory.getDisplayMethod(branch1).displayAllAvailableVehicles(branch1);

        // Display all available vehicle for branch 2
        displayFactory.getDisplayMethod(branch2).displayAllAvailableVehicles(branch2);
        //Book and make successful payment
        try{
            BookingInfo bookingInfo = bookedVehicleService.bookParticularVehicle(vehicle1,400.00);
            paymentService.makeSuccessfulPayment(bookingInfo, PaymentMode.UPI, PaymentStatus.SUCCESS);
            System.out.println("User1 has made booking for Vehicle:" + vehicle1.getVehicleNumber() + " booked whose booking id is:" + bookingInfo.getBookingId());
        } catch (TemporaryUnavailableException ex) {
            System.out.println("User1 ,Please try for different vehicle..");
        }
    }


}
