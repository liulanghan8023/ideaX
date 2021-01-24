package com.tramp.idea.enums;

/**
 * @author chenjm1
 * @since 2018/1/2
 */
public enum TypeEnum {
    LONG("java.lang.Long", "java.lang.Long"),
    BYTE("java.lang.Long", "java.lang.Long"),
    DOUBLE("java.lang.Double", "java.lang.Double"),
    FLOAT("java.lang.Float", "java.lang.Float"),
    INTEGER("java.lang.Integer", "java.lang.Integer"),
    SHORT("java.lang.Short", "java.lang.Short"),
    BOOLEAN("java.lang.Boolean", "java.lang.Boolean"),
    //MAP("java.util.Map", "java.util.Map"),
    STRING("java.lang.String", "java.lang.String"),
    ;
	private String type;
	private String typePath;

	TypeEnum(String type, String typePath) {
		this.type = type;
		this.typePath = typePath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypePath() {
		return typePath;
	}

	public void setTypePath(String typePath) {
		this.typePath = typePath;
	}

    public static Boolean isExit(String type){
        TypeEnum[] values = values();
        for (TypeEnum value : values) {
            if (value.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
