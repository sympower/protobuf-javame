package net.jarlehansen.proto2javame.domain.java;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class JavaFileOutput {
	private final String packageName;
	private final String className;
    private StringBuilder mainClass = new StringBuilder();
    private StringBuilder enumClasses = new StringBuilder();
	private StringBuilder internalClass = new StringBuilder();
	private StringBuilder publicMethods = new StringBuilder();
	private StringBuilder privateAndProtectedMethods = new StringBuilder();
	private StringBuilder staticMethods = new StringBuilder();

    public JavaFileOutput(final String packageName, final String className) {
        this.packageName = packageName;
        this.className = className;
	}

    public String getPackageName() {
        return packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setMainClass(final StringBuilder mainClass) {
        this.mainClass = mainClass;
    }

    public void setEnumClasses(final StringBuilder enumClasses) {
        this.enumClasses = enumClasses;
    }

    public void setInternalClass(final StringBuilder internalClass) {
        this.internalClass = internalClass;
    }

    public void setPublicMethods(final StringBuilder publicMethods) {
        this.publicMethods = publicMethods;
    }

    public void setPrivateAndProtectedMethods(final StringBuilder privateAndProtectedMethods) {
        this.privateAndProtectedMethods = privateAndProtectedMethods;
    }

    public void setStaticMethods(final StringBuilder staticMethods) {
        this.staticMethods = staticMethods;
    }
    
    public String getCompleteSource() {
        return mainClass.toString() + enumClasses.toString() + internalClass.toString() + publicMethods.toString() +
                privateAndProtectedMethods.toString() + staticMethods.toString();
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("JavaFileOutput");
        sb.append("{packageName='").append(packageName).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", mainClass=").append(mainClass);
        sb.append(", enumClasses=").append(enumClasses);
        sb.append(", internalClass=").append(internalClass);
        sb.append(", publicMethods=").append(publicMethods);
        sb.append(", privateAndProtectedMethods=").append(privateAndProtectedMethods);
        sb.append(", staticMethods=").append(staticMethods);
        sb.append('}');
        return sb.toString();
    }
}
