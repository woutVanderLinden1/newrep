package model.values;

public enum Modification {
	SET{
		@Override
		public int calculate(int modvalue, int oldtotal) {
			// TODO Auto-generated method stub
			return modvalue;
		}

	},
	PLUS{
		@Override
		public int calculate(int modvalue, int oldtotal) {
			// TODO Auto-generated method stub
			return modvalue+oldtotal;
		}
	},
	MINUS{
		@Override
		public int calculate(int modvalue, int oldtotal) {
			// TODO Auto-generated method stub
			return Math.max(oldtotal-modvalue,0);
		}
	},
	MULTIPLY{
		@Override
		public int calculate(int modvalue, int oldtotal) {
			// TODO Auto-generated method stub
			return modvalue*oldtotal;
		}
	},
	DIVIDE{
		@Override
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
