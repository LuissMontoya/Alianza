package co.com.test.alianza.appClients.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import co.com.test.alianza.appClients.DTO.ClientDTO;
import co.com.test.alianza.appClients.entity.Clients;

@Mapper
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
	
	ClientDTO entityToDTO(Clients entity);
	
	@InheritInverseConfiguration 
	Clients dtoToEntity(ClientDTO clientDTO);
	
	List<ClientDTO> listBeanToListDto(List<Clients> list);
	
	List<Clients> listDtoToListEnt(List<ClientDTO> list);
}
