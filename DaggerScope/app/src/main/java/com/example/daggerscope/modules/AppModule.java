package com.example.daggerscope.modules;

import android.util.Log;
import com.example.daggerscope.entity.Person;
import com.example.daggerscope.scopes.AppScope;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    @AppScope
    @Provides
    Person providePerson() {
        Log.e(">>>", "provide Person");
        return new Person(18, "Bob");
    }

}
