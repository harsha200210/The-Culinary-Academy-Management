package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

public interface LoginBO extends SuperBO {

    UserDTO getUser(String userName);
}
