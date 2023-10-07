package utilities;

public class ClassHelper {
    public static class Product {
        public String productName;
        private String price;

        public Product(String productName, String price) {
            this.productName = productName;
            this.price = price;
        }

        public String getProductName() {
            return productName;
        }

        public String getPrice() {
            return price;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public void setPrice(String price) {
            this.price = price;
        }
    }

}
