package Json;

public class MonAn {
    private int Id;
    private String tenMonAn;
    private String giaMonAn;
    private byte[] imageMonAn;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getGiaMonAn() {
        return giaMonAn;
    }

    public void setGiaMonAn(String giaMonAn) {
        this.giaMonAn = giaMonAn;
    }

    public byte[] getImageMonAn() {
        return imageMonAn;
    }

    public void setImageMonAn(byte[] imageMonAn) {
        this.imageMonAn = imageMonAn;
    }

    public MonAn(int id, String tenMonAn, String giaMonAn, byte[] imageMonAn) {
        Id = id;
        this.tenMonAn = tenMonAn;
        this.giaMonAn = giaMonAn;
        this.imageMonAn = imageMonAn;
    }
}
