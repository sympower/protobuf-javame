package net.jarlehansen.proto2javame.domain.java;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class JavaFileOutput {
	private final String className;
	private final String packageName;
	
	private StringBuilder mainClass = new StringBuilder();
	private StringBuilder internalClass = new StringBuilder();
	private StringBuilder publicMethods = new StringBuilder();
	private StringBuilder privateMethods = new StringBuilder();
	private StringBuilder staticMethods = new StringBuilder();

	public JavaFileOutput(final String className, final String packageName) {
		this.className = className;
		this.packageName = packageName;
	}
	
	public void setMainClass(final StringBuilder mainClass) {
		this.mainClass = mainClass;
	}


	public void setInternalClass(final StringBuilder internalClass) {
		this.internalClass = internalClass;
	}


	public void setPublicMethods(final StringBuilder publicMethods) {
		this.publicMethods = publicMethods;
	}


	public void setPrivateMethods(final StringBuilder privateMethods) {
		this.privateMethods = privateMethods;
	}


	public void setStaticMethods(final StringBuilder staticMethods) {
		this.staticMethods = staticMethods;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public String getClassName() {
		return className;
	}

	public String getCompleteSource() {
		return mainClass.toString() + internalClass.toString() + publicMethods.toString() + privateMethods.toString()
				+ staticMethods.toString();
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("JavaFileOutput");
        sb.append("{className='").append(className).append('\'');
        sb.append(", packageName='").append(packageName).append('\'');
        sb.append(", mainClass=").append(mainClass);
        sb.append(", internalClass=").append(internalClass);
        sb.append(", publicMethods=").append(publicMethods);
        sb.append(", privateMethods=").append(privateMethods);
        sb.append(", staticMethods=").append(staticMethods);
        sb.append('}');
        return sb.toString();
    }
}
