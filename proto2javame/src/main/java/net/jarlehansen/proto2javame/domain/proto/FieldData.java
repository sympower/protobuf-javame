package net.jarlehansen.proto2javame.domain.proto;


/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
public class FieldData {
	private int id;
	private ValidScopes scope;
	private ValidTypes type;
	private String name;
	
	private boolean isList = false;
	private ListImplementation listImpl;

	public FieldData() {
	}

	public void setScope(final ValidScopes scope) {
		this.scope = scope;
	}

	public ValidScopes getScope() {
		return scope;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setType(final ValidTypes type) {
		this.type = type;
	}

	public ValidTypes getType() {
		return type;
	}

	public void setListImpl(final ListImplementation listImpl) {
		this.listImpl = listImpl;
		isList = true;
	}

	public ListImplementation getListImpl() {
		return listImpl;
	}

	public boolean isList() {
		return isList;
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FieldData");
        sb.append("{id=").append(id);
        sb.append(", scope=").append(scope);
        sb.append(", type=").append(type);
        sb.append(", name='").append(name).append('\'');
        sb.append(", isList=").append(isList);
        sb.append(", listImpl=").append(listImpl);
        sb.append('}');
        return sb.toString();
    }
}
