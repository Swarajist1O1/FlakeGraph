class DummyClass_156086 {
    @Test
    public void TestAsyncTaskBasicCG() {
        prepareTarget(methodSigFromComponents(TARGET_CLASS, TARGET_METHOD), TARGET_CLASS);
        boolean found = false;
        for (Edge edge : Scene.v().getCallGraph()) {
            //String sig = edge.getTgt().method().toString();
            System.out.println(edge);
            String sig = edge.getTgt().method().toString();

            if (edge.toString().contains("AHelper") && edge.toString().contains("handle"))
                found = true;
        }

        //Assert.assertTrue(found);
    }

}