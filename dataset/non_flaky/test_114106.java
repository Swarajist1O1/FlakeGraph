class DummyClass_114106 {
    @Test
    public void optionalIntConverterWorksCorrectly() {
        OptionalIntAttributeConverter converter = OptionalIntAttributeConverter.create();

        assertThat(transformFrom(converter, OptionalInt.empty())).isEqualTo(nullValue().toAttributeValue());
        assertThat(transformFrom(converter, OptionalInt.of(Integer.MIN_VALUE))).isEqualTo(fromNumber("-2147483648").toAttributeValue());
        assertThat(transformFrom(converter, OptionalInt.of(0))).isEqualTo(fromNumber("0").toAttributeValue());
        assertThat(transformFrom(converter, OptionalInt.of(Integer.MAX_VALUE))).isEqualTo(fromNumber("2147483647").toAttributeValue());

        assertThat(transformTo(converter, nullValue().toAttributeValue())).isEmpty();
        assertThat(transformTo(converter, fromNumber("-2147483648"))).hasValue(Integer.MIN_VALUE);
        assertThat(transformTo(converter, fromNumber("0"))).hasValue(0);
        assertThat(transformTo(converter, fromNumber("2147483647"))).hasValue(Integer.MAX_VALUE);
    }

}