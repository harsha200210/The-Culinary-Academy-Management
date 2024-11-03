package lk.ijse.bo.custom.impl;

import lk.ijse.bo.custom.LoginBO;
import lk.ijse.dao.DAOFactory;
import lk.ijse.dao.custom.UserDAO;
import lk.ijse.dto.UserDTO;
import lk.ijse.entity.User;

public class LoginBOImpl implements LoginBO {

    UserDAO userDAO = (UserDAO) DAOFactory.getDAO(DAOFactory.DAOType.USER);

    @Override
    public UserDTO getUser(String userName){
        User user = userDAO.getUser(userName);
        if (user == null){
            return null;
        }
        return new UserDTO(user.getUserId(),user.getUserName(),user.getPassword(),user.getRole());
    }
}
