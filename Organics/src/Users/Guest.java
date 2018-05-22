package Users;

import java.util.Objects;

public class Guest extends User {

    private String address;
    //private BasketWallet.Basket userBasket= new BasketWallet.Basket();
    //private BasketWallet.Wallet userWallet= new BasketWallet.Wallet();
    /**
     */
    public Guest(String name, String surname, String username, String password, String email, int userType) {
        super(name, surname, username, password, email, userType);
    }
    /**
     */
    public Guest() {
    }
    /**
     */
    public boolean upDateFilter(String str)
    {
        return true;
    }
    /**
     */
    public void updateInfo()
    {
        System.out.println(getName() + " " + getSurname() + " icin bilgileri guncelle");
    }
    /**
     */
    public void updateBasket()
    {

    }
    /**
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) &&
                Objects.equals(getSurname(), user.getSurname()) &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getEmail(), user.getEmail());
    }
}
