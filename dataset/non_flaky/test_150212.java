class DummyClass_150212 {
  @Test
  public void testSet() throws Exception {
    LongColumnVector input1 = new LongColumnVector(10);
    LongColumnVector input2 = new LongColumnVector(10);
    StructColumnVector input = new StructColumnVector(10, input1, input2);
    input.init();
    LongColumnVector output1 = new LongColumnVector(10);
    LongColumnVector output2 = new LongColumnVector(10);
    StructColumnVector output = new StructColumnVector(10, output1, output2);
    output.init();
    input1.isRepeating = true;
    input2.noNulls = false;
    input2.isNull[5] = true;
    input.noNulls = false;
    input.isNull[6] = true;
    for(int i=0; i < 10; ++i) {
      input1.vector[i] = i + 1;
      input2.vector[i] = i + 2;
    }
    output.isNull[3] = false;
    output.setElement(3, 6, input);
    StringBuilder buf = new StringBuilder();
    output.stringifyValue(buf, 3);
    assertEquals("null", buf.toString());
    output.isNull[3] = false;
    output.setElement(3, 5, input);
    buf = new StringBuilder();
    output.stringifyValue(buf, 3);
    assertEquals("[1, null]", buf.toString());
    output.isNull[3] = false;
    output.setElement(3, 4, input);
    buf = new StringBuilder();
    output.stringifyValue(buf, 3);
    assertEquals("[1, 6]", buf.toString());
    input.reset();
    assertEquals(false, input1.isRepeating);
    assertEquals(true, input.noNulls);
  }

}