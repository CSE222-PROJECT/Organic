package Panels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import CityGraph.*;
import BinarySearch.*;
import Products.*;
import Sort.*;
import Users.*;
import BasketWallet.*;

public class UserPanel {

    /***
     * object for user
     */
    private User currentGuest=new Guest();
    /**
     * object for basket
     */
    private  Basket order=new Basket();
    /**
     * object for product
     */
    ProductManagement allProduct=new ProductManagement();
    /**
     * all clothes
     */
    private KWPriorityQueue<Clothes> ClothesList= new KWPriorityQueue<>();
    /**
     * all nutrients
     */
    private KWPriorityQueue<Nutrient> NutrientList= new KWPriorityQueue<>();
    /**
     * all cosmetics
     */
    private KWPriorityQueue<Cosmetics> CosmeticsList= new KWPriorityQueue<>();
    /**
     * filter for range cost like 0-20 , 0-30
     */
    private ArrayList<String> nextFilter = new ArrayList<>();

    BinarySearchTree<Clothes> costFilterClothes=new BinarySearchTree<>();
    BinarySearchTree<Nutrient> costFilterNutrient=new BinarySearchTree<>();
    BinarySearchTree<Cosmetics> costFilterCosmetic=new BinarySearchTree<>();

    CityMap kargoPath=new CityMap();


    public static void main(String[] args)
    {

    }

    /**
     * constructor with user
     * @param user object
     */
    public UserPanel(User user)  {

        currentGuest=user;
        //Scanner in=new Scanner(System.in);
        mainPage();
    }

    /**
     * print all options
     * @param in scanner
     * @return option char
     */
    public char printMainOption(Scanner in)
    {
        System.out.println("**************************************************************************");
        System.out.println("***********************    ORGANIC  ***************************************");
        System.out.println("***********************  HOSGELDINIZ "+ currentGuest.getName() +"  "+currentGuest.getSurname()+"  ***************************************");
        System.out.println("k) Kategoriler");
        System.out.println("p) Profil");
        System.out.println("c) Cikis");

        char result = in.next().charAt(0);

        while (!(result == 'k' || result == 'p' || result == 'c')){
            System.out.println(result);
            System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
            result=in.next().charAt(0);
        }
        return result;
    }

    /**
     * select options
     */
    public void mainPage(){

        Scanner in=new Scanner(System.in);
        char  categoryChoise;
        boolean flag=true;


        while(flag)
        {
            char mainChoise=printMainOption(in);
            switch (mainChoise){
                case 'k':
                    categoryChoise=selectCategory(in);
                    int status = -1;
                    if (categoryChoise == 'a')
                        status = 0;
                    if (categoryChoise == 'b')
                        status = 1;
                    if (categoryChoise == 'c')
                        status = 2;

                    selectOption(status);
                    break;
                case 'p':
                    currentGuest.updateInfo();
                    break;
                case 'c':
                    flag=false;
                    System.out.println("Basarili sekilde cikis yapildi");
                    break;
                default:
                    break;

            }

        }
    }

    /**
     * select category
     * @param in scanner
     * @return category
     */
    public char selectCategory(Scanner in)
    {
        System.out.println("a) Giyim");
        System.out.println("b) Kozmetik");
        System.out.println("c) Gida");
        System.out.println("d) Ana sayfa");

        char result = in.next().charAt(0);

        while (!(result == 'a' || result == 'b' || result == 'c' || result == 'd')){
            System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
            result=in.next().charAt(0);
        }
        return result;

    }

    /**
     * category redirecting
     * @param categoryChoise choice
     */
    public void categoryPage(char categoryChoise){
        switch (categoryChoise){
            case 'a':
                clothesPage();
                break;
            case 'b':
                cosmeticPage();
                break;
            case 'c':
                nutrientPage();
                break;
            case 'd':
                mainPage();
                break;
            default:
                break;

        }

    }

