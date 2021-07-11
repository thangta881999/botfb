package com.TP.api.web;

import com.TP.Respone.ValidRespone;
import com.TP.entity.UserEntity;
import com.TP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/user")
public class UserAPI {
    @Autowired
    private UserService userService;

    @PostMapping
    public ValidRespone DangKy(@RequestBody UserEntity userEntity, BindingResult result){
        ValidRespone response= new ValidRespone();
        if (result.hasErrors()) {

            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {
            response.setValidated(true);
            userService.save(userEntity);
        }
        return response;
    }
    public static final Pattern VAL_ID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VAL_ID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
