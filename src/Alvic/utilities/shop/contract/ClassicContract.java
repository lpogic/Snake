package Alvic.utilities.shop.contract;

import Alvic.utilities.shop.Product;
import Alvic.utilities.shop.contract.stamp.Stamp;

public class ClassicContract<T> implements Contract<T> {

    private Class<T> brand;
    private Stamp stamp;

    public ClassicContract(Class<T> brand, Stamp stamp) {
        this.brand = brand;
        this.stamp = stamp;
    }

    Class<T> getBrand() {
        return brand;
    }

    @Override
    public T fetch(Product product, boolean spend) {
        return brand.cast(stamp.seal(product,spend));
    }
}
