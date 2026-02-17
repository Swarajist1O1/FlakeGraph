class DummyClass_156087 {
    @Test
    public void TestAsyncTaskBasicCG() {
        prepareTarget(methodSigFromComponents(TARGET_CLASS, TARGET_METHOD), TARGET_CLASS);

        asyncFuncMaps.clear();
        asyncFuncMaps.put("doInBackground", DO_IN_BG);
        asyncFuncMaps.put("onPreExecute", ON_PRE_EXE);
        asyncFuncMaps.put("onPostExecute", ON_POS_EXE);
        asyncFuncMaps.put("onProgressUpdate", ON_PRO_UPD);

        int full = 0, ret = 0;
        for(String key: asyncFuncMaps.keySet())
        {
            full |= asyncFuncMaps.get(key);
        }

        for (Edge edge : Scene.v().getCallGraph()) {
            String sig = edge.getTgt().method().toString();
            for (String key : asyncFuncMaps.keySet()) {
                if (sig.contains(key))
                    ret |= asyncFuncMaps.get(key);
            }
        }

        //The four functions shall all appear in call graph
        Assert.assertEquals(ret, full);
    }

}