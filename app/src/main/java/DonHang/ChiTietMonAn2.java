package DonHang;

public class ChiTietMonAn2 {
    private String tenMonAn;
    private long price;
    private byte[] image;
    private int soLuong;

    public ChiTietMonAn2(String tenMonAn, long price, byte[] image, int soLuong) {
        this.tenMonAn = tenMonAn;
        this.price = price;
        this.image = image;
        this.soLuong = soLuong;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
