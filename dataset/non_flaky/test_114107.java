class DummyClass_114107 {
    @Test
    public void optionalLongConverterWorksCorrectly() {
        OptionalLongAttributeConverter converter = OptionalLongAttributeConverter.create();

        assertThat(transformFrom(converter, OptionalLong.empty())).isEqualTo(nullValue().toAttributeValue());
        assertThat(transformFrom(converter, OptionalLong.of(Long.MIN_VALUE))).isEqualTo(fromNumber("-9223372036854775808").toAttributeValue());
        assertThat(transformFrom(converter, OptionalLong.of(0))).isEqualTo(fromNumber("0").toAttributeValue());
        assertThat(transformFrom(converter, OptionalLong.of(Long.MAX_VALUE))).isEqualTo(fromNumber("9223372036854775807").toAttributeValue());

        assertThat(transformTo(converter, nullValue().toAttributeValue())).isEmpty();
        assertThat(transformTo(converter, fromNumber("-9223372036854775808"))).hasValue(Long.MIN_VALUE);
        assertThat(transformTo(converter, fromNumber("0"))).hasValue(0);
        assertThat(transformTo(converter, fromNumber("9223372036854775807"))).hasValue(Long.MAX_VALUE);
    }

}