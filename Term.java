import java.util.Comparator;

public class Term implements Comparable<Term> {
    private final String query;
    private final long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        if (query == null) {
            throw new NullPointerException("Query is null");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("");
        }

        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term v, Term w) {
            if (v.weight < w.weight) return 1;
            if (v.weight > w.weight) return -1;
            return 0;
        }
    }

    // Compares the two terms in lexicographic order,
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r){
        if (r < 0) throw new IllegalArgumentException();
        return new PrefixOrder(r);
    }

    private static class PrefixOrder implements Comparator<Term> {
        private final int r;

        PrefixOrder(int r){
            this.r = r;
        }

        public int compare(Term v, Term w){
            String s1 = v.query;
            String s2 = w.query;
            int minLen = Math.min(s1.length(), s2.length());
            int limit = Math.min(minLen, r);

            for (int = 0; i < limit, i++) {
                if (s1.charAt(i) < s2.charAt(i)) return -1;
                if (s1.charAt(i) > s2.charAt(i)) return 1;
            }

            if (limit == r) return 0;
        }
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return weight + "\t" + query;
    }

    // unit testing
    public static void main(String[] args){
        Term t1 = new Term("apple", 100);
        Term t2 = new Term("apply", 200);

        System.out.println("Natural compare: " + t1.compareTo(t2));
        System.out.println("Prefix (3) compare: " + Term.byPrefixOrder(3).compare(t1, t2));
    }

