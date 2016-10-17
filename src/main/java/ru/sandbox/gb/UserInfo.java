package ru.sandbox.gb;

/**
 * Created by Горбач on 17.10.2016.
 */
public class UserInfo{
    private String userName="";
    private String password = "";
    private boolean loginFlag = false;

    public UserInfo(){}

    public void setUserName(String name){ userName=name; }
    public void setLoginFlag(Boolean flag){ loginFlag=flag; }
    public void setPassword(String pass){ password=pass; }

    public String getUser(){ return userName; }
    public boolean getLoginFlag(){ return loginFlag; }
    public String getPassword(){ return password; }

    public void login(String TheUser){
        loginFlag = true;
        userName = TheUser;
    }

    public void Logout(){
        loginFlag = false;
        userName = "";
        password = "";
    }
}
