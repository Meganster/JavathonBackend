package com.javathon.backend.util.validators;

import com.javathon.backend.dao.UserDao;
import com.javathon.backend.model.db.User;
import com.javathon.backend.service.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AuthValidator implements Validator {
    private final UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public AuthValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
        User user = userDao.findByVkId(userDTO.getVkId());
        if (user != null) {
            logger.info("");
            errors.rejectValue("vkId", "403","Already authentificated");
        }
    }
}
