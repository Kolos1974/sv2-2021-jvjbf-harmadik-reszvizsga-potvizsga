package contentsite;

import java.util.Objects;

public class User {
    private String userName;
    private int password;
    private boolean logIn;
    private boolean premiumMember;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = this.hashCode();
    }

    public String getUserName() {
        return userName;
    }

    public int getPassword() {
        return password;
    }

    public boolean isLogIn() {
        return logIn;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }


    public void setLogIn(boolean value) {
        this.logIn = value;
        this.premiumMember = true;
    }

    public void upgradeForPremium() {
        this.logIn = true;
        this.premiumMember = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userName, user.userName) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }
}
