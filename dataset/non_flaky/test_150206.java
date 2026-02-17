class DummyClass_150206 {
  @Test
  public void testFlatten() throws Exception {
    LongColumnVector col1 = new LongColumnVector(10);
    ListColumnVector vector = new ListColumnVector(10, col1);
    vector.init();

    // TEST - repeating NULL & no selection
    col1.isRepeating = true;
    vector.isRepeating = true;
    vector.noNulls = false;
    vector.isNull[0] = true;
    vector.childCount = 0;
    for(int i=0; i < 10; ++i) {
      col1.vector[i] = i + 3;
      vector.offsets[i] = i;
      vector.lengths[i] = 10 + i;
    }
    vector.flatten(false, null, 10);
    // make sure the vector was flattened
    assertFalse(vector.isRepeating);
    assertFalse(vector.noNulls);
    // child isn't flattened, because parent is repeating null
    assertTrue(col1.isRepeating);
    assertTrue(col1.noNulls);
    for(int i=0; i < 10; ++i) {
      assertTrue("isNull at " + i, vector.isNull[i]);
    }
    for(int i=0; i < 10; ++i) {
      StringBuilder buf = new StringBuilder();
      vector.stringifyValue(buf, i);
      assertEquals("null", buf.toString());
    }
    vector.unFlatten();
    assertTrue(col1.isRepeating);
    assertTrue(vector.isRepeating);

    // TEST - repeating NULL & selection
    Arrays.fill(vector.isNull, 1, 10, false);
    int[] sel = new int[]{3, 5, 7};
    vector.flatten(true, sel, 3);
    for(int i=1; i < 10; i++) {
      assertEquals("failure at " + i,
          i == 3 || i == 5 || i == 7, vector.isNull[i]);
    }
    vector.unFlatten();

    // TEST - repeating non-NULL & no-selection
    vector.noNulls = true;
    vector.isRepeating = true;
    vector.offsets[0] = 0;
    vector.lengths[0] = 3;
    vector.childCount = 3;
    vector.flatten(false, null, 10);
    // make sure the vector was flattened
    assertFalse(vector.isRepeating);
    assertFalse(vector.noNulls);
    assertFalse(col1.isRepeating);
    assertFalse(col1.noNulls);
    for(int i=0; i < 10; ++i) {
      assertEquals("offset at " + i, 0, vector.offsets[i]);
      assertEquals("length at " + i, 3, vector.lengths[i]);
    }
    for(int i=0; i < 10; ++i) {
      StringBuilder buf = new StringBuilder();
      vector.stringifyValue(buf, i);
      assertEquals("[3, 3, 3]", buf.toString());
    }
    vector.unFlatten();
    assertTrue(col1.isRepeating);
    assertTrue(col1.noNulls);
    assertTrue(vector.isRepeating);
    assertTrue(vector.noNulls);

    // TEST - repeating non-NULL & selection
    Arrays.fill(vector.offsets, 1, 10, -1);
    Arrays.fill(vector.lengths, 1, 10, -1);
    Arrays.fill(col1.vector, 1, 10, -1);
    vector.flatten(true, sel, 3);
    for(int i=1; i < 10; i++) {
      if (i == 3 || i == 5 || i == 7) {
        assertEquals("failure at " + i, 0, vector.offsets[i]);
        assertEquals("failure at " + i, 3, vector.lengths[i]);
      } else {
        assertEquals("failure at " + i, -1, vector.offsets[i]);
        assertEquals("failure at " + i, -1, vector.lengths[i]);
      }
    }
    for(int i=0; i < 3; ++i) {
      assertEquals("failure at " + i, 3, col1.vector[i]);
    }
    for(int i=3; i < 10; ++i) {
      assertEquals("failure at " + i, -1, col1.vector[i]);
    }
    vector.unFlatten();

    // TEST - reset
    vector.reset();
    assertFalse(col1.isRepeating);
    assertTrue(col1.noNulls);
    assertFalse(vector.isRepeating);
    assertTrue(vector.noNulls);
    assertEquals(0, vector.childCount);
  }

}