package org.starcoin.types;


import org.starcoin.utils.Hex;

public final class AccountAddress {
    static final int LENGTH = 16;
    public final java.util.@com.novi.serde.ArrayLen(length = 16) List<@com.novi.serde.Unsigned Byte> value;

    public AccountAddress(java.util.@com.novi.serde.ArrayLen(length = 16) List<@com.novi.serde.Unsigned Byte> value) {
        java.util.Objects.requireNonNull(value, "value must not be null");
        this.value = value;
    }

    public static AccountAddress deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.value = TraitHelpers.deserialize_array16_u8_array(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static AccountAddress bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
            throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        AccountAddress value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
            throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public static AccountAddress valueOf(byte[] values) {
        if (values.length != LENGTH) {
            throw new java.lang.IllegalArgumentException("Invalid length for AccountAddress");
        }
        java.util.List<Byte> address = new java.util.ArrayList<Byte>(LENGTH);
        for (int i = 0; i < LENGTH; i++) {
            address.add(Byte.valueOf(values[i]));
        }
        return new AccountAddress(address);
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        TraitHelpers.serialize_array16_u8_array(value, serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AccountAddress other = (AccountAddress) obj;
        if (!java.util.Objects.equals(this.value, other.value)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.value != null ? this.value.hashCode() : 0);
        return value;
    }

    public byte[] toBytes() {
        byte[] bytes = new byte[LENGTH];
        int i = 0;
        for (Byte item : value) {
            bytes[i++] = item.byteValue();
        }
        return bytes;
    }

    @Override
    public String toString() {
        return "AccountAddress{" +
                "value=" + Hex.encode(value) +
                '}';
    }

    public static final class Builder {
        public java.util.@com.novi.serde.ArrayLen(length = 16) List<@com.novi.serde.Unsigned Byte> value;

        public AccountAddress build() {
            return new AccountAddress(
                    value
            );
        }
    }
}
