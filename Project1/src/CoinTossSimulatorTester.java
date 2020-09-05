public class CoinTossSimulatorTester {
	public static void main (String []args) {
		
		CoinTossSimulator coinToss = new CoinTossSimulator();
		coinToss.run(0);
		System.out.println("After constructor:");
		System.out.println("Number of Trials [exp 0]:" + coinToss.getNumTrials());
		System.out.println("Two-head tosses:"+ coinToss.getTwoHeads());
		System.out.println("Two-tail tosses:"+ coinToss.getTwoTails());
		System.out.println("one-head one-tail tosses:"+ coinToss.getHeadTails());
		
		
		coinToss.run(1);
		System.out.println("After run(1):");
		System.out.println("Number of Trials [exp 1]:" + coinToss.getNumTrials());
		System.out.println("Two-head tosses:"+ coinToss.getTwoHeads());
		System.out.println("Two-tail tosses:"+ coinToss.getTwoTails());
		System.out.println("one-head one-tail tosses:"+ coinToss.getHeadTails());
		
		coinToss.run(10);
		System.out.println("After run(10):");
		System.out.println("Number of Trials [exp 11]:" + coinToss.getNumTrials());
		System.out.println("Two-head tosses:"+ coinToss.getTwoHeads());
		System.out.println("Two-tail tosses:"+ coinToss.getTwoTails());
		System.out.println("one-head one-tail tosses:"+ coinToss.getHeadTails());
		
		coinToss.run(100);
		System.out.println("After run(100):");
		System.out.println("Number of Trials [exp 111]:" + coinToss.getNumTrials());
		System.out.println("Two-head tosses:"+ coinToss.getTwoHeads());
		System.out.println("Two-tail tosses:"+ coinToss.getTwoTails());
		System.out.println("one-head one-tail tosses:"+ coinToss.getHeadTails());
		
		coinToss.reset();
		System.out.println("After reset");
		System.out.println("Number of Trials [exp 0]:" + coinToss.getNumTrials());
		System.out.println("Two-head tosses:"+ coinToss.getTwoHeads());
		System.out.println("Two-tail tosses:"+ coinToss.getTwoTails());
		System.out.println("one-head one-tail tosses:"+ coinToss.getHeadTails());
		
		coinToss.run(1000);
		System.out.println("After run(1000):");
		System.out.println("Number of Trials [exp 1000]:" + coinToss.getNumTrials());
		System.out.println("Two-head tosses:"+ coinToss.getTwoHeads());
		System.out.println("Two-tail tosses:"+ coinToss.getTwoTails());
		System.out.println("one-head one-tail tosses:"+ coinToss.getHeadTails());
		
		
		

		
		
		
		
	}

}
