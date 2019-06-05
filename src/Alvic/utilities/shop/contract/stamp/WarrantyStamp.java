package Alvic.utilities.shop.contract.stamp;

import Alvic.utilities.shop.Product;

public class WarrantyStamp implements Stamp {

    @Override
    public Object seal(Product product, boolean spend) {
        if(!product.isStored())
            product.set(product.imp());
        return product.get();
    }
}
