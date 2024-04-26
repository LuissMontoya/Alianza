package co.com.test.alianza.appClients.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
		
		if(Objects.isNull(clientDTO.getSharedKey()) || clientDTO.getSharedKey().isEmpty()) {
			clientDTO.setSharedKey(String.valueOf("1"));
			List<Clients> clientes = clientRepository.findAllByOrderBySharedKeyDesc();
			if(!clientes.isEmpty()) {
				Integer valorAnterior = Integer.parseInt(clientes.get(0).getSharedKey());
				clientDTO.setSharedKey(String.valueOf(valorAnterior+1));
			}
		}
		
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
				List<Clients> clientes = new ArrayList<>();
				clientes.add(client.get());
				response = Utils.mapearRespuesta(HttpStatus.OK.name(), HttpStatus.OK.value(),
						clientes);
			
			}else {
				response = Utils.mapearRespuesta(HttpStatus.NOT_FOUND.name(), HttpStatus.NOT_FOUND.value());
			}
			
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			response = Utils.mapearRespuesta(e.getLocalizedMessage(), HttpStatus.CONFLICT.value());
			return new ResponseEntity<ResponseDTO>(response, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

}
