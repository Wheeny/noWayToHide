package com.lastma.data.models;

public class Officer {

    private String fullName;
    private String email;
    private int id;
    private String password;
    private boolean isLoggedIn;

    public boolean isLoggedIn() { return isLoggedIn;
    }
    public void setLoggedIn(boolean loggedIn) { isLoggedIn = loggedIn;
    }
    public String getPassword() {return password;
    }
    public void setPassword(String password) {this.password = password;
    }
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}
