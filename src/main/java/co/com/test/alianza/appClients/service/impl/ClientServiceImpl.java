package co.com.test.alianza.appClients.service.impl;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.com.test.alianza.appClients.DTO.ClientDTO;
import co.com.test.alianza.appClients.DTO.ResponseDTO;
import co.com.test.alianza.appClients.entity.Clients;
import co.com.test.alianza.appClients.mapper.ClientMapper;
import co.com.test.alianza.appClients.repositories.ClientRepository;
import co.com.test.alianza.appClients.service.IClientService;
import co.com.test.alianza.appClients.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements IClientService{
	
	private final ClientRepository clientRepository;
	
	@Override
	public ResponseEntity<ResponseDTO> saveClient(ClientDTO clientDTO) {
		log.info("saveClient "+ clientDTO);
		ResponseDTO response = null;
		try {
				response = Utils.mapearRespuesta(HttpStatus.CREATED.name(), HttpStatus.CREATED.value(),
						this.clientRepository.save(ClientMapper.INSTANCE.dtoToEntity(clientDTO)));
			
		} catch (Exception e) {
			//log.error(e.getLocalizedMessage());
			response = Utils.mapearRespuesta(e.getLocalizedMessage(), HttpStatus.CONFLICT.value());
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseDTO> getAll() {
		log.info("getAll ");
		ResponseDTO response = null;
		try {
				response = Utils.mapearRespuesta(HttpStatus.OK.name(), HttpStatus.OK.value(),
						ClientMapper.INSTANCE.listBeanToListDto(this.clientRepository.findAll()));
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			response = Utils.mapearRespuesta(e.getLocalizedMessage(), HttpStatus.CONFLICT.value());
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseDTO> updateClient(ClientDTO clientDTO) {
		log.info("updateClient "+clientDTO);
		ResponseDTO response = null;
		Optional<Clients> clientOptional = this.clientRepository.findById(clientDTO.getSharedKey());
		try {
			if(clientOptional.isPresent()) {
				response = Utils.mapearRespuesta(HttpStatus.OK.name(), HttpStatus.OK.value(),
						this.clientRepository.save(ClientMapper.INSTANCE.dtoToEntity(clientDTO)));
			}
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			response = Utils.mapearRespuesta(e.getLocalizedMessage(), HttpStatus.CONFLICT.value());
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseDTO> findClientBySharedKey(String sharedKey) {
		log.info("findClientBySharedKey "+sharedKey);
		ResponseDTO response = null;
		Optional<Clients> client = this.clientRepository.findById(sharedKey);
		try {
			if(client.isPresent()) {
				response = Utils.mapearRespuesta(HttpStatus.OK.name(), HttpStatus.OK.value(),
						client);
			
			}else {
				response = Utils.mapearRespuesta(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value(),
						client);
			}
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			response = Utils.mapearRespuesta(e.getLocalizedMessage(), HttpStatus.CONFLICT.value());
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

}
