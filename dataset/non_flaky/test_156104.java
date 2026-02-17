class DummyClass_156104 {
  @Test
  public void SubClassTest() throws FileNotFoundException, UnsupportedEncodingException {		
	  
	  String testClass = "soot.defaultInterfaceMethods.JavaNCSSCheck";
	  String abstractClass = "soot.defaultInterfaceDifferentPackage.AbstractCheck";
	  String classToAnalyze = "soot.defaultInterfaceDifferentPackage.AbstractCheck";
	  final SootMethod target =
		        prepareTarget(
		            methodSigFromComponents(testClass, voidType, mainClass),
		            testClass,
		            classToAnalyze);
		
		ArrayList<Edge> edges = GetCallGraph();
		
		assertEquals(edges.get(0).getTgt(), Scene.v().getMethod("<soot.defaultInterfaceDifferentPackage.AbstractCheck: void log(java.lang.String,java.lang.String)>"));		
		
	}

}