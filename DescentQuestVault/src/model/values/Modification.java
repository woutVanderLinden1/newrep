package model.values;

public enum Modification {
	SET{
		public int calculate(int modvalue, int oldtotal) {
			// TODO Auto-generated method stub
			return modvalue;
		}

	},
	PLUS{
		public int calculate(int modvalue, int oldtotal) {
			// TODO Auto-generated method stub
			return modvalue+oldtotal;
		}
	},
	MINUS{
		public int calculate(int modvalue, int oldtotal) {
			// TODO Auto-generated method stub
			return oldtotal-modvalue;
		}
	},
	MULTIPLY{
		public int calculate(int modvalue, int oldtotal) {
			// TODO Auto-generated method stub
			return modvalue*oldtotal;
		}
	},
	DIVIDE{
		public int calculate(int modvalue, int oldtotal) {
			// TODO Auto-generated method stub
			return modvalue/oldtotal;
		}
	}
	
	
	
	
	
	
	
	
	
	;

	public int calculate(int modvalue, int oldtotal) {
		// TODO Auto-generated method stub
		return 0;
	}

}
