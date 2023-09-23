package com.kumarprem.AngularSpringBoot.restImpl;

import com.kumarprem.AngularSpringBoot.constant.CafeConstant;
import com.kumarprem.AngularSpringBoot.rest.UserRest;
import com.kumarprem.AngularSpringBoot.service.UserService;
import com.kumarprem.AngularSpringBoot.utils.CafeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserRestImpl implements UserRest {

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMap) {
        try{

            return userService.signUp(requestMap);
        }catch (Exception ex){

            ex.printStackTrace();

        }

        return CafeUtils.getResponseEntity(CafeConstant.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
