package dang.fileDB.sample;

import java.io.Serializable;

/**
 * Description: 用户对象
 *
 * @Date Create in 2017/12/15
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String email;

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

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + id;
        if (name != null) {
            result = result * 31 + name.hashCode();
        }
        if (email != null) {
            result = result * 31 + email.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof User) {
            User user = (User) obj;
            boolean result = id == user.getId();
            result &= (name == null ? user.getName() == null : name.equals(user.getName()));
            result &= (email == null ? user.getEmail() == null : email.equals(user.getEmail()));
            return result;
        }
        return false;
    }
}
