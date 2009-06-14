package net.jarlehansen.proto2javame.generated.sourcecode.tests;

import net.jarlehansen.proto2javame.generated.sourcecode.tests.tempfiles.JunitTestLargeObject;
import net.jarlehansen.protobuf.javame.ByteString;

/**
 * @author Jarle Hansen hansjar@gmail.com
 *         Date: May 14, 2009
 */
public enum LargeObjectConstants {
    ;

    public static JunitTestLargeObject createTestObjOnlyRequiredFields() {
        return JunitTestLargeObject.newBuilder().setByteStringObj(REQ_BYTESTRINGOBJ).
                setAmount(REQ_AMOUNT).setBoolVal(REQ_BOOLVAL).setFloatNum(REQ_FLOATNUM).
                setId(REQ_ID).setName(REQ_NAME).setNumber2(REQ_NUMBER2).build();
    }

    public static JunitTestLargeObject createTestObjRequiredAndOptionalFields() {
        return JunitTestLargeObject.newBuilder().setByteStringObj(REQ_BYTESTRINGOBJ).
                setAmount(REQ_AMOUNT).setBoolVal(REQ_BOOLVAL).setFloatNum(REQ_FLOATNUM).
                setId(REQ_ID).setName(LargeObjectConstants.REQ_NAME).setNumber2(REQ_NUMBER2).
                setAmountOptional(OPT_AMOUNTOPTIONAL).setBoolValOptional(OPT_BOOLVALOPTIONAL)
                .setByteStringObjOptional(OPT_BYTESTRINGOBJOPTIONAL).setFloatNumOptional(OPT_FLOATNUMOPTIONAL)
                .setIdOptional(OPT_IDOPTIONAL).setNameOptional(OPT_NAMEOPTIONAL)
                .setNumber2Optional(OPT_NUMBER2OPTIONAL).build();
    }

    public static JunitTestLargeObject createTestObjAllFields() {
        return JunitTestLargeObject.newBuilder().setByteStringObj(REQ_BYTESTRINGOBJ).
                setAmount(REQ_AMOUNT).setBoolVal(REQ_BOOLVAL).setFloatNum(REQ_FLOATNUM).
                setId(REQ_ID).setName(REQ_NAME).setNumber2(REQ_NUMBER2).
                setAmountOptional(OPT_AMOUNTOPTIONAL).setBoolValOptional(OPT_BOOLVALOPTIONAL)
                .setByteStringObjOptional(OPT_BYTESTRINGOBJOPTIONAL).setFloatNumOptional(OPT_FLOATNUMOPTIONAL)
                .setIdOptional(OPT_IDOPTIONAL).setNameOptional(OPT_NAMEOPTIONAL)
                .setNumber2Optional(OPT_NUMBER2OPTIONAL).addElementAmountList(LIST_AMOUNTLIST_1).
                        addElementAmountList(LIST_AMOUNTLIST_2).addElementByteStringObjList(LIST_BYTESTRINGOBJLIST_1).
                        addElementByteStringObjList(LIST_BYTESTRINGOBJLIST_2).addElementFloatNumList(LIST_FLOATNUMLIST_1).
                        addElementFloatNumList(LIST_FLOATNUMLIST_2).addElementIdList(LIST_IDLIST_1).
                        addElementIdList(LIST_IDLIST_2).addElementNameList(LIST_NAMELIST_1).
                        addElementNameList(LIST_NAMELIST_2).addElementBoolValList(LIST_BOOLVAL_1).
                        addElementBoolValList(LIST_BOOLVAL_2).addElementNumber2List(LIST_NUMBER2LIST_1).
                        addElementNumber2List(LIST_NUMBER2LIST_2).build();
    }

    public static boolean equalsTestObjRequired(final JunitTestLargeObject testObj) {
        return testObj.getId() == REQ_ID && testObj.getNumber2() == REQ_NUMBER2 && testObj.getName().equals(REQ_NAME) &&
                testObj.getBoolVal() && testObj.getAmount() == REQ_AMOUNT &&
                testObj.getFloatNum() == REQ_FLOATNUM &&
                testObj.getByteStringObj().toStringUtf8().equals(REQ_BYTESTRINGOBJ.toStringUtf8());
    }

