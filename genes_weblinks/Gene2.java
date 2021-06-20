/**
 * Takes a dna sequence, either written in uppercase or lowercase, and looks for a gene
 * (a gene starts with ATG, ends with TAA and is a multiple of 3)
 * 
 */
public class Gene2 {
    public String findSimpleGene(String dna, int start, int end) {
    // takes in a dna sequence and looks for a gene in it:
    // gene starts with ATG, ends with TAA strand, and is a multiple of 3
    String gene = "";
    if (start == -1) {return gene;}
    if (end == -1) {return gene;}
    if ((end-start)%3 == 0) {gene = dna.substring(start, end + 3);}
    return gene;
    }
    
    public void startEnd (String dna){
    int flag = 0;    
    if (dna.toLowerCase().equals(dna)){
        flag = 1;
        dna = dna.toUpperCase();
    }    
    int start = dna.indexOf("ATG");
    int end = dna.indexOf("TAA", start + 3);
    String geneResult = findSimpleGene(dna,start,end);
    if (flag == 1){geneResult = geneResult.toLowerCase();}
    System.out.println("Gene is " + geneResult);
    }
    
    public void testSimpleGene() {
    // testing findSimpleGene method:    
    String dna = "AATGCGTAATATGGT";
    System.out.println("DNA strand is " + dna);
    startEnd(dna);
        
    //dna = "AATGCTAGGGTAATATGGT";
    dna = "CGATGGTTTGT";
    System.out.println("DNA strand is " + dna);
    startEnd(dna);
       
    dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
    System.out.println("DNA strand is " + dna);
    startEnd(dna);
    
    dna = "TTATAA";
    System.out.println("DNA strand is " + dna);
    startEnd(dna);
    
    dna = "aatgccgtaagt";
    System.out.println("DNA strand is " + dna);
    startEnd(dna);
    }
    
//    public static void main (String[] args) {
//        Gene2 pr = new Gene2();
//        pr.testSimpleGene();
//    }
}