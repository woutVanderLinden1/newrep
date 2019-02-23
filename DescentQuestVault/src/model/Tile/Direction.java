package model.Tile;

public enum Direction {
 
	UP{
		public Direction nextDirection(){
			return Direction.LEFT;
		}
		

		public boolean isOpposite(Direction direct) {
			if(direct==DOWN) {
				return true;
			}
			return false;
		}
	},
	DOWN{
		public Direction nextDirection(){
			return Direction.RIGHT;
			
		}
		public boolean isOpposite(Direction direct) {
			if(direct==UP) {
				return true;
			}
			return false;
		}
	},
	LEFT{
		public Direction nextDirection(){
			return Direction.DOWN;
		}
		public boolean isOpposite(Direction direct) {
			if(direct==RIGHT) {
				return true;
			}
			return false;
		}
	},
	RIGHT{
		public Direction nextDirection(){
			return Direction.UP;
		}
		public boolean isOpposite(Direction direct) {
			if(direct==LEFT) {
				return true;
			}
			return false;
		}
	};

	public Direction nextDirection(){
		return null;
	}

	public Direction nextDirection(int i) {
		if(i==1) {
			return nextDirection(); 
		}
		return nextDirection(i-1).nextDirection();
	}

	public boolean isOpposite(Direction direct) {
		
		return false;
	}
	
}
