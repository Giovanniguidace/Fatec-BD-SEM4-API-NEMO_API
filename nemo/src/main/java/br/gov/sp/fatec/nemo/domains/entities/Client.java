package br.gov.sp.fatec.nemo.domains.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;

        Client client = (Client) o;

        if (getClientId() != null ? !getClientId().equals(client.getClientId()) : client.getClientId() != null)
            return false;
        return getClientSecret() != null ? getClientSecret().equals(client.getClientSecret()) : client.getClientSecret() == null;
    }

    @Override
    public int hashCode() {
        int result = getClientId() != null ? getClientId().hashCode() : 0;
        result = 31 * result + (getClientSecret() != null ? getClientSecret().hashCode() : 0);
        return result;
    }
}
