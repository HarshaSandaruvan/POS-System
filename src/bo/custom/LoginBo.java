package bo.custom;

import bo.SuperBO;

public interface LoginBo extends SuperBO {
    public boolean checkPassword(String username,String password);

    public String getFullNameByUserName(String userName);
}
