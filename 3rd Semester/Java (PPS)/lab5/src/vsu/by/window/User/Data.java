package vsu.by.window.User;

public class Data {
    private String username;
    private String password;
    private String rank;

    Data(String username, String password, String rank){
        this.username = username;
        this.password = password;
        this.rank = rank;
    }
    // Геттеры
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getRank(){
        return rank;
    }
    // Сеттеры
    public void setUsername(String newUsername){
        username = newUsername;
    }
    public void setPassword(String newPassword){
        password = newPassword;
    }
}