    public void selectOption(int status){

        System.out.println("f) Filtreleme");
        System.out.println("o) Onceki sayfa");
        System.out.println("g) Urunleri goster");

        Scanner in=new Scanner(System.in);
        char  categoryChoise = in.next().charAt(0);

        while (!(categoryChoise == 'f' || categoryChoise == 'o' || categoryChoise == 'g')){
            System.out.println(categoryChoise);
            System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
            categoryChoise=in.next().charAt(0);
        }

        switch (categoryChoise){

            case 'f' :
                filterPage(status);
            break;
            case 'o' :
                mainPage();
            break;
            case 'g' :
                if(status == 0){
                    clothesPage();
                }
                else if(status == 1){
                    nutrientPage();
                }
                else{
                    cosmeticPage();
                }

                break;

                default:
                    break;
        }
    }

    public void filterPage(int status){

        System.out.println("    FILTRELEME EKRANI   ");
        System.out.println("1) Fiyata gore");
        System.out.println("2) Etikete gore");
        System.out.println("3) Bedene gore");
        System.out.println("4) Materyaline gore");
        System.out.println("5) Cinsiyete gore");

        Scanner in=new Scanner(System.in);
        int  choice = in.nextInt();

        while (!(choice == 1 || choice == 2 || choice == 3 || choice == 4 || choice == 5)){
            System.out.println(choice);
            System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
            choice=in.nextInt();
        }

        switch (choice){

            case 1 :
                filterForPriceRange(status);
                break;
            case 2 :
                filterForTag(status);
                break;
            case 3 :
                filterForSize();
                break;
            case 4 :
                filterForMaterial(status);
                break;
            case 5 :
                filterForGender();
                break;

            default:
                break;
        }
    }

    /**
     * clothes page
     */
    public void clothesPage(){
        Scanner in=new Scanner(System.in);
        int choise;
        char selectOption;

        filtered(readUserFilters(), 0);

        System.out.println("size"+ClothesList.size());
        if(ClothesList.size() != 0){
            System.out.println("Urunu sepete eklemek icin numarasini girin : ");

            choise=in.nextInt();

            while(choise >= ClothesList.size()) {
                System.out.println("Gecersiz numara ");
                choise = in.nextInt();
            }
            Product temp=ClothesList.getTheData(choise);
            order.add(temp);
            System.out.println("Ürün sepetinize eklendi\n");
        }

        else{
            System.out.println("Belirtilen filtreye uygun urun bulunamadi");
        }

    }

    /**
     * nutrient page
     */
    public void nutrientPage(){
        Scanner in=new Scanner(System.in);
        int choise;

        filtered(currentGuest.getFilter(), 1);


        if(NutrientList.size() != 0){
            System.out.println("Urunu sepete eklemek icin numarasini girin : ");
            choise=in.nextInt();
            while(choise >= NutrientList.size()) {
                System.out.println("Gecersiz numara ");
                choise = in.nextInt();
            }
            Product temp=NutrientList.getTheData(choise);
            order.add(temp);
        }
        else{
            System.out.println("Belirtilen filtreye uygun urun bulunamadi");
        }

    }

    /**
     * cosmetic page
     */
    public void cosmeticPage(){
        Scanner in=new Scanner(System.in);
        int choise;

        filtered(currentGuest.getFilter(), 2);
        if(CosmeticsList.size() != 0){
            System.out.println("Urunu sepete eklemek icin numarasini girin : ");

            choise=in.nextInt();
            while(choise >= CosmeticsList.size()) {
                System.out.println("Gecersiz numara ");
                choise = in.nextInt();
            }
            Product temp=CosmeticsList.getTheData(choise);
            order.add(temp);
            System.out.println("Ürün sepetinize eklendi\n");
        }
        else{
            System.out.println("Belirtilen filtreye uygun urun bulunamadi");
        }

    }

