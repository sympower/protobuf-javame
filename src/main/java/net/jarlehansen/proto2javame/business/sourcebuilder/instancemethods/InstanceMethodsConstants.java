package net.jarlehansen.proto2javame.business.sourcebuilder.instancemethods;

/**
 * 
 * @author hansjar@gmail.com Jarle Hansen
 *
 */
enum InstanceMethodsConstants {
	;
	
	public static final String KEY_PUBLIC_COMPUTESIZE_START = "public.compute.size.start";
	public static final String KEY_PUBLIC_COMPUTESIZE_FIELDS = "public.compute.size.fields";
    public static final String KEY_PUBLIC_COMPUTESIZE_FIELDS_NESTED = "public.compute.size.fields.nested";
	public static final String KEY_PUBLIC_COMPUTESIZE_FIELDS_LIST = "public.compute.size.fields.list";
    public static final String KEY_PUBLIC_COMPUTESIZE_FIELDS_OPTIONAL = "public.compute.size.fields.optional";
	public static final String KEY_PUBLIC_COMPUTESIZE_END = "public.compute.size.end";

    public static final String KEY_PRIVATE_COMPUTEMESSAGESIZE_START = "private.compute.message.size.start";
    public static final String KEY_PRIVATE_COMPUTEMESSAGESIZE_CUSTOMTYPE = "private.compute.message.size.custom.type";
    public static final String KEY_PRIVATE_COMPUTE_MESSAGESIZE_CUSTOMTYPE_LIST = "private.compute.message.size.custom.type.list";
    public static final String KEY_PRIVATE_COMPUTEMESSAGESIZE_CUSTOMTYPE_OPTIONAL = "private.compute.message.size.custom.type.optional";
    public static final String KEY_PRIVATE_COMPUTEMESSAGESIZE_END = "private.compute.message.size.end";

	public static final String KEY_PUBLIC_WRITEFIELDS_START = "public.writefields.start";
	public static final String KEY_PUBLIC_WRITEFIELDS_FIELDS = "public.writefields.fields";
    public static final String KEY_PUBLIC_WRITEFIELDS_FIELDS_NESTED = "public.writefields.fields.nested";
	public static final String KEY_PUBLIC_WRITEFIELDS_FIELDS_LIST = "public.writefields.fields.list";
    public static final String KEY_PUBLIC_WRITEFIELDS_FIELDS_LIST_NESTED = "public.writefields.fields.list.nested";
    public static final String KEY_PUBLIC_WRITEFIELDS_FIELDS_OPTIONAL = "public.writefields.fields.optional";
    public static final String KEY_PUBLIC_WRITEFIELDS_FIELDS_OPTIONAL_NESTED = "public.writefields.fields.optional.nested";
    public static final String KEY_PUBLIC_WRITEFIELDS_END = "public.writefields.end";
	
	public static final String KEY_PACKAGEPRIVATE_STATIC_PARSEFIELDS = "packageprotected.static.parsefields";

    public static final String KEY_PACKAGEPRIVATE_STATIC_GETNEXTFIELDNUMBER = "packageprotected.static.getnextfieldnumber";

    public static final String KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_START = "packageprotected.static.populatewithfield.start";
    public static final String KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_FIELDS = "packageprotected.static.populatewithfield.fields";
    public static final String KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_FIELDS_LIST = "packageprotected.static.populatewithfield.fields.list";
    public static final String KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_FIELDS_NESTED = "packageprotected.static.populatewithfield.fields.nested";
    public static final String KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_FIELDS_LIST_NESTED = "packageprotected.static.populatewithfield.fields.list.nested";
    public static final String KEY_PACKAGEPRIVATE_STATIC_POPULATEWITHFIELD_END = "packageprotected.static.populatewithfield.end";
}
