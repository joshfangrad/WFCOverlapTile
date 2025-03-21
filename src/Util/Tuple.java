package Util;

public class Tuple <F, S> {
    private final F first;
    private final S second;

    public Tuple(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tuple<F,S> t = (Tuple<F,S>)obj;
        return (t.getFirst() == this.first
            && t.getSecond() == this.second);
    }
}
