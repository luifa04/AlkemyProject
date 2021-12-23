package com.alkemy.ong.security.dto;

import com.alkemy.ong.util.docs.AuthenticationConstantDocs;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoggedUserDto {

	@ApiModelProperty(value = AuthenticationConstantDocs.AUTHENTICATION_TOKEN)
	private String token;
	
}
