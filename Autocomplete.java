    import java.util.Arrays;
    
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
    public static void main(String[] args){
        Term[] terms = {
        new Term("apple", 10),
        new Term("apply", 20),
        new Term("ape", 5),
        new Term("bat", 30)
    };

    Autocomplete autocomplete = new Autocomplete(terms);
    
    System.out.println("Matches for 'ap': " + autocomplete.numberOfMatches("ap"));

    Term[] results = autocomplete.allMatches("ap");
    for (Term t : results) {
        System.out.println(t);
    }
    }

}