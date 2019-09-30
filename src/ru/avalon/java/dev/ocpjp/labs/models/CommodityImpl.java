package ru.avalon.java.dev.ocpjp.labs.models;

public class CommodityImpl implements Commodity {
    private String code;
    private String vendorCode;
    private String name;
    private double price;
    private int residue;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getVendorCode() {
        return vendorCode;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int getResidue() {
        return residue;
    }

    static CommodityBuilderImpl builder() {
        return new CommodityBuilderImpl();
    }

    static class CommodityBuilderImpl implements CommodityBuilder {
        private CommodityImpl commodityBuilderImpl = new CommodityImpl();

        @Override
        public CommodityBuilderImpl code(String code) {
            commodityBuilderImpl.code = code;
            return this;
        }

        @Override
        public CommodityBuilderImpl vendorCode(String vendorCode) {
            commodityBuilderImpl.vendorCode = vendorCode;
            return this;
        }

        @Override
        public CommodityBuilderImpl name(String name) {
            commodityBuilderImpl.name = name;
            return this;
        }

        @Override
        public CommodityBuilderImpl price(double price) {
            commodityBuilderImpl.price = price;
            return this;
        }

        @Override
        public CommodityBuilderImpl residue(int residue) {
            commodityBuilderImpl.residue = residue;
            return this;
        }

        @Override
        public Commodity build() {
            Commodity commodity = commodityBuilderImpl;
            commodityBuilderImpl = new CommodityImpl();
            return commodity;
        }
    }

    @Override
    public String toString() {
        return "" + getName() + "; " + getCode() + "; " + getVendorCode() + "; " + getPrice() + "; " + getResidue();
    }

    public static int compareByPrice(Commodity first, Commodity second) {
        if (first.getPrice() > second.getPrice()) {
            return 1;
        } else if (first.getPrice() == second.getPrice()) {
            return 0;
        }
        return -1;
    }

    public static int compareByResidue(Commodity first, Commodity second) {
        if (first.getResidue() > second.getResidue()) {
            return 1;
        } else if (first.getResidue() == second.getResidue()) {
            return 0;
        }
        return -1;
    }

    public static int compareByNameLength(Commodity first, Commodity second) {
        if (first.getName().length() > second.getName().length()) {
            return 1;
        } else if (first.getName().length() == second.getName().length()) {
            return 0;
        }
        return -1;
    }

    public static int compareByName(Commodity first, Commodity second) {
        return first.getName().compareToIgnoreCase(second.getName());
    }
}
