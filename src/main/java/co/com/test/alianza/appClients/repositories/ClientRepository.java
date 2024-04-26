package co.com.test.alianza.appClients.repositories;

import org.springframework.stereotype.Repository;
import co.com.test.alianza.appClients.entity.Clients;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ClientRepository extends JpaRepository<Clients,String>{
	
	public Optional<Clients> findClientBySharedKey(String sharedKey);
}