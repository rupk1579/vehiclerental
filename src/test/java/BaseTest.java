import com.vehical.rental.factory.DisplayFactory;
import com.vehical.rental.repo.InMemoryStorageRepo;
import com.vehical.rental.service.*;

public class BaseTest {

    protected IAdminService adminService;
    protected DisplayFactory displayFactory;
    protected IBookedVehicleService bookedVehicleService;
    protected ILockManagesService lockManagesService;
    protected  IPaymentService paymentService;

    public void initializeAllService(int timeOut) {

        InMemoryStorageRepo inMemoryStorageRepo = InMemoryStorageRepo.getInstance();
        adminService = new AdminServiceImpl(inMemoryStorageRepo);

        lockManagesService = new LockManagesServiceImpl(timeOut);
        displayFactory = new DisplayFactory(lockManagesService);
        bookedVehicleService = new BookedVehicleServiceImpl(inMemoryStorageRepo,lockManagesService);
        paymentService =  new PaymentService(lockManagesService,bookedVehicleService);
    }
}
