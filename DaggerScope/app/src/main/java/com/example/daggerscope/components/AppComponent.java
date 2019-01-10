package com.example.daggerscope.components;

import com.example.daggerscope.App;
import com.example.daggerscope.modules.AppModule;
import com.example.daggerscope.scopes.AppScope;
import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponent {
//    Person person();

    void inject(App app);
}
