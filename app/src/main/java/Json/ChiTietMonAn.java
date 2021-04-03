package Json;

public class    ChiTietMonAn {
    private String tenMonAn;
    private String chiTietMon;
    private String price;
    private String image;

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getChiTietMon() {
        return chiTietMon;
    }

    public void setChiTietMon(String chiTietMon) {
        this.chiTietMon = chiTietMon;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ChiTietMonAn(String tenMonAn, String chiTietMon, String price, String image) {
        this.tenMonAn = tenMonAn;
        this.chiTietMon = chiTietMon;
        this.price = price;
        this.image = image;
    }
}
