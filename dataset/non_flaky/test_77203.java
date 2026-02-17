class DummyClass_77203 {
    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        // due to a jackson bug, a float number which is larger than Double.POSITIVE_INFINITY cannot be convert to BigDecimal correctly
        // https://github.com/FasterXML/jackson-databind/issues/1770
        // https://github.com/FasterXML/jackson-databind/issues/2087
        bigDecimalMapper = new ObjectMapper().enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);
        bigIntegerMapper = new ObjectMapper().enable(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS);

    }

}