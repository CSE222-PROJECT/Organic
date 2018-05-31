import Panels.KWPriorityQueue;
import Panels.LoginPanel;
import Panels.SuperAdminPanel;
import Panels.UserPanel;
import Products.ProductManagement;
import Users.SuperAdmin;

public class Organics {

    public ProductManagement productManagement= new ProductManagement();
    public KWPriorityQueue<ProductManagement> priorityProducts= new KWPriorityQueue<>();
    public UserPanel userpanel;
    public ProductManagement companyPanel;
    public SuperAdminPanel superPanel;

    private LoginPanel p;

    /**
     * default constructor of Organics
     */
    public Organics() {
        
    }

    /**
     * the starting page of Organics
     * call Userpanel to give options and continue
     */
    public void start() {
        p = new LoginPanel();
        guestPanel();
        companyPanel();
        superAdminPanel();
        
    }

    /**
     * call the method of users to order smth
     */
    public void guestPanel(){
        if(p.getCurrentUser().getUserType()==3)
            userpanel=new UserPanel(p.getCurrentUser());

    }

    /**
     * company panel who cn add or remove products from database
     */
    public void companyPanel(){
        if(p.getCurrentUser().getUserType() == 2){
            companyPanel=new ProductManagement(p.getCurrentUser());
        }
    }

    public void superAdminPanel () {

        if (p.getCurrentUser().getUserType() == 1)
            superPanel = new SuperAdminPanel();


    }

    /**
     */
    public void showProductWithPriority()
    {

    }



}
