package ai.ecma.organization.projection;


import ai.ecma.organization.entity.Measurement;
import ai.ecma.organization.entity.Organization;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "customOrganization", types = {Organization.class, Measurement.class})
public interface CustomOrganization extends CustomProjection {

    String getTin();

    String getAddress();

    String getAccountNumber();


}
