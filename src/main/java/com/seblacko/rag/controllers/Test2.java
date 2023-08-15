package com.seblacko.rag.controllers;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api")
public class Test2 {
   @GET
   @Path("/test2")
   public String viewTest(){
       return "This is test view 2";
   }
}
