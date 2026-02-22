import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;    
    public class Autocomplete {
        private final Term[] terms;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null) throw new IllegalArgumentException("Terms cannot be null");

        this.terms = new Term[terms.length];
        for(int i =0; i < terms.length; i++) {
            if (terms [i] == null) throw new IllegalArgumentException("Term cannot be null");
            this.terms[i] = terms[i];
        }

        Arrays.sort(this.terms);
    }

    // Returns all terms that start with the given prefix,
    // in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("Prefix cannot be null");

        Term prefixTerm = new Term(prefix, 0);
        int first = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
        
        if (first == -1) return new Term[0]; // No matches found

        int last = BinarySearchDeluxe.lastIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
        
        // Extract the sub-array of matches
        int count = last - first + 1;
        Term[] matches = new Term[count];
        for (int i = 0; i < count; i++) {
            matches[i] = terms[first + i];
        }

        // Sort matches by descending weight
        Arrays.sort(matches, Term.byReverseWeightOrder());
        return matches;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null) throw new IllegalArgumentException("Prefix cannot be null");
        
        Term prefixTerm = new Term(prefix, 0);
        int first = BinarySearchDeluxe.firstIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
        if (first == -1) return 0;

        int last = BinarySearchDeluxe.lastIndexOf(terms, prefixTerm, Term.byPrefixOrder(prefix.length()));
        return last - first + 1;
    }

    // unit testing
    public static void main(String[] args) {

    // read in the terms from a file
    String filename = args[0];
    In in = new In(filename);
    int n = in.readInt();
    Term[] terms = new Term[n];
    for (int i = 0; i < n; i++) {
        long weight = in.readLong();           // read the next weight
        in.readChar();                         // scan past the tab
        String query = in.readLine();          // read the next query
        terms[i] = new Term(query, weight);    // construct the term
    }

    // read in queries from standard input and print the top k matching terms
    int k = Integer.parseInt(args[1]);
    Autocomplete autocomplete = new Autocomplete(terms);
    while (StdIn.hasNextLine()) {
        String prefix = StdIn.readLine();
        Term[] results = autocomplete.allMatches(prefix);
        StdOut.printf("%d matches\n", autocomplete.numberOfMatches(prefix));
        for (int i = 0; i < Math.min(k, results.length); i++)
            StdOut.println(results[i]);
    }
}

}