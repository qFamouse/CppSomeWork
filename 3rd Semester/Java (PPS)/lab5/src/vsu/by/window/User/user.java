package vsu.by.window.User;

import java.io.*;
import java.util.ArrayList;

public class user {
    // Input data
    private String username;
    private String password;
    private static String rank;
    // Data
    private static boolean dataIsLoaded = false;
    private static ArrayList<Data> dataBase = new ArrayList<Data>();
    private boolean isLogin = false;

    static boolean LoadData(){
        try{
            Reader reader = new FileReader("src/vsu/by/window/User/data.csv");
            BufferedReader buffReader = new BufferedReader(reader);
            String[] UsernamePassword;
            while(buffReader.ready()){
                UsernamePassword = buffReader.readLine().split(";");
                if (UsernamePassword.length == 3){
                    final String RankList = "login teacher admin";
                    if (RankList.contains(UsernamePassword[2]))
                    dataBase.add(new Data(UsernamePassword[0], UsernamePassword[1], UsernamePassword[2]));
                }
            }
            dataIsLoaded = true;
            System.out.println("dada loaded");
            buffReader.close();
            return true;
        }catch (FileNotFoundException e){
            System.out.println("DataBase is not found!");
        }catch (IOException e){
            System.out.println("Error input/output");
        }
        return false;
    }
    // // // // CONSTRUCTOR // // // // //
    public user(){
        LoadData();
    }
    // // // // PUBLIC METHODS // // // // //
    public String getRank(){
        return rank;
    }

    public boolean getDataStatus(){
        return dataIsLoaded;
    }

    public void SetUsername(String username){
        this.username = username;
    }

    public void SetPassword(String password){
        this.password = password;
    }

    public boolean isLogin(){
        return isLogin;
    }

    public boolean isUser(){
        System.out.println(dataBase.size());
        System.out.println(username);
        System.out.println(password);
        if (username.isEmpty() || password.isEmpty()){ return false; }
        for (Data data : dataBase){
            if (username.equals(data.getUsername())){
                if (password.equals(data.getPassword())){
                    rank = data.getRank();
                    isLogin = true;
                    return true;
                }
            }
        }
        isLogin = false;
        return false;
    }
}
