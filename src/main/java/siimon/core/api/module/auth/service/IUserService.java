package siimon.core.api.module.auth.service;

import siimon.core.api.module.auth.dto.UserDto;
import siimon.core.api.module.auth.model.UserModel;
import siimon.core.api.shared.dto.RespDto;

import java.util.List;

public interface IUserService {

	RespDto registerNewUser(UserDto userDto);
	List<UserModel> getAllRegisteredUsers();
	UserModel getRegisteredUser(Integer id);
	RespDto updateUser(Integer id, UserDto userDto, int type);
	RespDto deleteUser(Integer id);

}
