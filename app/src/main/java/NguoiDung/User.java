package NguoiDung;

public class User {
    private String name;
    private String sdt;
    private String email;
    private String online;

    public User(String name, String sdt, String email, String online) {
        this.name = name;
        this.sdt = sdt;
        this.email = email;
        this.online = online;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
}
