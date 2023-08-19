package com.seblacko.rag.middlewares;

import com.seblacko.rag.services.UserService;
import jakarta.annotation.Priority;
//import jakarta.inject.Inject;
import jakarta.inject.Inject;
import org.glassfish.hk2.api.PerLookup;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(1)
public class JwtValidationFilter implements ContainerRequestFilter {
    @Inject
   private UserService userService;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
          String path = containerRequestContext.getUriInfo().getPath();

          if(path.equals("admin_auth") || path.equals("refresh_token")){
              return;
          }
          if(containerRequestContext.getHeaders().getFirst("Authorization") == null){
              System.out.println("Response is unauthorized");
          }else{
              String authorizationToken = containerRequestContext.getHeaders().getFirst("Authorization").split(" ")[1];

          }
    }
}
