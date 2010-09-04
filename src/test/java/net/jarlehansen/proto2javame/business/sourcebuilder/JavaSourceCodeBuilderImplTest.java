package net.jarlehansen.proto2javame.business.sourcebuilder;

import net.jarlehansen.proto2javame.domain.java.JavaFileOutput;
import net.jarlehansen.proto2javame.testutils.TestConstants;
import net.jarlehansen.proto2javame.testutils.TestObjectFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author hansjar@gmail.com Jarle Hansen
 */
public class JavaSourceCodeBuilderImplTest {
    private JavaSourceCodeBuilder javaSourceBuilder;

    @Before
    public void setUp() {
        javaSourceBuilder = TestObjectFactory.createJavaSourceCodeBuilder();
    }

    @Test
    public void testCreateJavaSourceCode() {
        assertThat(javaSourceBuilder.createJavaSourceCode(TestObjectFactory.createTestProtoFileInputList()).get(0),
                is(JavaFileOutput.class));
    }

    @Test
    public void testCreateJavaSourceCodeAssertClassName() {
        final List<JavaFileOutput> javaOutputList = javaSourceBuilder.createJavaSourceCode(TestObjectFactory
                .createTestProtoFileInputList());
        assertEquals(TestConstants.PROTO_CLASS_NAME, javaOutputList.get(0).getClassName());
    }

    @Test
    public void testCreateJavaSourceCodeAssertSource() {
        final List<JavaFileOutput> javaOutputList = javaSourceBuilder.createJavaSourceCode(TestObjectFactory
                .createTestProtoFileInputList());
        assertTrue(javaOutputList.get(0).getCompleteSource().length() > 0);
    }
}
