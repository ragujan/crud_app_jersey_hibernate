package com.seblacko.rag.configs;

import com.seblacko.rag.util.PackageGrabber;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
public class AppConfig extends ResourceConfig {
    public AppConfig(){
        System.out.println("app is configured");
        packages(PackageGrabber.CONTROLLERS);
        packages(PackageGrabber.MIDDLEWARES);
//        register(MultiPartFeature.class);
        register(DependencyBinder.class);
        register(JspMvcFeature.class);
        property(JspMvcFeature.TEMPLATE_BASE_PATH,"WEB-INF/View");
    }
}
