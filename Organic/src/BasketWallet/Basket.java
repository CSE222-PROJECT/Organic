package BasketWallet;

import Products.ProductInterface;

import java.util.ArrayList;

public class Basket {


    protected ArrayList<ProductInterface> sepet= new ArrayList<>();

    /**
     * default const
     */
    public Basket(){}

    /**
     * @param sepet the other basket to initialize
     */
    public Basket(ArrayList<ProductInterface> sepet) {
        this.sepet = sepet;
    }
    /**
     */
    public ArrayList<ProductInterface> getSepet() {
        return sepet;
    }
    /**
     */
    public void add(ProductInterface product)
    {
        sepet.add(product);
    }
    /**
     */
    @Override
    public String toString() {
        return "BasketWallet.Basket{" +
                "sepet=" + sepet +
                '}';
    }
}
