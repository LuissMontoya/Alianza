package co.com.test.alianza.appClients.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
	 private String sharedKey;
	 private String businessId;
	 private String email;
	 private String phone;
	 private Date dataAdded;
}
