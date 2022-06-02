package Study.LuzinA;

public class IndexOfCoincidence {




    void indexOfCoincidence (String text, int textLength, int gamma) {
        int i, g, groupLen;
        char offset;
        int n = 26;
        groupLen = textLength/gamma;
        double index [] = new double [gamma];
        for (g=0; g< gamma; g++) {
            double freqs[] = new double[n];
            for (i = g; i < textLength; i += gamma) {
                offset = Character.isUpperCase(text.charAt(i)) ? 'A' : 'a';
                if (Character.isLetter(text.charAt(i)))
                    freqs[text.charAt(i) - offset]++;
            }
            for (i = 0; i < n; i++) {
                index[g] += freqs[i] * (freqs[i] - 1);
                }
            index [g] = index [g] /(groupLen*(groupLen-1));

        }
        double index_s = 0.0;
        for (g = 0; g<gamma; g++)
            index_s += index[g];
        index_s = index_s/ gamma;
        System.out.println("Gamma len: " + gamma + " Index: " + index_s);
    }
}
