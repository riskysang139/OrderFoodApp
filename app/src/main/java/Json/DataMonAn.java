package Json;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataMonAn implements Serializable {
   public String tenMon;
    public String price;
    public String linkImage;

    public DataMonAn(String tenMon, String price, String linkImage) {
        this.tenMon = tenMon;
        this.price = price;
        this.linkImage = linkImage;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }
}
