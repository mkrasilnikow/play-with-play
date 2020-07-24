package mapper;

import model.BrandEntity;

import java.util.*;

public class BrandStore {
    private final Map<String, BrandEntity> brands = new HashMap<>();

    public Optional<BrandEntity> addBrand(BrandEntity brand) {
        brands.put(brand.getName(), brand);
        return Optional.of(brand);
    }

    public Optional<BrandEntity> getBrand(String id) {
        return Optional.ofNullable(brands.get(id));
    }

    public Set<BrandEntity> getAll() {
        return new HashSet<>(brands.values());
    }

    public Optional<BrandEntity> update(BrandEntity brand) {
        var name = brand.getName();
        if (brands.containsKey(name)) {
            brands.put(name, brand);
            return Optional.ofNullable(brand);
        }
        return null;
    }

    public boolean delete(String id) {
        return brands.remove(id) != null;
    }
}
