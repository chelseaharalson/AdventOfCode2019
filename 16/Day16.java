import java.io.IOException;

public class Day16 {

	private static final String puzzleInput = "59750530221324194853012320069589312027523989854830232144164799228029162830477472078089790749906142587998642764059439173975199276254972017316624772614925079238407309384923979338502430726930592959991878698412537971672558832588540600963437409230550897544434635267172603132396722812334366528344715912756154006039512272491073906389218927420387151599044435060075148142946789007756800733869891008058075303490106699737554949348715600795187032293436328810969288892220127730287766004467730818489269295982526297430971411865028098708555709525646237713045259603175397623654950719275982134690893685598734136409536436003548128411943963263336042840301380655801969822";
    static int[] pattern = {0,1,0,-1};
    
	public static void main(String[] args) throws IOException {
        String input = puzzleInput;
		// PART 1
		for (int i = 0; i < 100; i++) {
			input = fft(input);
		}
		System.out.println(input);
	}

    // PART 1
	public static String fft(String input) {
		char[] charArr = input.toCharArray();
		StringBuffer result = new StringBuffer();
		for (int multiplier = 0; multiplier < charArr.length; multiplier++) { 
            long newNum = 0;
            // Skip leading zeros
			for (int charIdx = multiplier; charIdx < charArr.length; charIdx++) {
				int num = Character.getNumericValue(charArr[charIdx]);
				int patternVal = getMultiplier(multiplier, charIdx);
                // Skip zero and all following zeros
				if ((patternVal == 0)) {
					int skip = (multiplier);
					charIdx += skip;
					continue;
				}
				newNum = newNum + num * patternVal;
			}
			result.append(Math.abs(newNum) % 10);
		}
		return result.toString();
	}

	private static int getMultiplier(int multiplier, int charIdx) {
        // Skip to appropriate pattern value based on multiplier
		int repetition = multiplier + 1;
		int idx = (charIdx + 1) / repetition;
		int patternVal = pattern[idx % 4];
		return patternVal;
	}

}
