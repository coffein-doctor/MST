package com.caffeinedoctor.beverageservice.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class UriUtil {

    private static String scheme;

    @Value("{app.uri.scheme}")
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public static String buildUri(String path) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .scheme(scheme)
                .path(path)
                .toUriString();
    }

}
