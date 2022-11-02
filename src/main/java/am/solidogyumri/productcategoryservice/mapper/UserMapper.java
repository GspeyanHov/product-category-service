package am.solidogyumri.productcategoryservice.mapper;

import am.solidogyumri.productcategoryservice.dto.UserCreateDto;
import am.solidogyumri.productcategoryservice.dto.UserDto;
import am.solidogyumri.productcategoryservice.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserCreateDto userCreateDto);

    UserDto map(User user);


}
