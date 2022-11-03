
	public class Player {

		//attributes
		private String name;
		private char color;

		//constructor
		public Player(String name, char color) {
		this.name = name;
		this.color = color;
		}

		//getter and setter
		public void setName(String name) {
		this.name = name;
		}
		
		public void setColor(char color) {
			this.color = color;
		}

		public String getName() {
		return name;
		}
		
		public char getColor() {
			return color;
		}
		
		//toString method 
		public String toString() {
			return this.getName() + "(" + this.getColor() + ")";
		}
		
}
