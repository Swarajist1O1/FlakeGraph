class DummyClass_114105 {
    @Test
    public void optionalDoubleConverterWorksCorrectly() {
        OptionalDoubleAttributeConverter converter = OptionalDoubleAttributeConverter.create();

        assertThat(transformFrom(converter, OptionalDouble.empty())).isEqualTo(nullValue().toAttributeValue());
        assertThat(transformFrom(converter, OptionalDouble.of(-Double.MAX_VALUE))).isEqualTo(fromNumber("-1.7976931348623157E308").toAttributeValue());
        assertThat(transformFrom(converter, OptionalDouble.of(-Double.MIN_VALUE))).isEqualTo(fromNumber("-4.9E-324").toAttributeValue());
        assertThat(transformFrom(converter, OptionalDouble.of(0.0))).isEqualTo(fromNumber("0.0").toAttributeValue());
        assertThat(transformFrom(converter, OptionalDouble.of(Double.MIN_VALUE))).isEqualTo(fromNumber("4.9E-324").toAttributeValue());
        assertThat(transformFrom(converter, OptionalDouble.of(Double.MAX_VALUE))).isEqualTo(fromNumber("1.7976931348623157E308").toAttributeValue());

        assertThat(transformTo(converter, nullValue().toAttributeValue())).isEmpty();
        assertThat(transformTo(converter, fromNumber("-1.7976931348623157E308"))).hasValue(-Double.MAX_VALUE);
        assertThat(transformTo(converter, fromNumber("-4.9E-324"))).hasValue(-Double.MIN_VALUE);
        assertThat(transformTo(converter, fromNumber("0.0"))).hasValue(0.0);
        assertThat(transformTo(converter, fromNumber("4.9E-324"))).hasValue(Double.MIN_VALUE);
        assertThat(transformTo(converter, fromNumber("1.7976931348623157E308"))).hasValue(Double.MAX_VALUE);
    }

}