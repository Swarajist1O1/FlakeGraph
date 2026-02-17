class DummyClass_150207 {
  @Test
  public void testSet() throws Exception {
    LongColumnVector input1 = new LongColumnVector(10);
    ListColumnVector input = new ListColumnVector(10, input1);
    input.init();
    LongColumnVector output1 = new LongColumnVector(30);
    ListColumnVector output = new ListColumnVector(10, output1);
    output.init();
    input.noNulls = false;
    input.isNull[6] = true;
    input.childCount = 11;
    Arrays.fill(output1.vector, -1);
    for(int i=0; i < 10; ++i) {
      input1.vector[i] = 10 * i;
      input.offsets[i] = i;
      input.lengths[i] = 2;
      output.offsets[i] = i + 2;
      output.lengths[i] = 3;
    }
    output.childCount = 30;

    // copy a null
    output.isNull[3] = false;
    output.setElement(3, 6, input);
    assertEquals(30, output.childCount);
    StringBuilder buf = new StringBuilder();
    output.stringifyValue(buf, 3);
    assertEquals("null", buf.toString());

    // copy a value
    output.isNull[3] = false;
    output.setElement(3, 5, input);
    assertEquals(30, output.offsets[3]);
    assertEquals(2, output.lengths[3]);
    assertEquals(32, output.childCount);
    buf = new StringBuilder();
    output.stringifyValue(buf, 3);
    assertEquals("[50, 60]", buf.toString());

    // overwrite a value
    output.isNull[3] = false;
    output.setElement(3, 4, input);
    assertEquals(34, output.childCount);
    assertEquals(34, output1.vector.length);
    assertEquals(50, output1.vector[30]);
    assertEquals(60, output1.vector[31]);
    buf = new StringBuilder();
    output.stringifyValue(buf, 3);
    assertEquals("[40, 50]", buf.toString());

    input.reset();
    assertEquals(false, input1.isRepeating);
    assertEquals(true, input.noNulls);
    output.reset();
    assertEquals(0, output.childCount);

    input.isRepeating = true;
    input.offsets[0] = 0;
    input.lengths[0] = 10;
    output.setElement(2, 7, input);
    assertEquals(10, output.childCount);
    buf = new StringBuilder();
    output.stringifyValue(buf, 2);
    assertEquals("[0, 10, 20, 30, 40, 50, 60, 70, 80, 90]", buf.toString());
  }

}