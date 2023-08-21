package com.seblacko.rag.middlewares;

import com.seblacko.rag.services.UserService;
import com.seblacko.rag.util.JwtTokenUtil;
import com.seblacko.rag.util.UserDetails;
import io.fusionauth.jwt.JWTExpiredException;
import jakarta.annotation.Priority;
//import jakarta.inject.Inject;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

@Provider
@Priority(1)
public class JwtValidationFilter implements ContainerRequestFilter {
    @Inject
   private UserService userService;
    @Inject
    private JwtTokenUtil tokenUtil;
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
          String path = containerRequestContext.getUriInfo().getPath();

          if(path.equals("admin_auth") || path.equals("refresh_token")){
              return;
          }
          if(containerRequestContext.getHeaders().getFirst("Authorization") == null){
              System.out.println("Response is unauthorized");
              containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("No Authorization header is found").build());
          }else{

              String authorizationToken = containerRequestContext.getHeaders().getFirst("Authorization").split(" ")[1];
              System.out.println("authorization token "+authorizationToken);
              try {
                  UserDetails userDetails = userService.getAdminByEmailPassword(tokenUtil.getUsernameFromToken(authorizationToken),"admin");
              }catch (JWTExpiredException| NullPointerException ex){
                  ex.printStackTrace();
                  containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Token Expired").build());
              }catch (Exception ex){
                  ex.printStackTrace();
                  containerRequestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity("Problem with token or something").build());
              }
          }
    }
}
