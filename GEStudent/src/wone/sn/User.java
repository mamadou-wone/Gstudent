package wone.sn;

public class User {
    private int ID;
    private String login;
    private String password;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int ID, String login, String password) {
        this.ID = ID;
        this.login = login;
        this.password = password;
    }
}
