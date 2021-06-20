
/**
 * Finds all of the genes in a dna sequence and prints them and their number.
 * A single gene starts with the ATG codon, is a multiple of 3,
 * and ends with either TAA, TAG or TGA - whichever is the first strand after ATG
 * 
 * (the dna is given in test())
 */
public class AllGenes {
    public String findGene(String dna, int look_at){
        int startIndex = dna.indexOf("ATG", look_at);
        if (startIndex == -1){return "";} //no gene in dna - return an empty string
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //int minIndex = Math.min(tgaIndex, Math.min(taaIndex,tagIndex));
        //if (minIndex == dna.length()){return "";} //alt:
        int minIndex = 0;
        if (taaIndex == -1 ||
             (tgaIndex != -1 && tgaIndex < taaIndex)){minIndex = tgaIndex;}
        else {minIndex = taaIndex;}  
        if (minIndex == -1 ||
             (tagIndex != -1 && tagIndex < minIndex)){minIndex = tagIndex;}
        if (minIndex == -1){return "";}
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon);
        while (currIndex != -1){
            if ((currIndex - startIndex)%3 == 0){return currIndex;}
            else {currIndex = dna.indexOf(stopCodon, currIndex + 1);}
        }
        return -1; //dna.length()
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while (true){
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()){break;}
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + 
                         currGene.length();
        }
    }    

    public int howManyGenes(String dna){
        int thisMany = 0;
        int startIndex = 0;
        while (true){
            String currGene = findGene(dna, startIndex);
            if (currGene.isEmpty()){break;}
            thisMany = thisMany + 1;
            startIndex = dna.indexOf(currGene, startIndex) + 
                         currGene.length();
        }
        return thisMany;
    }
    
    public void test(){
    // testing howManyGenes method:  
    String dna = "AATGCGTAAGTAATGGTATGGCCTGCTAAGT"; //test dna strand
    System.out.println("DNA strand is " + dna);
    int result = howManyGenes(dna);
    System.out.println("Number of genes in a strand: " + result);
    // testing printAllGenes method - just calling the method    
    printAllGenes(dna);
    }

    
//    public static void main (String[] args) {
//        AllGenes pr = new AllGenes();
//        pr.test();
//    }
}
