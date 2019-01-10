package com.example.daggerscope.scopes;

import javax.inject.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by Gavin on 16/12/15.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface SubActivityScope {
}
