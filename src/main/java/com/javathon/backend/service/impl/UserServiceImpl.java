package com.javathon.backend.service.impl;

import com.javathon.backend.dao.UserDao;
import com.javathon.backend.model.db.User;
import com.javathon.backend.model.db.events.impl.EventMessage;
import com.javathon.backend.security.Interceptors.MainInterceptor;
import com.javathon.backend.service.dto.MessageDTO;
import com.javathon.backend.service.dto.UserDTO;
import com.javathon.backend.service.interf.UserService;
import com.javathon.backend.util.MessageConverter;
import com.javathon.backend.util.RandomString;
import com.javathon.backend.util.UniversalResponse;
import com.javathon.backend.util.UserConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;
    private final MainInterceptor mainInterceptor;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, MainInterceptor mainInterceptor) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.mainInterceptor = mainInterceptor;
    }
    @Override
    public void saveUser(User user) {
        userDao.save(user);
    }

    @Override
    public User getUserByVkId(long id) {
        return userDao.findByVkId(id);
    }

    @Override
    public String getToken() {
        return passwordEncoder.encode(UUID.randomUUID().toString());
    }

    @Override
    public UniversalResponse updatePosition(UserDTO userDTO) {
        UniversalResponse universalResponse = new UniversalResponse();
        //Update user
        User user = userDao.findByVkId(userDTO.getVkId());
        user.setLastLatitude(userDTO.getLastLatitude());
        user.setLastLongitude(userDTO.getLastLongitude());
        user.setLastSeenDate(LocalDateTime.now());
        userDao.save(user);
        //Send friends position

        user.getFriend().forEach((vkId, friend) -> {
            if(friend.isVisible()) {
                universalResponse.getFriends().put(vkId, new UserDTO.Builder(friend).setDefault_config().build());
            }
        });

        universalResponse.setSuccess(true);
        return universalResponse;
    }

    @Override
    public UniversalResponse getFriendPosition(long friend_id) {
        UniversalResponse universalResponse = new UniversalResponse();
        User user = userDao.findUserByToken(mainInterceptor.getToken());
        User friend = user.getFriend().get(friend_id);
        if(friend == null && !friend.isVisible()) {
            universalResponse.setSuccess(false);
            return universalResponse;
        }
        UserDTO userDTO = new UserDTO.Builder(friend).setDefault_config().build();
        universalResponse.setFriend(userDTO);
        universalResponse.setSuccess(true);
        return universalResponse;
    }

    @Override
    public UniversalResponse setVisible(boolean isVisible) {
        User user = userDao.findUserByToken(mainInterceptor.getToken());
        user.setVisible(isVisible);
        userDao.save(user);
        return UniversalResponse.OK();
    }

    @Override
    public UniversalResponse addFriend(UserDTO userDTO) {
        User user = userDao.findUserByToken(mainInterceptor.getToken());
        User friend = userDao.findByVkId(userDTO.getVkId());
        if(friend == null && user.getFriend().containsKey(friend.getVkId())){
            return UniversalResponse.BAD();
        }
        user.getFriend().put(userDTO.getVkId(), UserConverter.convertUserDTOToUser(userDTO));
        userDao.save(user);
        return UniversalResponse.OK();
    }

    @Override
    public UniversalResponse createMessage(MessageDTO messageDTO) {
        User user = userDao.findUserByToken(mainInterceptor.getToken());
        EventMessage eventMessage = MessageConverter.convertMessageDTOToEventMessage(messageDTO);
        eventMessage.setAuthor(user);
        userDao.save(user);
        return UniversalResponse.OK();
    }

    @Override
    public UniversalResponse getFriendsMessages(){
        User user = userDao.findUserByToken(mainInterceptor.getToken());
        UniversalResponse universalResponse = new UniversalResponse();
        if(!user.isVisibleMessage()){
           return UniversalResponse.BAD();
        }
        user.getFriend().forEach((key, friend) -> {
            List<MessageDTO> messages = new ArrayList<>();
            if(friend.getMessages().size() != 0) {
                friend.getMessages().forEach(eventMessage -> {
                    messages.add(new MessageDTO.Builder(eventMessage).setDefault_config().build());
                });
            }
            universalResponse.getMessages().put(key, messages);
        });
        return universalResponse;
    }

    @Override
    public String getShortToken() {
        return RandomString.getShortTokenString();
    }
}
