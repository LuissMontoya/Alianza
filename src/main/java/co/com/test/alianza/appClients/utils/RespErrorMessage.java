package co.com.test.alianza.appClients.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespErrorMessage {
	   private  int code;
	   private  String message;
}
