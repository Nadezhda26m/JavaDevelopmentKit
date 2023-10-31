package list.register;

import java.util.Objects;

public class PhoneNumber {
    private final String countryPrefix;
    private final int areaCode;
    private final int subscriberNumber;

    public PhoneNumber(String countryPrefix, int areaCode, int subscriberNumber) {
        this.countryPrefix = countryPrefix;
        this.areaCode = areaCode;
        this.subscriberNumber = subscriberNumber;
    }

    @Override
    public String toString() {
        return countryPrefix + areaCode + subscriberNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return this.toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryPrefix, areaCode, subscriberNumber);
    }
}
