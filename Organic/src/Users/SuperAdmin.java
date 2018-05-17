package Users;

import BasketWallet.*;
import Panels.*;
import Products.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class SuperAdmin extends User {
    /**
     * user file
     */
    private String USERSFILE = "users.csv";
    public QueueList<Basket> order= new QueueList<>();

    /**
     * constructor
     * @param name of the admin
     * @param surname of the admin
     * @param username of the admin
     * @param password of the admin
     * @param email of the admin
     * @param userType of the admin
     */
    public SuperAdmin(String name, String surname, String username, String password, String email, int userType) {
        super(name, surname, username, password, email, userType);
    }

    /**
     *
     * @return filter
     */
    @Override
    public ArrayList<String> getFilter() {
        return null;
    }

    /**
     * update info
     */
    @Override
    public void updateInfo() {

    }

    /**
     * Confirm the basket
     */
    public boolean confirmBasket(){
        return true;
    }

    /**
     * Get infos and add c company to database
     * @return success
     */
    public boolean addCompany(){
        Scanner sc = new Scanner(System.in);
        int userType = 2;

        System.out.println("Name: ");
        String name = sc.next();

        System.out.println("Surname: ");
        String surname = sc.next();

        System.out.println("Username: ");
        String temp = sc.next();

        ArrayList<User> AllUsers = LoginPanel.readUsers();

        for (int i = 0; i < AllUsers.size(); i++) {
            if (temp.equals(AllUsers.get(i).getUsername())) {
                System.out.println("Username already exist!");
                return false;
            }
        }
        String username = temp;

        System.out.println("Password ");
        String password = sc.next();

        System.out.println("Email: ");
        String email = sc.next();

        String str = userType + "," + username + "," + password + "," + name + "," + surname + email + "\n";

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(USERSFILE, true));
            writer.append(str);

            writer.close();

            System.out.println("Users.Company added successfully!");
        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        return true;
    }

    /**
     * equals method
     * @param o object
     * @return boolean
     */
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
