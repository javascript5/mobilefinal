package a59070108.kmitl.ac.th.mobilefinal;

public class FriendModel {
    int id;
    String name, email, phone, website;

    public FriendModel(int id, String name, String email, String phone, String website) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.website = website;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
