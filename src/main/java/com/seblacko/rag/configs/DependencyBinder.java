package com.seblacko.rag.configs;

import com.seblacko.rag.services.UserService;
import com.seblacko.rag.util.JwtTokenUtil;
import com.seblacko.rag.util.UserDetails;
import jakarta.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class DependencyBinder extends AbstractBinder {

    @Override
    protected void configure() {
        bind(UserService.class).to(UserService.class).in(Singleton.class);
        bind(JwtTokenUtil.class).to(JwtTokenUtil.class).in(Singleton.class);
    }
}
