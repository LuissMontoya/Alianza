package co.com.test.alianza.appClients.service;


import org.springframework.http.ResponseEntity;

import co.com.test.alianza.appClients.DTO.ClientDTO;
import co.com.test.alianza.appClients.DTO.ResponseDTO;

public interface IClientService {
	
	public ResponseEntity<ResponseDTO> getAll();
	public ResponseEntity<ResponseDTO> saveClient(ClientDTO clientDTO);
	public ResponseEntity<ResponseDTO> updateClient(ClientDTO clientDTO);
	public ResponseEntity<ResponseDTO> findClientBySharedKey(String sharedKey);
}