    /**
     * filter all product
     * @param filter general or personal
     * @param status clothes or nutrients
     */
    public void filtered(ArrayList<String> filter, int status)
    {
        int flag=0;
        if(status==0) {

            for (int i = 0; i < allProduct.ClothesList.size(); i++) {

                for (int j = 0; j < allProduct.ClothesList.get(i).features.size(); j++) {
                    for (int k = 0; k < filter.size(); k++) {
                        //System.out.println(allProduct.ClothesList.get(i).features.get(j)+"="+filter.get(k));
                        if (allProduct.ClothesList.get(i).features.get(j).equals(filter.get(k)))
                        {
                            k = filter.size();
                            flag = -1;
                        }

                    }
                    if(flag==-1)
                        j = allProduct.ClothesList.get(i).features.size();

                }

                if (flag == 0) {

                    this.ClothesList.offer(allProduct.ClothesList.get(i));
                }
                flag = 0;
            }

        }


        if(status==1) {
            for (int i = 0; i < allProduct.NutrientList.size(); i++) {

                for (int j = 0; j < allProduct.NutrientList.get(i).features.size(); j++) {
                    for (int k = 0; k < filter.size(); k++) {
                        //System.out.println(allProduct.ClothesList.get(i).features.get(j)+"="+filter.get(k));
                        if (allProduct.NutrientList.get(i).features.get(j).equals(filter.get(k)))
                        {
                            k = filter.size();
                            flag = -1;
                        }

                    }
                    if(flag==-1)
                        j = allProduct.NutrientList.get(i).features.size();

                }

                if (flag == 0) {

                    this.NutrientList.offer(allProduct.NutrientList.get(i));
                }
                flag = 0;
            }
        }

        if(status==2) {
            for (int i = 0; i < allProduct.CosmeticsList.size(); i++) {

                for (int j = 0; j < allProduct.CosmeticsList.get(i).features.size(); j++) {
                    for (int k = 0; k < filter.size(); k++) {
                        //System.out.println(allProduct.ClothesList.get(i).features.get(j)+"="+filter.get(k));
                        if (allProduct.CosmeticsList.get(i).features.get(j).equals(filter.get(k)))
                        {
                            k = filter.size();
                            flag = -1;
                        }

                    }
                    if(flag==-1)
                        j = allProduct.CosmeticsList.get(i).features.size();

                }

                if (flag == 0) {

                    this.CosmeticsList.offer(allProduct.CosmeticsList.get(i));
                }
                flag = 0;
            }
        }

        printProduct(status);
    }

    /**
     * print products
     * @param status clothes or nutrients
     */
    public void printProduct(int status){
        ArrayList<Product> sorted=new ArrayList<>();
        if(status==0) {
            Sort<Clothes> sortClothes= new Sort<Clothes>();
            sortClothes.addAll(ClothesList);
            sorted.addAll(sortClothes.sortIncreasingOrder());
            for (int i = 0; i <this.ClothesList.size();i++){
                System.out.println(i +") " + this.ClothesList.getTheData(i).getName());
            }
        }

        if(status==1) {
            for (int i = 0; i <this.NutrientList.size();i++){
                System.out.println(i +") " + this.NutrientList.getTheData(i).getName());
            }
        }

        if(status==2) {
            for (int i = 0; i <this.CosmeticsList.size();i++){
                System.out.println(i +") " + this.CosmeticsList.getTheData(i).getName());
            }
        }
    }

    /**
     * filter for price range like 0-20
     * @return choice
     */
    public boolean filterForPriceRange(int status ){
        ArrayList<Clothes> clothes=new ArrayList<>();
        ArrayList<Nutrient> nutrient=new ArrayList<>();
        ArrayList<Cosmetics> cosmetic=new ArrayList<>();

        System.out.println("Lutfen fiyat araligini giriniz");
        Scanner in = new Scanner(System.in);
        double choice =  in.nextInt();

        if (status==0){
            for (int i = 0; i < ClothesList.size(); i++) {
                costFilterClothes.add(ClothesList.getTheData(i));
            }
            Clothes clothesFiyat=new Clothes();
            clothesFiyat.setPrice(choice);
            clothes=costFilterClothes.getDescendingOrder(clothesFiyat);
            for (int i = 0 ; i < clothes.size() ; ++i){
                System.out.println(i + ") " + clothes.get(i));
            }
        }
        if (status==1){
            for (int i = 0; i < NutrientList.size(); i++) {
                costFilterNutrient.add(NutrientList.getTheData(i));
            }
            Nutrient nutrientFiyat=new Nutrient();
            nutrientFiyat.setPrice(choice);
            nutrient=costFilterNutrient.getDescendingOrder(nutrientFiyat);
            for (int i = 0 ; i < nutrient.size() ; ++i){
                System.out.println(i + ") " + nutrient.get(i));
            }
        }
        if (status==2){
            for (int i = 0; i < ClothesList.size(); i++) {
                costFilterCosmetic.add(CosmeticsList.getTheData(i));
            }
            Cosmetics cosmeticFiyat= new Cosmetics();
            cosmeticFiyat.setPrice(choice);
            cosmetic=costFilterCosmetic.getDescendingOrder(cosmeticFiyat);
            for (int i = 0 ; i < cosmetic.size() ; ++i){
                System.out.println(i + ") " + cosmetic.get(i));
            }
        }




        /**Binary searchTreeye urunler eklenecek*/
        //costFilter.showRangeOfCost();

        return true;

    }

