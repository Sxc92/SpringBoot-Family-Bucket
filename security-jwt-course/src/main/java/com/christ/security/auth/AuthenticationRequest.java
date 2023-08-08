package com.christ.security.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 史偕成
 * @date 2023/08/07 22:22
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {


    private String email, password;

}
