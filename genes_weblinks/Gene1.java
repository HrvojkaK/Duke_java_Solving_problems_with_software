
/**
 * Takes a dna sequence, and looks for a gene
 * (a gene starts with an ATG sequence, ends with TAA and is a multiple of 3)
 */
public class Gene1 {
    public String findSimpleGene(String dna) {
    // takes in a dna sequence and looks for a gene in it:
    // gene starts with ATG, ends with TAA strand, and is a multiple of 3
    String gene = "";
    int start = dna.indexOf("ATG");
    if (start == -1) {return gene;}
    int end = dna.indexOf("TAA", start + 3);
    if (end == -1) {return gene;}
    if ((end-start)%3 == 0) {gene = dna.substring(start, end + 3);}
    return gene;
    }
    
    public void testSimpleGene() {
    // testing findSimpleGene method:    
    String dna = "AATGCGTAATATGGT";
    System.out.println("DNA strand is " + dna);
    String geneResult = findSimpleGene(dna);
    System.out.println("Gene is " + geneResult);
    
    //dna = "AATGCTAGGGTAATATGGT";
    dna = "CGATGGTTTGT";
    System.out.println("DNA strand is " + dna);
    geneResult = findSimpleGene(dna);
    System.out.println("Gene is " + geneResult);
    
    dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
    System.out.println("DNA strand is " + dna);
    geneResult = findSimpleGene(dna);
    System.out.println("Gene is " + geneResult);
    
    dna = "TTATAA";
    System.out.println("DNA strand is " + dna);
    geneResult = findSimpleGene(dna);
    System.out.println("Gene is " + geneResult);
    
    }
//    public static void main (String[] args) {
//        Gene1 pr = new Gene1();
//        pr.testSimpleGene();
//    }
}
