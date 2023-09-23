package com.kumarprem.AngularSpringBoot.serviceImpl;

import com.kumarprem.AngularSpringBoot.POJO.User;
import com.kumarprem.AngularSpringBoot.constant.CafeConstant;
import com.kumarprem.AngularSpringBoot.dao.UserDao;
import com.kumarprem.AngularSpringBoot.service.UserService;
import com.kumarprem.AngularSpringBoot.utils.CafeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {

        log.info("inside signup {}",requestMap);
       try {
           if(validateSignUp(requestMap)){

               User user = userDao.findByEmail(requestMap.get("email"));
               if(Objects.isNull(user)){

                   userDao.save(getUserFromMap(requestMap));
                   return CafeUtils.getResponseEntity("Successfully Registered",HttpStatus.CREATED);
               }else{
                   return CafeUtils.getResponseEntity("Email already exist",HttpStatus.BAD_REQUEST);
               }

           }
           else {
               return CafeUtils.getResponseEntity(CafeConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
           }
       }catch (Exception ex){
           ex.printStackTrace();
       }

       return CafeUtils.getResponseEntity(CafeConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUp(Map<String,String> requestMap){
        if(requestMap.containsKey("name") && requestMap.containsKey("contactNumber")
                && requestMap.containsKey("email") && requestMap.containsKey("password")){
            return true;
        }else{
            return false;
        }
    }

    private User  getUserFromMap(Map<String,String> requestMap){
        User user = new User();
        user.setName(requestMap.get("name"));
        user.setContactNumber(requestMap.get("contactNumber"));
        user.setEmail(requestMap.get("email"));
        user.setPassword(requestMap.get("password"));
        user.setStatus("false");
        user.setRole("user");
        return user;

    }

}
