package net.jarlehansen.proto2javame.modules.io;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import net.jarlehansen.proto2javame.io.protoinput.ProtoObjectBuilder;
import net.jarlehansen.proto2javame.io.protoinput.ProtoObjectBuilderImpl;
import net.jarlehansen.proto2javame.io.protoinput.ProtoParser;
import net.jarlehansen.proto2javame.io.protoinput.enums.EnumParser;
import net.jarlehansen.proto2javame.io.protoinput.enums.EnumValueParser;
import net.jarlehansen.proto2javame.io.protoinput.factory.ProtoParserFactory;
import net.jarlehansen.proto2javame.io.protoinput.factory.ProtoParserFactoryImpl;
import net.jarlehansen.proto2javame.io.protoinput.fields.FieldParser;
import net.jarlehansen.proto2javame.io.protoinput.message.MessageParser;
import net.jarlehansen.proto2javame.io.protoinput.options.OptionParser;
import net.jarlehansen.proto2javame.io.sourceoutput.SourceFileWriter;
import net.jarlehansen.proto2javame.io.sourceoutput.SourceFileWriterImpl;

/**
 * User: Jarle Hansen (hansjar@gmail.com)
 * Date: Aug 30, 2010
 * Time: 7:22:40 PM
 */
public class IoModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ProtoObjectBuilder.class).to(ProtoObjectBuilderImpl.class);
        bind(SourceFileWriter.class).to(SourceFileWriterImpl.class);

        bind(ProtoParserFactory.class).to(ProtoParserFactoryImpl.class);
        // Proto-files parsers
        bind(ProtoParser.class).annotatedWith(Names.named("FieldParser")).to(FieldParser.class);
        bind(ProtoParser.class).annotatedWith(Names.named("MessageParser")).to(MessageParser.class);
        bind(ProtoParser.class).annotatedWith(Names.named("OptionParser")).to(OptionParser.class);
        bind(ProtoParser.class).annotatedWith(Names.named("EnumParser")).to(EnumParser.class);
        bind(ProtoParser.class).annotatedWith(Names.named("EnumValueParser")).to(EnumValueParser.class);
    }

}
