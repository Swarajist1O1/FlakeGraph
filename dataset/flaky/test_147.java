class DummyClass_147 {
@Test
public void manyParameters() throws Exception {
    assertThat(testResult(ManyParameters.class), isSuccessful());
    assertEquals(6, ManyParameters.iterations);
    assertEquals(asList(-1, -2, -4, -1, -2, -4), ManyParameters.firstTestCases);
    assertEquals(asList('r', 'r', 'r', 'y', 'y', 'y'), ManyParameters.secondTestCases);
}
}