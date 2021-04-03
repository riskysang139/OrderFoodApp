package YeuThich;

public class ChiTietMonAn1 {
    private String tenMonAn;
    private byte[] image;

    public ChiTietMonAn1(String tenMonAn, byte[] image) {
        this.tenMonAn = tenMonAn;
        this.image = image;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
