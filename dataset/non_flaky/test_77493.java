class DummyClass_77493 {
    @BeforeEach
    public void throwsAnExceptionOnMalformedFiles() {
        assertThatThrownBy(super::throwsAnExceptionOnMalformedFiles)
                .hasMessageContaining("* Malformed JSON at line:");
    }

}