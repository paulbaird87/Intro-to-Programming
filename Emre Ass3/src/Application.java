public class Application {
	
		private BackEnd backEnd;
		private FrontEndGTerm uiGT;
		private FrontEndConsole uiConsole;
	
		public Application() {
			
				this.backEnd = new BackEnd();
				this.uiGT = new FrontEndGTerm(backEnd);
				this.uiConsole = new FrontEndConsole(backEnd);
				
		}
		
		public static void main(String[] args) {
			
				Application app = new Application();
		}
}