    /**
     * filter for sorted price
     * @return choice
     */
    public boolean filterForSortedPrise(){
        // Merge sort static oldugu icin objesini olusturmamiza gerek yok direk bu metodun
         //icinde kullanabiliriz . Bu metodun icinde artan azalan olarak siralanacak

        return true;

    }

    /**
     *
     * @param category clothes or nutrients
     * @return filter
     */
    public void filterForTag(int category){


        Scanner in = new Scanner(System.in);
        if(category == 0){

            System.out.println("1 )Etek \n2) Pantolon\n3) Gömlek\n4) Shirt\n5) Elbise");

            int  choice = in.nextInt();

            while (!(choice == 1 || choice == 2 || choice == 3 || choice == 4|| choice == 5)){
                System.out.println(choice);
                System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
                choice=in.nextInt();
            }
            switch (choice) {

                case 1:
                    nextFilter.add("Etek");
                    break;
                case 2:
                    nextFilter.add("Pantolon");
                    break;
                case 3:
                    nextFilter.add("Gömlek");
                    break;
                case 4:
                    nextFilter.add("Shirt");
                    break;
                case 5:
                    nextFilter.add("Elbise");
                    break;
                default:
                    break;
            }


        }
        if(category == 1){
            System.out.println("1) Kahve \n2) Biskuvi\n3) Meyve Suyu\n4) Cubuk Kraker\n5) Pogaca");

            int  choice = in.nextInt();

            while (!(choice == 1 || choice == 2 || choice == 3 || choice == 4|| choice == 5)){
                System.out.println(choice);
                System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
                choice=in.nextInt();
            }
            switch (choice) {

                case 1:
                    nextFilter.add("Kahve");
                    break;
                case 2:
                    nextFilter.add("Biskuvi");
                    break;
                case 3:
                    nextFilter.add("Meyve Suyu");
                    break;
                case 4:
                    nextFilter.add("Cubuk Kraker");
                    break;
                case 5:
                    nextFilter.add("Pogaca");
                    break;
                default:
                    break;
            }


        }
        if(category == 2){

            System.out.println("1) Krem \n2) Fondoten\n3) Parfum\n4) Eyeliner\n5) Ruj");

            int  choice = in.nextInt();

            while (!(choice == 1 || choice == 2 || choice == 3 || choice == 4|| choice == 5)){
                System.out.println(choice);
                System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
                choice=in.nextInt();
            }
            switch (choice) {

                case 1:
                    nextFilter.add("Krem");
                    break;
                case 2:
                    nextFilter.add("Fondoten");
                    break;
                case 3:
                    nextFilter.add("Parfum");
                    break;
                case 4:
                    nextFilter.add("Eyeliner");
                    break;
                case 5:
                    nextFilter.add("Ruj");
                    break;
                default:
                    break;
            }

        }

    }

