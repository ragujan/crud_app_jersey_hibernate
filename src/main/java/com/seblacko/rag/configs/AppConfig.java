package com.seblacko.rag.configs;

import com.seblacko.rag.util.PackageGrabber;
import org.glassfish.jersey.server.ResourceConfig;

public class AppConfig extends ResourceConfig {
    public AppConfig(){
        System.out.println("app is configured");
        packages(PackageGrabber.CONTROLLERS);
        packages(PackageGrabber.MIDDLEWARES);
        register(DependencyBinder.class);
    }
}
