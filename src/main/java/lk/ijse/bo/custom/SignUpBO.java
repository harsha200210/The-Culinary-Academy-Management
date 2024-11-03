package lk.ijse.bo.custom;

import lk.ijse.bo.SuperBO;
import lk.ijse.dto.UserDTO;

public interface SignUpBO extends SuperBO {

    void signUp(UserDTO userDTO);
}
