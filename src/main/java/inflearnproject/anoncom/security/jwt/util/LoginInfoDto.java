package inflearnproject.anoncom.security.jwt.util;

import lombok.Data;

@Data
public class LoginInfoDto {
    private Long memberId;
    private String email;
    private String name;
}
