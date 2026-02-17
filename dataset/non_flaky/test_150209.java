class DummyClass_150209 {
  @Test
  public void testFlatten() throws Exception {
    LongColumnVector col1 = new LongColumnVector(10);
    LongColumnVector col2 = new LongColumnVector(10);
    UnionColumnVector vector = new UnionColumnVector(10, col1, col2);
    vector.init();
    col1.isRepeating = true;
    for(int i=0; i < 10; ++i) {
      vector.tags[i] = i % 2;
      col1.vector[i] = i;
      col2.vector[i] = 2 * i;
    }
    vector.flatten(false, null, 10);
    assertFalse(col1.isRepeating);
    for(int i=0; i < 10; ++i) {
      assertEquals(i % 2, vector.tags[i]);
      assertEquals("col1 at " + i, 0, col1.vector[i]);
      assertEquals("col2 at " + i, 2 * i, col2.vector[i]);
    }
    vector.unFlatten();
    assertTrue(col1.isRepeating);
    for(int i=0; i < 10; ++i) {
      StringBuilder buf = new StringBuilder();
      vector.stringifyValue(buf, i);
      assertEquals("{\"tag\": " + (i % 2) + ", \"value\": " +
          (i % 2 == 0 ? 0 : 2 * i) + "}", buf.toString());
    }
    vector.reset();
    assertFalse(col1.isRepeating);
  }

}