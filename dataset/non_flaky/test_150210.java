class DummyClass_150210 {
  @Test
  public void testSet() throws Exception {
    LongColumnVector input1 = new LongColumnVector(10);
    LongColumnVector input2 = new LongColumnVector(10);
    UnionColumnVector input = new UnionColumnVector(10, input1, input2);
    input.init();
    LongColumnVector output1 = new LongColumnVector(10);
    LongColumnVector output2 = new LongColumnVector(10);
    UnionColumnVector output = new UnionColumnVector(10, output1, output2);
    output.init();
    input1.isRepeating = true;
    for(int i=0; i < 10; ++i) {
      input.tags[i] = i % 2;
      input1.vector[i] = i + 1;
      input2.vector[i] = i + 2;
    }
    output.setElement(3, 4, input);
    StringBuilder buf = new StringBuilder();
    output.stringifyValue(buf, 3);
    assertEquals("{\"tag\": 0, \"value\": 1}", buf.toString());
    input.noNulls = false;
    input.isNull[5] = true;
    output.setElement(3, 5, input);
    buf = new StringBuilder();
    output.stringifyValue(buf, 3);
    assertEquals("null", buf.toString());
    input.reset();
    assertEquals(false, input1.isRepeating);
    assertEquals(true, input.noNulls);
  }

}