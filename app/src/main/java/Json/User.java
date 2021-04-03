package Json;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String userLoginID;
    private String passWord;
    private String userName;

    public User(int userID, String userLoginID, String passWord, String userName) {
        this.userID = userID;
        this.userLoginID = userLoginID;
        this.passWord = passWord;
        this.userName = userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserLoginID() {
        return userLoginID;
    }

    public void setUserLoginID(String userLoginID) {
        this.userLoginID = userLoginID;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
