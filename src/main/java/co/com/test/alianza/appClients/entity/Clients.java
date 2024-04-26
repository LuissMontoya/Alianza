package co.com.test.alianza.appClients.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Clients {
	
	 @Id
	 @Column(name = "Shared_key")
	 private String sharedKey;
	 
	 @Column(name = "Business_id")
	 private String businessId;

	 @Column(name = "Email")
	 private String email;

	 @Column(name = "Phone")
	 private String phone;

	 @Column(name = "Data_Added")
	 private Date dataAdded;

	 @CreationTimestamp
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "date_creation", nullable = false, updatable = false)
	 private Date dateCreation;

	 @UpdateTimestamp
	 @Temporal(TemporalType.TIMESTAMP)
	 @Column(name = "date_update", nullable = false)
	 private Date dateUpdate;
	    
}
