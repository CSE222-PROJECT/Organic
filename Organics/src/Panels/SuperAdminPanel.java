package Panels;

import java.io.IOException;
import java.util.Scanner;

import static Panels.UserPanel.confirmList;
import static Users.SuperAdmin.confirmBasket;
import static Users.SuperAdmin.showBaskets;

public class SuperAdminPanel {

    private int choice;

    public SuperAdminPanel () {
        SuperAdminMenu();

    }

    public void SuperAdminMenu () {

        System.out.println("1) Bekleyen Siparişleri Göster");
        System.out.println("2) Bekleyen Siparişleri Onayla");
        System.out.println("3) Önceki Sayfa");
        Scanner in = new Scanner(System.in);
        choice = in.nextInt();
        SuperAdminPage(choice);

    }

    public void SuperAdminPage (int choice) {

        switch (choice) {
            case 1 : showBaskets();
                break;
            case 2 : confirmBasket();
                break;
            case 3 :
                break;

            default:
                System.out.println("Hatalı Seçim");
                break;
        }

    }



}
