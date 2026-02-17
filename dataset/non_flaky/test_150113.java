class DummyClass_150113 {
  @Test
  public void testEvaluateWithoutRun() throws HiveException {
    // init udf
    Udf udf = new Udf();
    ObjectInspector[] initArguments = {queryOI, argOI};
    udf.initialize(initArguments);
    //set arguments
    DeferredObject queryObj = new DeferredJavaObject("hello(:1)");
      DeferredObject argObj = new DeferredJavaObject("name");
      DeferredObject[] argumentsObj = {queryObj, argObj};
      
      // init exec and set parameters, included
      udf.initExec(argumentsObj);
      udf.setParameters(argumentsObj);
      
      // checking var exists and its value is right
      Var var = udf.exec.findVariable(":1");
      Assert.assertNotNull(var);
      String val = (String) var.value;
      Assert.assertEquals(val, "name");
  }

}