package net.jarlehansen.proto2javame.modules;

import com.google.inject.Guice;
import com.google.inject.Injector;
import net.jarlehansen.proto2javame.modules.business.CodeGeneratorModule;
import net.jarlehansen.proto2javame.modules.io.IoModule;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Aug 30, 2010
 * Time: 6:25:43 PM
 */
public enum DependencyInjector {
    MAIN;

    private final Injector injector;

    private DependencyInjector() {
        injector = Guice.createInjector(new CodeGeneratorModule(), new IoModule());
    }

    public <T> T getInstance(final Class<T> clazz) {
        return injector.getInstance(clazz);
    }
}
