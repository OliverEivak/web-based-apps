package ee.ttu.olivereivak.veebirakex2.resource;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

import ee.ttu.olivereivak.veebirakex2.entity.Authentication;
import ee.ttu.olivereivak.veebirakex2.resource.filter.ApplicationPrincipal;

import lombok.extern.slf4j.Slf4j;

/**
 * Extend this to have resources automatically bound in ApplicationModule.
 */

@Slf4j
public class BaseResource {

    @Context
    private SecurityContext securityContext;

    protected Authentication getAuthentication() {
        ApplicationPrincipal applicationPrincipal = (ApplicationPrincipal) securityContext.getUserPrincipal();
        Authentication authentication = null;

        if (applicationPrincipal != null) {
            authentication = applicationPrincipal.getAuthentication();
        }

        return authentication;
    }

}
