package co.com.test.alianza.appClients.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.com.test.alianza.appClients.DTO.ClientDTO;
import co.com.test.alianza.appClients.DTO.ResponseDTO;
import co.com.test.alianza.appClients.service.impl.ClientServiceImpl;
import co.com.test.alianza.appClients.utils.Constants;
import co.com.test.alianza.appClients.utils.RespErrorMessage;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/client")
@CrossOrigin(origins = "*", methods = { RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST,
		RequestMethod.PUT })
@RequiredArgsConstructor
public class ClientController {
	
	private final ClientServiceImpl clientServiceImpl;
	
    @ApiResponses(value = {
            @ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
    @PostMapping("/create")
	public ResponseEntity<ResponseDTO> saveClient(@RequestBody ClientDTO client) {
		return this.clientServiceImpl.saveClient(client);
	}
	
    @GetMapping("/search")
    @ApiResponses(value = {
            @ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
    public ResponseEntity<ResponseDTO> findClientBySharedKey(@RequestParam("sharedKey") String sharedKey) {
        return this.clientServiceImpl.findClientBySharedKey(sharedKey);
    }
    
    @GetMapping("/getAll")
    @ApiResponses(value = {
            @ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
    public ResponseEntity<ResponseDTO> getAll() {
    	return this.clientServiceImpl.getAll();
    	}
    
    @PutMapping("/update")
    @ApiResponses(value = {
            @ApiResponse(code = Constants.CODIGO_200, message = Constants.MESG_200, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_400, message = Constants.MESG_400, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_422, message = Constants.MESG_422, response = RespErrorMessage.class),
            @ApiResponse(code = Constants.CODIGO_500, message = Constants.MESG_500, response = RespErrorMessage.class) })
    public ResponseEntity<ResponseDTO> update(@RequestBody ClientDTO client) {
    	return this.clientServiceImpl.updateClient(client);
    	}

}
