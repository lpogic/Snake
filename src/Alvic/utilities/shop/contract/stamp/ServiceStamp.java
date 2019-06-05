package Alvic.utilities.shop.contract.stamp;

import Alvic.utilities.shop.Product;

public class ServiceStamp implements Stamp {

    @Override
    public Object seal(Product product, boolean spend) {
        return product.imp();
    }
}
