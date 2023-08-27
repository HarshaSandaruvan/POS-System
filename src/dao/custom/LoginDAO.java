package dao.custom;

import dao.SuperDAO;

public interface LoginDAO extends SuperDAO {
    public String getPasswordByUsername(String userName);
    public String getFullNameByUserName(String userName);
}
