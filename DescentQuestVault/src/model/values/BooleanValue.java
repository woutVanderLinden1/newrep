package model.values;

public enum BooleanValue {
	TRUE{
		public boolean getValue() {
			return true;
		}
	}
	
	, FALSE
	{
		public boolean getValue() {
			return false;
		}
	};

	public boolean getValue() {
		// TODO Auto-generated method stub
		return false;
	}

	public static BooleanValue toValue(boolean value) {
		if(value) {
			return TRUE;
		}
		return FALSE;
	}

}
