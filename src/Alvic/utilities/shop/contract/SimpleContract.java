package Alvic.utilities.shop.contract;

import Alvic.utilities.shop.Product;
import Alvic.utilities.shop.contract.stamp.Stamp;

public class SimpleContract implements Contract<Object> {

    private Stamp stamp;

    public SimpleContract(Stamp stamp) {
        this.stamp = stamp;
    }

    @Override
    public Object fetch(Product product, boolean spend) {
        return stamp.seal(product, spend);
    }
}
