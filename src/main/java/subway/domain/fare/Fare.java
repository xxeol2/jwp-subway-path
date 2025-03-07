package subway.domain.fare;

import subway.exception.SubwayIllegalArgumentException;

public class Fare implements Comparable<Fare> {

    private final int value;

    public Fare() {
        this(0);
    }

    public Fare(final int value) {
        validate(value);
        this.value = value;
    }

    private void validate(final int value) {
        if (value < 0) {
            throw new SubwayIllegalArgumentException("요금은 0원 이상이어야 합니다.");
        }
    }

    public Fare plus(final Fare other) {
        return new Fare(value + other.value);
    }

    public Fare minus(final Fare other) {
        int fare = value - other.value;
        if (fare < 0) {
            return new Fare(0);
        }
        return new Fare(fare);
    }

    public Fare discountByRate(final int discountRate) {
        return new Fare(value - value * discountRate / 100);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Fare fare = (Fare) o;

        return value == fare.value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    @Override
    public int compareTo(final Fare o) {
        return Integer.compare(value, o.value);
    }

    public int getValue() {
        return value;
    }
}
