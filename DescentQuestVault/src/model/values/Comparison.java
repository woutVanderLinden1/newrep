package model.values;

public enum Comparison {
	EQUALS{
		public boolean compare(int theInteger, int compvalue) {
			// TODO Auto-generated method stub
			return theInteger==compvalue;
		}
	}
	, LESS{
		public boolean compare(int theInteger, int compvalue) {
			// TODO Auto-generated method stub
			return theInteger<compvalue;
		}
	}
	, MORE{
		public boolean compare(int theInteger, int compvalue) {
			// TODO Auto-generated method stub
			return theInteger>compvalue;
		}
	}
	, DOESNOTEQUAL{
		public boolean compare(int theInteger, int compvalue) {
			// TODO Auto-generated method stub
			return theInteger!=compvalue;
		}
	};

	public boolean compare(int theInteger, int compvalue) {
		// TODO Auto-generated method stub
		return false;
	}

}