    public void filterForMaterial(int category){


        Scanner in = new Scanner(System.in);
        if(category == 0){

            System.out.println("1 )Pamuk \n2) Keten\n3) Ipek\n4) Polyester\n5) Viskon");

            int  choice = in.nextInt();

            while (!(choice == 1 || choice == 2 || choice == 3 || choice == 4|| choice == 5)){
                System.out.println(choice);
                System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
                choice=in.nextInt();
            }
            switch (choice) {

                case 1:
                    nextFilter.add("Pamuk");
                    break;
                case 2:
                    nextFilter.add("Keten");
                    break;
                case 3:
                    nextFilter.add("Ipek");
                    break;
                case 4:
                    nextFilter.add("Polyester");
                    break;
                case 5:
                    nextFilter.add("Viskon");
                    break;
                default:
                    break;
            }


        }
        if(category == 1){
            System.out.println("1) Seker \n2) Baharat\n3) Gluten\n4) Sut\n5) Yag");

            int  choice = in.nextInt();

            while (!(choice == 1 || choice == 2 || choice == 3 || choice == 4|| choice == 5)){
                System.out.println(choice);
                System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
                choice=in.nextInt();
            }
            switch (choice) {

                case 1:
                    nextFilter.add("Seker");
                    break;
                case 2:
                    nextFilter.add("Baharat");
                    break;
                case 3:
                    nextFilter.add("Gluten");
                    break;
                case 4:
                    nextFilter.add("Sut");
                    break;
                case 5:
                    nextFilter.add("Yag");
                    break;
                default:
                    break;
            }


        }
        if(category == 2){

            System.out.println("1) Palm yagi \n2) Gliserin\n3) Titanyum dioksit\n4) Florur\n5) SLS ");

            int  choice = in.nextInt();

            while (!(choice == 1 || choice == 2 || choice == 3 || choice == 4|| choice == 5)){
                System.out.println(choice);
                System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
                choice=in.nextInt();
            }
            switch (choice) {

                case 1:
                    nextFilter.add("Palm yagi");
                    break;
                case 2:
                    nextFilter.add("Gliserin");
                    break;
                case 3:
                    nextFilter.add("Titanyum dioksit");
                    break;
                case 4:
                    nextFilter.add("Florur");
                    break;
                case 5:
                    nextFilter.add("SLS");
                    break;
                default:
                    break;
            }

        }

    }

    private void filterForGender(){

        System.out.println("1) Kadin \n2) Erkek");

        Scanner in=new Scanner(System.in);
        int  choice = in.nextInt();

        while (!(choice == 1 || choice == 2)){
            System.out.println(choice);
            System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
            choice=in.nextInt();
        }

        switch (choice) {

            case 1:
                nextFilter.add("K");
                break;
            case 2:
                nextFilter.add("E");
                break;
            default:
                break;
        }


        System.out.println(nextFilter.toString());


    }
    private void filterForSize(){

        System.out.println("1) S \n2) M \n3) L");

        Scanner in=new Scanner(System.in);
        int  choice = in.nextInt();

        while (!(choice == 1 || choice == 2 || choice == 3)){
            System.out.println(choice);
            System.out.print("Yanlis girdiniz!Tekrar deneyin: ");
            choice=in.nextInt();
        }

        switch (choice) {

            case 1:
                nextFilter.add("S");
                break;
            case 2:
                nextFilter.add("M");
                break;
            case 3:
                nextFilter.add("L");
                break;
            default:
                break;
        }


        System.out.println(nextFilter.toString());


    }
    void printFilterOptions(ArrayList<String> options){

        for (int i = 0 ; i < options.size() ; ++i){
            System.out.println(i + ") " + options.get(i));
        }
    }

    /**
     *
     * @param bound of filter
     * @param choice choice
     * @param in scanner
     * @return choice
     */
    int checkValidity(int bound, int choice, Scanner in){

        choice = in.nextInt();
        while(choice < 0 || choice > bound){
            System.out.println("Lutfen gecerli bir sayı giriniz");
            choice = in.nextInt();
        }
        return choice;
    }

    /**
     *  read user filters from file to arraylist of strings
     */
    public ArrayList<String> readUserFilters(){
        ArrayList<String> tempFilters = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader("users_filters.csv"));
            String line;
            while ( (line = in.readLine() ) != null) {
                String[] tokens = line.split(",");
                String username = tokens[0];

                if(username.equals(currentGuest.getUsername()))
                {
                    for (int i = 1; i < tokens.length; i++) {
                        tempFilters.add(tokens[i]);
                    }
                    return tempFilters;
                }
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void herhangi(Object obj){

    }



}