    public static boolean equalsTestObjRequiredAndOptional(final JunitTestLargeObject testObj) {
        return equalsTestObjRequired(testObj) && testObj.getIdOptional() == OPT_IDOPTIONAL && testObj.getNumber2Optional() == OPT_NUMBER2OPTIONAL &&
                testObj.getNameOptional().equals(OPT_NAMEOPTIONAL) && !testObj.getBoolValOptional() &&
                testObj.getAmountOptional() == OPT_AMOUNTOPTIONAL && testObj.getFloatNumOptional() == OPT_FLOATNUMOPTIONAL &&
                testObj.getByteStringObjOptional().toStringUtf8().equals(OPT_BYTESTRINGOBJOPTIONAL.toStringUtf8());
    }

    public static boolean equalsTestObjAllFields(final JunitTestLargeObject testObj) {
        return equalsTestObjRequired(testObj) && equalsTestObjRequiredAndOptional(testObj) &&
                (Integer) testObj.getIdList().get(0) == LIST_IDLIST_1 &&
                (Integer) testObj.getIdList().get(1) == LIST_IDLIST_2 &&
                (Long) testObj.getNumber2List().get(0) == LIST_NUMBER2LIST_1 &&
                (Long) testObj.getNumber2List().get(1) == LIST_NUMBER2LIST_2 &&
                testObj.getNameList().get(0).equals(LIST_NAMELIST_1) &&
                testObj.getNameList().get(1).equals(LIST_NAMELIST_2) &&
                !((Boolean) testObj.getBoolValList().get(0)) &&
                (Boolean) testObj.getBoolValList().get(1) &&
                (Double) testObj.getAmountList().get(0) == LIST_AMOUNTLIST_1 &&
                (Double) testObj.getAmountList().get(1) == LIST_AMOUNTLIST_2 &&
                (Float) testObj.getFloatNumList().get(0) == LIST_FLOATNUMLIST_1 &&
                (Float) testObj.getFloatNumList().get(1) == LIST_FLOATNUMLIST_2 &&
                ((ByteString) testObj.getByteStringObjList().get(0)).toStringUtf8().equals(LIST_BYTESTRINGOBJLIST_1.toStringUtf8()) &&
                ((ByteString) testObj.getByteStringObjList().get(1)).toStringUtf8().equals(LIST_BYTESTRINGOBJLIST_2.toStringUtf8());
    }

    // Required
    private static final int REQ_ID = 11415151;
    private static final long REQ_NUMBER2 = 12312978937548142L;
    private static final String REQ_NAME = "Jarle Hansen";
    private static final boolean REQ_BOOLVAL = true;
    private static final double REQ_AMOUNT = 4551.125;
    private static final float REQ_FLOATNUM = 1241246.124156F;
    private static final ByteString REQ_BYTESTRINGOBJ = ByteString.copyFromUtf8("testing testing");

    // Optional
    private static final int OPT_IDOPTIONAL = 155112;
    private static final long OPT_NUMBER2OPTIONAL = 14184771791L;
    private static final String OPT_NAMEOPTIONAL = "Optional Name";
    private static final boolean OPT_BOOLVALOPTIONAL = false;
    private static final double OPT_AMOUNTOPTIONAL = 124124.12417;
    private static final float OPT_FLOATNUMOPTIONAL = 8588912146561F;
    private static final ByteString OPT_BYTESTRINGOBJOPTIONAL = ByteString.copyFromUtf8("optional String");

    // Repeated
    private static final int LIST_IDLIST_1 = 2455;
    private static final int LIST_IDLIST_2 = 12515156;
    private static final long LIST_NUMBER2LIST_1 = 189248124781498L;
    private static final long LIST_NUMBER2LIST_2 = 86898927L;
    private static final String LIST_NAMELIST_1 = "First Name";
    private static final String LIST_NAMELIST_2 = "Second Name";
    private static final boolean LIST_BOOLVAL_1 = false;
    private static final boolean LIST_BOOLVAL_2 = true;
    private static final double LIST_AMOUNTLIST_1 = 9041241.124124;
    private static final double LIST_AMOUNTLIST_2 = 9182905.1251;
    private static final float LIST_FLOATNUMLIST_1 = 124901824.14124F;
    private static final float LIST_FLOATNUMLIST_2 = 8214.124F;
    private static final ByteString LIST_BYTESTRINGOBJLIST_1 = ByteString.copyFromUtf8("ajwdoiajdi");
    private static final ByteString LIST_BYTESTRINGOBJLIST_2 = ByteString.copyFromUtf8("asdasfasfsafsafasghsddfhfhfdasfsad");
}
